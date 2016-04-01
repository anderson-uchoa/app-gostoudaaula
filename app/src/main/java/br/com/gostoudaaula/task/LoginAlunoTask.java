package br.com.gostoudaaula.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gostoudaaula.client.AlunoClient;
import br.com.gostoudaaula.delegate.LoginAlunoDelegate;
import br.com.gostoudaaula.model.Aluno;

/**
 * Created by alexf on 05/01/16.
 */
public class LoginAlunoTask extends AsyncTask<Void, Void, Aluno> {

    private final LoginAlunoDelegate delegate;
    private final Context ctx;
    private Aluno aluno;
    private Exception erro;
    private ProgressDialog progress;

    public LoginAlunoTask(LoginAlunoDelegate delegate, Aluno aluno, Context ctx) {
        this.ctx = ctx;
        this.delegate = delegate;
        this.aluno = aluno;
    }

    @Override
    protected void onPreExecute() {
        this.progress = ProgressDialog.show(ctx, "Autenticação", "Aguarde enquanto verificamos...", true);
    }

    @Override
    protected Aluno doInBackground(Void... params) {


        try {
            this.aluno = new ObjectMapper().readValue(new AlunoClient().login(this.aluno), Aluno.class);
        } catch (Exception e) {
            this.erro = e;
        }

        return aluno;
    }

    @Override
    protected void onPostExecute(Aluno aluno) {

        progress.dismiss();

        if (this.erro != null) {
            this.delegate.lidaComErro(erro);
        } else {
            this.delegate.carregaAulasDoAluno(aluno);
        }
    }
}
