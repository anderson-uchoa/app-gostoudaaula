package br.com.gostoudaaula.client;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public abstract class Client {

    protected static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    //    protected final String URL = "http://10.0.2.2:8080/gostoudaaula-web/";
    protected final String URL = "http://unifieo-plsnotnow.rhcloud.com/";
    protected OkHttpClient client = new OkHttpClient();
    protected ObjectMapper mapper = new ObjectMapper();


    protected Request createRequestForPOST(String json, String url) {
        RequestBody body = RequestBody.create(JSON, json);
        Log.i("url post", url);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return request;
    }

    protected Request createRequestForGET(String url) {
        Log.i("url get", url);
        return new Request.Builder().url(url).build();
    }

    public Response send(Request request) throws IOException {
        return client.newCall(request).execute();
    }

}
