package br.com.gostoudaaula.task;

import android.os.AsyncTask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import br.com.gostoudaaula.client.TokenClient;
import br.com.gostoudaaula.delegate.LoginProfessorDelegate;
import br.com.gostoudaaula.model.Professor;

/**
 * Created by alexf on 22/03/16.
 */
public class LoginProfessorTokenTask extends AsyncTask<Void, Void, Professor> {


    private LoginProfessorDelegate delegate;
    private String token;
    private Exception erro;

    public LoginProfessorTokenTask(LoginProfessorDelegate delegate, String token) {
        this.delegate = delegate;
        this.token = token;
    }

    @Override
    protected Professor doInBackground(Void... params) {

        Professor professor = new Professor();
        professor.setToken(token);

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(professor);
            professor = mapper.readValue(new TokenClient().autenticaProfesso(json), Professor.class);
        } catch (JsonProcessingException e) {
            this.erro = e;
            e.printStackTrace();
        } catch (IOException e) {
            this.erro = e;
            e.printStackTrace();
        }

        return professor;
    }

    @Override
    protected void onPostExecute(Professor professor) {
        if (erro != null) {
            delegate.trataErros(erro);
        } else {
            delegate.carregaTurmasDoProfesor(professor);
        }
    }
}
