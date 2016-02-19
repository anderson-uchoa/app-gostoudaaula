package br.com.gostoudaaula.client;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import br.com.gostoudaaula.model.Aluno;

/**
 * Created by alexf on 09/01/16.
 */
public class AulaClient extends Client {

    public String getAulasSemAvaliacao(Aluno aluno) throws IOException {
        Request request = createRequestForGET(URL + "aula/" + aluno.getProntuario());
        Response response = client.newCall(request).execute();

        return response.body().string();
    }
}
