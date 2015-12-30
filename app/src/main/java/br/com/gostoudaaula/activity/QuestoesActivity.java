package br.com.gostoudaaula.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.helper.QuestoesHelper;
import br.com.gostoudaaula.model.Questoes;
import br.com.gostoudaaula.model.Respostas;

public class QuestoesActivity extends AppCompatActivity {

    private QuestoesHelper helper;
    private Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questoes);
        this.helper = new QuestoesHelper(this);
        this.botao = (Button) findViewById(R.id.questoes_responder);

        Questoes questoes = new Questoes();
        questoes.setDescricao("Questão de teste");
        helper.inserePergunta(questoes);

        helper.inserePergunta(questoes);
        for (String texto : new String[]{"Alex", "Felipe", "Vieira"}) {
            helper.addAlterinativa(texto);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.setResposta();
            }
        });
    }
}

