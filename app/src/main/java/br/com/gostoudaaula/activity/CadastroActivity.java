package br.com.gostoudaaula.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.client.AlunoClient;
import br.com.gostoudaaula.helper.CadastroHelper;
import br.com.gostoudaaula.model.Aluno;

public class CadastroActivity extends AppCompatActivity {

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
                AlunoClient client = new AlunoClient();
                client.cadastra(aluno);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
