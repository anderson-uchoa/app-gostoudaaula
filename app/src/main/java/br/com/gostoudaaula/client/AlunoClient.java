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

    public boolean cadastra(String json) throws IOException {
        Request request = createRequestForPOST(json, URL + "aluno");
        Response response = client.newCall(request).execute();

        Log.i("codigo da req", String.valueOf(response.code()));
        return response.isSuccessful();
    }


}
