package br.com.gostoudaaula.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.com.gostoudaaula.delegate.LoginAlunoDelegate;
import br.com.gostoudaaula.R;
import br.com.gostoudaaula.helper.LoginHelper;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.task.LoginTask;

public class LoginAlunoActivity extends AppCompatActivity implements LoginAlunoDelegate {

    private LoginHelper helper;
    private Button botaoLogin;
    private TextView cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.helper = new LoginHelper(this);
        this.botaoLogin = (Button) findViewById(R.id.login_botao);
        this.cadastro = (TextView) findViewById(R.id.login_cadastro);
    }

    @Override
    protected void onResume() {
        super.onResume();


        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginTask(LoginAlunoActivity.this, helper.constroi(), LoginAlunoActivity.this).execute();
            }

        });

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginAlunoActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });


    }

    public void loga(Aluno aluno) {
        Intent intent = new Intent(this, ListaAulaActivity.class);
        intent.putExtra("aluno", aluno);
        startActivity(intent);
    }

    @Override
    public void carregaPaginaPrincipal(Aluno aluno) {
        Log.i("aluno: ", String.valueOf(aluno));
        Intent intent = new Intent(this, ListaAulaActivity.class);
        intent.putExtra("aluno", aluno);
        startActivity(intent);
    }

    @Override
    public void trataErros(Exception e) {
        e.printStackTrace();
        Toast.makeText(LoginAlunoActivity.this, "Problema de autentiçao", Toast.LENGTH_SHORT).show();
    }
}
