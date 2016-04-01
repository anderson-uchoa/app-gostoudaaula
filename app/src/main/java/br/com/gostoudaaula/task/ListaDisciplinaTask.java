package br.com.gostoudaaula.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

import br.com.gostoudaaula.client.ProfessorClient;
import br.com.gostoudaaula.delegate.ListaDisciplinaDelegate;
import br.com.gostoudaaula.model.Disciplina;
import br.com.gostoudaaula.model.PeriodoLetivo;
import br.com.gostoudaaula.model.Professor;

/**
 * Created by alexf on 16/03/16.
 */
public class ListaDisciplinaTask extends AsyncTask<Void, Void, ArrayList<Disciplina>> {


    private ListaDisciplinaDelegate delegate;
    private PeriodoLetivo periodo;
    private Professor professor;
    private Context ctx;
    private Exception erro;
    private ProgressDialog progress;

    public ListaDisciplinaTask(ListaDisciplinaDelegate delegate, PeriodoLetivo periodo, Professor professor, Context ctx) {
        this.delegate = delegate;
        this.periodo = periodo;
        this.professor = professor;
        this.ctx = ctx;
    }


    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(ctx, "Pesquisando", "Carregando disciplinas...", true);
    }

    @Override
    protected ArrayList<Disciplina> doInBackground(Void... params) {

        ArrayList<Disciplina> disciplinas = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            disciplinas = mapper.readValue(new ProfessorClient().getDisciplinas(professor, periodo.getTurma()),
                    new TypeReference<ArrayList<Disciplina>>() {
                    });
        } catch (IOException e) {
            this.erro = e;
        }

        return disciplinas;
    }

    @Override
    protected void onPostExecute(ArrayList<Disciplina> disciplinas) {

        if (erro == null) {
            delegate.lidaComDisciplina(disciplinas, periodo);
        } else {
            delegate.lidaComErro(erro);
        }

        progress.dismiss();

    }

}
