package br.com.gostoudaaula.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import br.com.gostoudaaula.client.AvaliacaoClient;
import br.com.gostoudaaula.delegate.AvaliacaoDelegate;
import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.Avaliacao;

/**
 * Created by alexf on 31/01/16.
 */
public class AvaliacaoTask extends AsyncTask<Void, Void, Avaliacao> {


    private final AvaliacaoDelegate delegate;
    private final Aula aula;
    private Exception erro;
    private ProgressDialog progress;
    private Context ctx;


    public AvaliacaoTask(AvaliacaoDelegate delegate, Aula aula, Context ctx) {
        this.delegate = delegate;
        this.aula = aula;
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(ctx, "Atualizando", "Carregando quetões...", true);
    }

    @Override
    protected Avaliacao doInBackground(Void... params) {
        Avaliacao avaliacao = null;

        try {
            avaliacao = new ObjectMapper().readValue(new AvaliacaoClient().getAvaliacaoDeAula(aula), Avaliacao.class);

        } catch (IOException e) {
            erro = e;
            e.printStackTrace();
        }

        return avaliacao;
    }

    @Override
    protected void onPostExecute(Avaliacao avaliacao) {

        progress.dismiss();

        if (erro == null && avaliacao != null) {
            this.delegate.lidaComAvaliacao(avaliacao);
        } else {
            this.delegate.lidaComErro(erro);
        }


    }
}
