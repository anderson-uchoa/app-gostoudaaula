package br.com.gostoudaaula.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import br.com.gostoudaaula.client.AvaliacaoClient;
import br.com.gostoudaaula.client.RespostasClient;
import br.com.gostoudaaula.delegate.QuestoesDelegate;
import br.com.gostoudaaula.json.mixin.AvaliacaoMixIn;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Respostas;

/**
 * Created by alexf on 01/02/16.
 */
public class RespostasTask extends AsyncTask<Void, Void, Void> {

    private final QuestoesDelegate delegate;
    private final List<Respostas> respostas;
    private final Context context;
    private final Aula aula;
    private final Aluno aluno;
    private final Avaliacao avaliacao;
    private ProgressDialog progress;
    private Exception erro;


    public RespostasTask(QuestoesDelegate delegate, Avaliacao avaliacao,
                         List<Respostas> respostas, Aluno aluno, Context context) {
        this.delegate = delegate;
        this.respostas = respostas;
        this.aula = avaliacao.getAula();
        this.avaliacao = avaliacao;
        this.context = context;
        this.aluno = aluno;
    }

    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(context, "Enviando respostas", "Aguarde...", true);
    }

    @Override
    protected Void doInBackground(Void... params) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(Avaliacao.class, AvaliacaoMixIn.AssociationMixIn.class);

        try {
            String json = mapper.writeValueAsString(respostas);
            new RespostasClient().enviaRespostas(json, avaliacao.getId());
            new AvaliacaoClient().avaliaAula(mapper.writeValueAsString(avaliacao), aluno.getId());
        } catch (IOException e) {
            erro = e;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        progress.dismiss();

        if (erro != null) {
            delegate.lidaComErro(erro);
        } else {
            delegate.avaliacaoRespondida();
        }
    }
}
