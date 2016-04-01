package br.com.gostoudaaula.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

import br.com.gostoudaaula.client.ProfessorClient;
import br.com.gostoudaaula.delegate.ListaTurmaDelegate;
import br.com.gostoudaaula.model.Professor;
import br.com.gostoudaaula.model.Turma;

/**
 * Created by alexf on 22/03/16.
 */
public class ListaTurmaTask extends AsyncTask<Void, Void, ArrayList<Turma>> {


    private final Professor professor;
    private final Context ctx;
    private ListaTurmaDelegate delegate;
    private ProgressDialog progress;
    private Exception erro;


    public ListaTurmaTask(ListaTurmaDelegate delegate, Professor professor, Context ctx) {
        this.delegate = delegate;
        this.professor = professor;
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        this.progress = ProgressDialog.show(ctx, "Aguarge", "Carregando turmas", true);

    }

    @Override
    protected ArrayList<Turma> doInBackground(Void... params) {
        ArrayList<Turma> turmas = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            turmas = mapper.readValue(new ProfessorClient().getTurmas(professor), new TypeReference<ArrayList<Turma>>() {
            });
        } catch (IOException e) {
            this.erro = e;
            e.printStackTrace();
        }
        return turmas;
    }

    @Override
    protected void onPostExecute(ArrayList<Turma> turmas) {

        if (erro == null) {
            delegate.lidaComTurmas(turmas);
        } else {
            delegate.lidaComErro(erro);
        }
        progress.dismiss();
    }
}
