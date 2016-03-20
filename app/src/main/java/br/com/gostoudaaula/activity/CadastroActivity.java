package br.com.gostoudaaula.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.delegate.CadastroAlunoDelegate;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.task.CadastroTask;
import butterknife.Bind;
import butterknife.ButterKnife;

public class CadastroActivity extends AppCompatActivity implements CadastroAlunoDelegate {

    @Bind(R.id.cadastro_nome)
    EditText nome;
    @Bind(R.id.cadastro_sobrenome)
    EditText sobrenome;
    @Bind(R.id.cadastro_senha)
    EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        ButterKnife.bind(this);
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
                Aluno aluno = constroi();

                new CadastroTask(CadastroActivity.this, aluno, CadastroActivity.this).execute();

        }

        return super.onOptionsItemSelected(item);
    }

    private Aluno constroi() {
        Aluno aluno = new Aluno();
        aluno.setNome(String.valueOf(nome.getText()));
        aluno.setSobrenome(String.valueOf(sobrenome.getText()));
        aluno.setSenha(String.valueOf(senha.getText()));
        return aluno;
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
