package br.com.gostoudaaula.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.adapter.ListaAulasAdapter;
import br.com.gostoudaaula.delegate.AulasDoAlunoDelegate;
import br.com.gostoudaaula.delegate.AvaliacaoDelegate;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.task.AulasDoAlunoTask;
import br.com.gostoudaaula.task.AvaliacaoTask;
import br.com.gostoudaaula.utils.TokenUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class ListaAulaActivity extends AppCompatActivity implements AulasDoAlunoDelegate, AvaliacaoDelegate {

    @Bind(R.id.lista_aula_swipe)
    SwipeRefreshLayout swipe;
    @Bind(R.id.lista_aula_aulas)
    ListView listaDeAulas;
    private Aluno aluno;
    private Avaliacao avaliacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aula);
        ButterKnife.bind(this);

        if (getIntent().hasExtra("aluno")) {
            aluno = (Aluno) getIntent().getParcelableExtra("aluno");
        } else {
            finish();
        }

        Log.i("id do aluno", String.valueOf(aluno.getId()));
        new AulasDoAlunoTask(this, aluno, this, true).execute();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new AulasDoAlunoTask(ListaAulaActivity.this, aluno, ListaAulaActivity.this, false).execute();
            }
        });
    }

    @OnItemClick(R.id.lista_aula_aulas)
    public void avaliaAula(int position) {
        Aula aula = (Aula) listaDeAulas.getItemAtPosition(position);
        new AvaliacaoTask(ListaAulaActivity.this, aula, ListaAulaActivity.this).execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista_aulas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_lista_aula_logout:
                new TokenUtils(this).deslogaAluno();
                Intent intent = new Intent(this, SplashActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void populaListaDeAulas(List<Aula> aulas) {
        ListaAulasAdapter adapter = new ListaAulasAdapter(this, aulas);
        listaDeAulas.setAdapter(adapter);
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
        this.swipe.setRefreshing(false);
        this.swipe.clearAnimation();
    }


}
