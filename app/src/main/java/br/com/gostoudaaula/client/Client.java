package br.com.gostoudaaula.client;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

public abstract class Client {

    //protected final String URL = "http://10.0.2.2:8080/gostoudaaula-web/";
    protected final String URL = "http://unifieo-plsnotnow.rhcloud.com/";
    protected static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
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

    protected Request createRequestForGet(String url) {
        Log.i("url get", url);
        return new Request.Builder().url(url).build();
    }

}
