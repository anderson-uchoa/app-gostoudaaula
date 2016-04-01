package br.com.gostoudaaula.task;

import android.content.Context;
import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import br.com.gostoudaaula.client.AlunoClient;
import br.com.gostoudaaula.delegate.CadastroAlunoDelegate;
import br.com.gostoudaaula.model.Aluno;

/**
 * Created by alexf on 06/01/16.
 */
public class CadastroTask extends AsyncTask<Void, Void, Boolean> {

    private final CadastroAlunoDelegate delegate;
    private final Context ctx;
    private final Aluno aluno;
    private Exception erro;

    public CadastroTask(CadastroAlunoDelegate delegate, Aluno aluno, Context ctx) {
        this.ctx = ctx;
        this.aluno = aluno;
        this.delegate = delegate;
    }

    @Override
    protected Boolean doInBackground(Void... params) {


        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(aluno);
            return new AlunoClient().cadastra(json);
        } catch (IOException e) {
            this.erro = e;
        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean confirmacao) {

        if (confirmacao) {
            delegate.confirmacaoDeCadastrado();
        } else {
            delegate.lidaComErro(erro);
        }

    }
}
