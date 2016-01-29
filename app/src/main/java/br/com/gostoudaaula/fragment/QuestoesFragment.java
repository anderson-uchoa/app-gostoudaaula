package br.com.gostoudaaula.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Questoes;
import br.com.gostoudaaula.model.Respostas;

/**
 * Created by alexf on 25/01/16.
 */
public class QuestoesFragment extends Fragment {

    private Integer total;
    private Integer questaoAtual;
    private ArrayList<Questoes> questoes;
    private ArrayList<Respostas> respostas;
    private Integer valueRating;
    private Avaliacao avaliacao;

    private TextView pergunta;
    private RatingBar rating;
    private Button button;
    private TextView quantidade;
    private Questoes questao;

    private void setup(Bundle bundle) {
        this.avaliacao = bundle.getParcelable("avaliacao");
        this.questoes = (ArrayList<Questoes>) avaliacao.getProjeto().getQuestoes();
        this.total = questoes.size();
        this.questaoAtual = bundle.getInt("questao_atual");
        this.questao = questoes.get(questaoAtual);
        this.respostas = bundle.getParcelableArrayList("respostas");
        this.valueRating = 0;
        Log.i("chamada esse cara aqui", "agora");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {

        Log.i("chama o oncreateView", "agora");

        if (avaliacao == null)

            if (avaliacao == null) {
                setup(getArguments());
            }

        View view = inflater.inflate(R.layout.fragment_questoes, container, false);

        carregaViews(view);

        populaViews();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueRating = rating.getProgress();
                Respostas respostaAtual = new Respostas();
                respostaAtual.setResposta(valueRating);
                respostaAtual.setQuestoes(questoes.get(questaoAtual));
                respostaAtual.setAvaliacao(avaliacao);
                respostas.add(respostaAtual);

                if (!proximaQuestao()) {

                    Log.i("avaliacao: ", String.valueOf(avaliacao.getData()));
                    Log.i("projeto: ", String.valueOf(avaliacao.getProjeto()));
                    for (Respostas r : respostas) {
                        Log.i("questao: ", r.getQuestoes().getDescricao());
                        Log.i("resposta: ", String.valueOf(r.getResposta()));
                    }
                }
            }
        });

        return view;
    }

    private void populaViews() {
        pergunta.setText(questao.getDescricao());
        quantidade.setText(this.questaoAtual + 1 + " de " + total);
    }

    private void carregaViews(View view) {
        this.quantidade = (TextView) view.findViewById(R.id.questoes_quantidade);
        this.pergunta = (TextView) view.findViewById(R.id.questoes_pergunta);
        this.rating = (RatingBar) view.findViewById(R.id.questoes_resposta);
        this.button = (Button) view.findViewById(R.id.questoes_botao_responder);
    }

    private boolean proximaQuestao() {
        if (++questaoAtual < total) {
            questao = questoes.get(questaoAtual);
            rating.setProgress(0);
            populaViews();
            return true;
        }
        return false;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            valueRating = savedInstanceState.getInt("value_rating");
            questaoAtual = savedInstanceState.getInt("questao_atual");
            questao = savedInstanceState.getParcelable("questao");
            avaliacao = savedInstanceState.getParcelable("avaliacao");
            Log.i("chama activity", questao.getDescricao());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("avaliacao", avaliacao);
        bundle.putParcelable("questao", this.questao);
        bundle.putInt("questao_atual", questaoAtual);
        bundle.putInt("value_rating", valueRating);
    }

}

