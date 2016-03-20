package br.com.gostoudaaula.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.gostoudaaula.delegate.DisciplinaDelegate;
import br.com.gostoudaaula.model.Disciplina;
import br.com.gostoudaaula.model.Turma;

/**
 * Created by alexf on 16/03/16.
 */
public class DisciplinaTask extends AsyncTask<Void, Void, ArrayList<Disciplina>> {

    private DisciplinaDelegate delegate;
    private Turma turma;
    private Context ctx;
    private Exception erro;
    private ProgressDialog progress;

    public DisciplinaTask(DisciplinaDelegate delegate, Turma turma, Context ctx) {
        this.delegate = delegate;
        this.turma = turma;
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(ctx, "Pesquisando", "Carregando disciplinas...", true);
    }

    @Override
    protected ArrayList<Disciplina> doInBackground(Void... params) {

        ArrayList<Disciplina> disciplinas = getDisciplinas();

        return disciplinas;
    }

    @Override
    protected void onPostExecute(ArrayList<Disciplina> disciplina) {

        if (erro == null) {
            delegate.lidaComDisciplina(disciplina);
        } else {
            delegate.lidaComErro(erro);
        }

        progress.dismiss();

    }

    @NonNull
    private ArrayList<Disciplina> getDisciplinas() {
        ArrayList<Disciplina> disciplinas = new ArrayList<>();
        Disciplina disciplina1 = new Disciplina();
        Disciplina disciplina2 = new Disciplina();
        disciplina1.setId(1L);
        disciplina1.setDescricao("Programaçao");
        disciplina2.setId(2L);
        disciplina2.setDescricao("Banco de dados");

        disciplinas.addAll(Arrays.asList(disciplina1, disciplina2));
        return disciplinas;
    }
}
