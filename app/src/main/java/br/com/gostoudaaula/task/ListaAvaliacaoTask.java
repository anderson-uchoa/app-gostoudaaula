package br.com.gostoudaaula.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

import br.com.gostoudaaula.client.ProfessorClient;
import br.com.gostoudaaula.delegate.ListaAvaliacaoDelegate;
import br.com.gostoudaaula.dto.PeriodoDTO;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Professor;

/**
 * Created by alexf on 24/03/16.
 */
public class ListaAvaliacaoTask extends AsyncTask<Void, Void, ArrayList<Avaliacao>> {

    private final ListaAvaliacaoDelegate delegate;
    private final Professor professor;
    private final PeriodoDTO periodo;
    private Exception erro;
    private ProgressDialog progress;
    private Context ctx;


    public ListaAvaliacaoTask(ListaAvaliacaoDelegate delegate, PeriodoDTO periodo, Professor professor, Context ctx) {
        this.delegate = delegate;
        this.periodo = periodo;
        this.professor = professor;
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(ctx, "Atualizando", "Carregando avaliações...", true);
    }

    @Override
    protected ArrayList<Avaliacao> doInBackground(Void... params) {

        ArrayList<Avaliacao> avaliacoes = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            avaliacoes = mapper.readValue(new ProfessorClient().getAvaliacoes(professor, periodo), new TypeReference<ArrayList<Avaliacao>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            this.erro = e;
        }

        return avaliacoes;
    }

    @Override
    protected void onPostExecute(ArrayList<Avaliacao> avaliacoes) {

        if (erro == null) {
            delegate.lidaComAvaliacoes(avaliacoes);
        } else {
            delegate.lidaComErro(erro);
        }
        progress.dismiss();
    }
}
