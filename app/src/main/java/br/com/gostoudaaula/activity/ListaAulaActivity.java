package br.com.gostoudaaula.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.adapter.ListaAulasAdapter;
import br.com.gostoudaaula.delegate.AulasDoAlunoDelegate;
import br.com.gostoudaaula.delegate.AvaliacaoDelegate;
import br.com.gostoudaaula.helper.ListaAulaHelper;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.task.AulasDoAlunoTask;
import br.com.gostoudaaula.task.AvaliacaoTask;

public class ListaAulaActivity extends AppCompatActivity implements AulasDoAlunoDelegate, AvaliacaoDelegate {

    private ListaAulaHelper helper;
    private ListView listaDeAulas;
    private Avaliacao avaliacao;
    private Aluno aluno;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aula);

        this.helper = new ListaAulaHelper(this);
        this.listaDeAulas = helper.listaDeAulas();


        if (getIntent().hasExtra("aluno")) {
            this.aluno = (Aluno) getIntent().getParcelableExtra("aluno");
        } else {
            finish();
        }

        new AulasDoAlunoTask(this, aluno, this, true).execute();

        listaDeAulas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aula aula = (Aula) parent.getItemAtPosition(position);
                new AvaliacaoTask(ListaAulaActivity.this, aula, ListaAulaActivity.this).execute();
            }
        });

        this.helper.getSwipe().setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new AulasDoAlunoTask(ListaAulaActivity.this, aluno, ListaAulaActivity.this, false).execute();
            }
        });
    }

    @Override
    public void populaListaDeAulas(List<Aula> aulas) {
        ListaAulasAdapter adapter = new ListaAulasAdapter(this, aulas);
        helper.getLista().setAdapter(adapter);
    }

    @Override
    public void lidaComAvaliacao(Avaliacao avaliacao) {
        Intent intent = new Intent(ListaAulaActivity.this, QuestoesActivity.class);
        intent.putExtra("avaliacao", avaliacao);
        intent.putExtra("aluno", aluno);
        startActivity(intent);
        finish();
    }



    @Override
    public void trataErro(Exception e) {
        e.printStackTrace();
        Toast.makeText(ListaAulaActivity.this, "Erro ao carregar as aulas", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void alertSemAulas() {
        Toast.makeText(ListaAulaActivity.this, "Nao ha aulas para serem avaliadas", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void encerraSwipe() {
        finalizaSwipe();
    }

    public void finalizaSwipe(){
        this.helper.finalizaSwipe();
    }

}
