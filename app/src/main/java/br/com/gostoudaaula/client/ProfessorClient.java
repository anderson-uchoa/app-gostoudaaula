package br.com.gostoudaaula.client;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import br.com.gostoudaaula.json.mixin.ProfessorMixIn;
import br.com.gostoudaaula.model.Professor;

/**
 * Created by alexf on 11/03/16.
 */
public class ProfessorClient extends Client {

    public String login(Professor professor) throws IOException {
        String json = mapper.writeValueAsString(professor);

        Request request = createRequestForPOST(json, URL + "professor/auth");
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String getTurmas(Professor professor) throws IOException {
        mapper.addMixIn(Professor.class, ProfessorMixIn.AssociationMixIn.class);
        String json = mapper.writeValueAsString(professor);
        Request request = createRequestForPOST(json, URL + "professor/turmas/" + professor.getId());
        Response response = client.newCall(request).execute();

        return response.body().string();
    }
}
