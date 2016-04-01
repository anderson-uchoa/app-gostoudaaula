package br.com.gostoudaaula.client;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import br.com.gostoudaaula.dto.PeriodoDTO;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Professor;
import br.com.gostoudaaula.model.Turma;

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
        Request request = createRequestForGET(URL + "professor/turmas/" + professor.getId());
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String getDisciplinas(Professor professor, Turma turma) throws IOException {
        String json = mapper.writeValueAsString(turma);
        Request request = createRequestForPOST(json, URL + "professor/disciplinas/" + professor.getId());
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    public String getAvaliacoes(Professor professor, PeriodoDTO periodo) throws IOException {
        String json = mapper.writeValueAsString(periodo);
        Request request = createRequestForPOST(json, URL + "professor/avaliacoes/" + professor.getId());
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String getRespostas(Avaliacao avaliacao) throws IOException {
        Request request = createRequestForGET(URL + "avaliacao/respostas/" + avaliacao.getId());
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}

