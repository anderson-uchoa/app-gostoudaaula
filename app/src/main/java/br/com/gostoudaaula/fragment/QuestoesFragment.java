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

import org.joda.time.LocalDate;

import java.util.ArrayList;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.delegate.QuestoesDelegate;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Questoes;
import br.com.gostoudaaula.model.Respostas;

/**
 * Created by alexf on 25/01/16.
 */
public class QuestoesFragment extends Fragment {

    private int total;
    private int questaoAtual;
    private ArrayList<Questoes> questoes;
    private ArrayList<Respostas> respostas;
    private int valueRating;
    private Avaliacao avaliacao;
    private QuestoesDelegate delegate;

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
        if (respostas == null) {
            respostas = new ArrayList<>();
        } else if (respostas.size() == questaoAtual + 1) {
            this.valueRating = respostas.get(questaoAtual).getResposta();
        }

        this.delegate = (QuestoesDelegate) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {

        setup(getArguments());

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
                respostaAtual.setData(LocalDate.now());
                respostas.add(respostaAtual);
                if (!proximaQuestao()) {
                    delegate.enviaRespostas(respostas);
                }
            }
        });

        return view;
    }

    private void populaViews() {
        pergunta.setText(questao.getDescricao());
        quantidade.setText(this.questaoAtual + 1 + " de " + total);
    }

    // TODO: 16/03/16 mudar para ButterKnife
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
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.delegate.mantemStatus(this.avaliacao, this.questaoAtual, this.respostas);
    }


}

