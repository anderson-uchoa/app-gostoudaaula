package br.com.gostoudaaula.client;

import android.util.Log;

import br.com.gostoudaaula.model.Aluno;

/**
 * Created by alexf on 23/12/2015.
 */
public class AlunoClient {

    public boolean cadastra(Aluno aluno) {
        Log.i("Aluno", String.valueOf(aluno));
        return true;
    }

    public boolean login(Aluno aluno) {
        return true;
    }
}
