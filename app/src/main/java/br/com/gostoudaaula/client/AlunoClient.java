package br.com.gostoudaaula.client;

import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

import br.com.gostoudaaula.model.Aluno;

/**
 * Created by alexf on 23/12/2015.
 */
public class AlunoClient {

    private final String URL = "http://10.0.2.2:8080/gostoudaaula-web/services/aluno";
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client = new OkHttpClient();

    public boolean cadastra(Aluno aluno) {
        Log.i("Aluno", String.valueOf(aluno));
        return true;
    }

    public boolean login(Aluno aluno) {
        return true;
    }

    public String teste() throws IOException {
        Request request = new Request.Builder().url(URL).build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String teste2(String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
