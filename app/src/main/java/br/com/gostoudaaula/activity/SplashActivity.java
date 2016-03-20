package br.com.gostoudaaula.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.delegate.LoginAlunoDelegate;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.task.LoginTokenTask;
import br.com.gostoudaaula.utils.AlunoUtils;

import static br.com.gostoudaaula.utils.AlunoUtils.ALUNO_PREFERENCES;
import static br.com.gostoudaaula.utils.AlunoUtils.ALUNO_TOKEN_APP;

/**
 * Created by alexf on 24/02/16.
 */
public class SplashActivity extends AppCompatActivity implements LoginAlunoDelegate {

    private final int TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences(AlunoUtils.ALUNO_PREFERENCES, MODE_PRIVATE);

                if (preferences.contains(AlunoUtils.ALUNO_TOKEN_APP)) {
                    new LoginTokenTask(SplashActivity.this, preferences.getString(AlunoUtils.ALUNO_TOKEN_APP, "")).execute();
                } else {
                    carregaPaginaLogin();
                }
            }
        }, TIME);

    }

    @Override
    public void carregaAulasDoAluno(Aluno aluno) {
        armazenaToken(aluno);
        Intent intent = new Intent(this, ListaAulaActivity.class);
        intent.putExtra("aluno", aluno);
        startActivity(intent);
        finish();
    }


    private void carregaPaginaLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void armazenaToken(Aluno aluno) {
        SharedPreferences preferences = getSharedPreferences(ALUNO_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(ALUNO_TOKEN_APP, aluno.getToken()).commit();
    }

    @Override
    public void trataErros(Exception e) {
        e.printStackTrace();
        carregaPaginaLogin();
    }
}

