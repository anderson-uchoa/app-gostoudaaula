package br.com.gostoudaaula.client;

import android.app.DownloadManager;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.Avaliacao;

/**
 * Created by alexf on 31/01/16.
 */
public class AvaliacaoClient extends Client {

    public String getAvaliacaoDeAula(Aula aula) throws IOException {
        Request request = createRequestForGET(URL + "avaliacao/" + aula.getId());
        Response response = client.newCall(request).execute();

        return response.body().string();
    }

    public String avaliaAula(String json, Long id) throws IOException {
        Request request = createRequestForPOST(json, URL + "avaliacao/respondida/" + id);
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
