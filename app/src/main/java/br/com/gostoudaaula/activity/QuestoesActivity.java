package br.com.gostoudaaula.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.example.AvaliacaoExample;
import br.com.gostoudaaula.fragment.QuestoesFragment;
import br.com.gostoudaaula.helper.QuestoesHelper;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Respostas;

public class QuestoesActivity extends AppCompatActivity {

    private QuestoesHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questoes);


        Bundle bundle = new Bundle();

        Avaliacao avaliacao = new AvaliacaoExample().getFullExample1();

        QuestoesFragment fragment = new QuestoesFragment();

        bundle.putParcelable("avaliacao", avaliacao);
        bundle.putInt("questao_atual", 0);
        bundle.putParcelableArrayList("respostas", new ArrayList<Respostas>());

        fragment.setArguments(bundle);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.questoes_frame_layout, fragment);
        ft.commit();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}

