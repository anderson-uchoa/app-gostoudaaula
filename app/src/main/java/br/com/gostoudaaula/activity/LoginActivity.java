package br.com.gostoudaaula.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.helper.LoginHelper;
import br.com.gostoudaaula.model.Aluno;

public class LoginActivity extends AppCompatActivity {

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
                Intent intent = new Intent(LoginActivity.this, QuestoesActivity.class);
                startActivity(intent);


            }
        });

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

    }
}
