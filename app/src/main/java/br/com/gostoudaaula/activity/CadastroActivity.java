package br.com.gostoudaaula.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.delegate.CadastroAlunoDelegate;
import br.com.gostoudaaula.helper.CadastroHelper;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.task.CadastroTask;

public class CadastroActivity extends AppCompatActivity implements CadastroAlunoDelegate {

    private CadastroHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        helper = new CadastroHelper(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_done:
                Aluno aluno = helper.constroi();

                new CadastroTask(CadastroActivity.this, aluno, CadastroActivity.this).execute();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void trataErro(Exception e) {
        e.printStackTrace();
        Toast.makeText(CadastroActivity.this, "Cadastro ja existe", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void confirmacaoDeCadastrado() {
        Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso", Toast.LENGTH_SHORT).show();
        finish();
    }
}
