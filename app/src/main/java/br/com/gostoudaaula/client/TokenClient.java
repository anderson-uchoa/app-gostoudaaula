package br.com.gostoudaaula.client;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by alexf on 21/02/16.
 */
public class TokenClient extends Client {

    public String autenticaAluno(String json) throws IOException {
        Request request = createRequestForPOST(json, URL + "aluno/auth/token");
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String autenticaProfesso(String json) throws IOException {
        Request request = createRequestForPOST(json, URL + "professor/auth/token");
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
