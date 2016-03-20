package br.com.gostoudaaula.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.gostoudaaula.client.AulaClient;
import br.com.gostoudaaula.delegate.AulasDoAlunoDelegate;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Aula;

/**
 * Created by alexf on 09/01/16.
 */
public class AulasDoAlunoTask extends AsyncTask<Void, Void, List<Aula>> {

    private final Aluno aluno;
    private final Context ctx;
    private final AulasDoAlunoDelegate delegate;
    private ProgressDialog progress;
    private boolean mostraProgress;

    public AulasDoAlunoTask(AulasDoAlunoDelegate delegate, Aluno aluno, Context ctx, boolean mostraProgress) {
        this.aluno = aluno;
        this.ctx = ctx;
        this.delegate = delegate;
        this.mostraProgress = mostraProgress;
    }

    @Override
    protected List<Aula> doInBackground(Void... params) {

        List<Aula> aulas = new ArrayList<>();

        try {
            aulas = new ObjectMapper().readValue(new AulaClient().getAulasSemAvaliacao(aluno),
                    new TypeReference<ArrayList<Aula>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return aulas;
    }

    @Override
    protected void onPostExecute(List<Aula> aulas) {


        if (mostraProgress) {
            progress.dismiss();
        } else {
            delegate.encerraSwipe();
        }

        if (!aulas.isEmpty()) {
            delegate.populaListaDeAulas(aulas);
        } else {
            delegate.alertSemAulas();
        }
    }

    @Override
    protected void onPreExecute() {
        if (mostraProgress) {
            this.progress = ProgressDialog.show(this.ctx, "Atualizando", "Verificando aulas disponíveis...", true);

        }
    }
}
