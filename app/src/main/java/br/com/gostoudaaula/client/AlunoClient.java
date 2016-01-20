package br.com.gostoudaaula.client;

import android.util.Log;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import br.com.gostoudaaula.model.Aluno;

public class AlunoClient extends Client {


    public String login(Aluno aluno) throws IOException {

        String json = mapper.writeValueAsString(aluno);
        Request request = createRequestForPOST(json, URL + "aluno/auth");
        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    public boolean cadastra(Aluno aluno) throws IOException {
        Request request = createRequestForPOST(mapper.writeValueAsString(aluno), URL + "aluno");
        Response response = client.newCall(request).execute();

        return response.isSuccessful();

    }


}
