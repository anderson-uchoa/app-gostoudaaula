package br.com.gostoudaaula.client;

import android.app.DownloadManager;
import android.util.Log;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import br.com.gostoudaaula.model.Avaliacao;

/**
 * Created by alexf on 01/02/16.
 */
public class RespostasClient extends Client {

    public String enviaRespostas(String json, Long id) throws IOException {
        Request request = createRequestForPOST(json, URL + "avaliacao/respostas/" + id);
        Response response = client.newCall(request).execute();
        return response.message();
    }
}
