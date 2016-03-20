package br.com.gostoudaaula.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import br.com.gostoudaaula.delegate.LoginProfessorDelegate;
import br.com.gostoudaaula.model.Professor;

/**
 * Created by alexf on 11/03/16.
 */
public class LoginProfessorTask extends AsyncTask<Void, Void, Professor> {

    private final Professor professor;
    private final Context ctx;
    private final LoginProfessorDelegate delegate;
    private ProgressDialog progress;
    private Exception erro;

    public LoginProfessorTask(LoginProfessorDelegate delegate, Professor professor, Context ctx) {
        this.ctx = ctx;
        this.professor = professor;
        this.delegate = delegate;
    }


    @Override
    protected void onPreExecute() {
        this.progress = ProgressDialog.show(ctx, "Autenticação", "Aguarde enquanto verificamos...", true);
    }

    @Override
    protected Professor doInBackground(Void... params) {



        return null;
    }

    @Override
    protected void onPostExecute(Professor professor) {

        progress.dismiss();

        if (this.erro != null) {
            this.delegate.trataErros(erro);
        } else {
            this.delegate.carregaTurmasDoProfesor(professor);
        }
    }


}
