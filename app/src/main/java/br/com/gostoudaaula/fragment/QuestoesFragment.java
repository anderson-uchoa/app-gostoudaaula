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
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by alexf on 25/01/16.
 */
public class QuestoesFragment extends Fragment {

    @Bind(R.id.questoes_pergunta)
    TextView pergunta;
    @Bind(R.id.questoes_resposta)
    RatingBar rating;
    @Bind(R.id.questoes_botao_responder)
    Button button;
    @Bind(R.id.questoes_quantidade)
    TextView quantidade;
    private int total;
    private int questaoAtual;
    private ArrayList<Questoes> questoes;
    private ArrayList<Respostas> respostas;
    private int valueRating;
    private Avaliacao avaliacao;
    private QuestoesDelegate delegate;
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
        setTitle("Questões");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {

        setup(getArguments());

        View view = inflater.inflate(R.layout.fragment_questoes, container, false);

        ButterKnife.bind(this, view);

        populaViews();

        return view;
    }

    private void populaViews() {
        pergunta.setText(questao.getDescricao());
        quantidade.setText(this.questaoAtual + 1 + " de " + total);
    }

    private void proximaQuestao() {
        this.delegate.proximaQuestao(this.avaliacao, this.respostas);
    }

    @OnClick(R.id.questoes_botao_responder)
    public void responde() {
        insereResposta();
        if (questaoAtual + 1 < total)
            proximaQuestao();
        else
            delegate.enviaRespostas(respostas);
    }

    private void insereResposta() {
        valueRating = rating.getProgress();
        Respostas respostaAtual = new Respostas();
        respostaAtual.setResposta(valueRating);
        respostaAtual.setQuestoes(questoes.get(questaoAtual));
        respostaAtual.setData(LocalDate.now());
        respostas.add(respostaAtual);
    }

    private void setTitle(String title) {
        getActivity().setTitle(title);
    }

}

