package br.com.gostoudaaula.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

import br.com.gostoudaaula.client.ProfessorClient;
import br.com.gostoudaaula.delegate.ChartDelegate;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Respostas;

/**
 * Created by alexf on 26/03/16.
 */
public class ChartTask extends AsyncTask<Void, Void, ArrayList<Respostas>> {

    private final ChartDelegate delegate;
    private final Avaliacao avaliacao;
    private final Context ctx;
    private Exception erro;
    private ProgressDialog progress;

    public ChartTask(ChartDelegate delegate, Avaliacao avaliacao, Context ctx) {
        this.delegate = delegate;
        this.avaliacao = avaliacao;
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(ctx, "Atualizando", "Carregando respostas...", true);
    }

    @Override
    protected ArrayList<Respostas> doInBackground(Void... params) {

        ArrayList<Respostas> respostas = null;

        ObjectMapper mapper = new ObjectMapper();
        try {
            respostas = mapper.readValue(new ProfessorClient().getRespostas(avaliacao), new TypeReference<ArrayList<Respostas>>() {
            });
        } catch (IOException e) {
            erro = e;
            e.printStackTrace();
        }
        return respostas;
    }

    @Override
    protected void onPostExecute(ArrayList<Respostas> respostas) {
        if (erro == null) {
            this.delegate.carregaGrafico(avaliacao, respostas);
        } else {
            this.delegate.lidaComErro(erro);
        }
        progress.dismiss();
    }
}
