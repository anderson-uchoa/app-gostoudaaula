package br.com.gostoudaaula.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gostoudaaula.delegate.LoginAlunoDelegate;
import br.com.gostoudaaula.client.AlunoClient;
import br.com.gostoudaaula.model.Aluno;

/**
 * Created by alexf on 05/01/16.
 */
public class LoginTask extends AsyncTask<Void, Void, Void> {

    private Aluno aluno;
    private final LoginAlunoDelegate delegate;
    private final Context ctx;
    private Exception erro;
    private ProgressDialog progress;

    public LoginTask(LoginAlunoDelegate delegate, Aluno aluno, Context ctx) {
        this.ctx = ctx;
        this.delegate = delegate;
        this.aluno = aluno;
    }

    @Override
    protected void onPreExecute() {
        this.progress = ProgressDialog.show(ctx, "Autenticação", "Aguarde enquanto verificamos...", true);
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            this.aluno = new ObjectMapper().readValue(new AlunoClient().login(this.aluno), Aluno.class);
        } catch (Exception e) {
            this.erro = e;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void param) {

        progress.dismiss();

        if (this.erro != null) {
            this.delegate.trataErros(erro);
        } else {
            this.delegate.carregaPaginaPrincipal(aluno);
        }
    }
}
