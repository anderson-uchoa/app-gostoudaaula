package br.com.gostoudaaula.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gostoudaaula.client.TokenClient;
import br.com.gostoudaaula.delegate.LoginAlunoDelegate;
import br.com.gostoudaaula.model.Aluno;

/**
 * Created by alexf on 21/02/16.
 */
public class LoginAlunoTokenTask extends AsyncTask<Void, Void, Aluno> {


    private final LoginAlunoDelegate delegate;
    private final String token;
    private Exception erro;

    public LoginAlunoTokenTask(LoginAlunoDelegate delegate, String token) {
        this.delegate = delegate;
        this.token = token;
    }

    @Override
    protected Aluno doInBackground(Void... params) {

        Aluno aluno = new Aluno();
        aluno.setToken(token);

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(aluno);
            aluno = mapper.readValue(new TokenClient().autenticaAluno(json), Aluno.class);
        } catch (Exception e) {
            this.erro = e;
            e.printStackTrace();
        }

        if (aluno.getId() != null)
            return aluno;

        return null;
    }

    @Override
    protected void onPostExecute(Aluno aluno) {
        if (erro != null) {
            delegate.trataErros(erro);
        } else {
            delegate.carregaAulasDoAluno(aluno);
        }
    }
}
