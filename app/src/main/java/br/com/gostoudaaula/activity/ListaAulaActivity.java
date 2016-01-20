package br.com.gostoudaaula.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.adapter.ListaAulasAdapter;
import br.com.gostoudaaula.delegate.AulasDoAlunoDelegate;
import br.com.gostoudaaula.helper.ListaAulaHelper;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Aula;
import br.com.gostoudaaula.task.AulasDoAlunoTask;

public class ListaAulaActivity extends AppCompatActivity implements AulasDoAlunoDelegate {

    private ListaAulaHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aula);

        this.helper = new ListaAulaHelper(this);
        Aluno aluno = null;

        if (getIntent().hasExtra("aluno")) {
            aluno = (Aluno) getIntent().getSerializableExtra("aluno");
        } else {
            finish();
        }
        
        new AulasDoAlunoTask(this, aluno, this).execute();
    }

    @Override
    public void populaListaDeAulas(List<Aula> aulas) {
        ListaAulasAdapter adapter = new ListaAulasAdapter(this, aulas);
        helper.getLista().setAdapter(adapter);
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
}
