package br.com.gostoudaaula.task;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;

import br.com.gostoudaaula.client.AlunoClient;
import br.com.gostoudaaula.model.Aluno;

/**
 * Created by alexf on 30/12/15.
 */
public class TesteTask extends AsyncTask<Void, Void, Void> {

    private final Context ctx;

    public TesteTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected Void doInBackground(Void... params) {
        Aluno aluno = new Aluno(13100085);
        aluno.setNome("Brother");
        aluno.setSobrenome("Pereira");
        aluno.setSenha("teste");

        try {
            new AlunoClient().teste2(new Gson().toJson(aluno));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
