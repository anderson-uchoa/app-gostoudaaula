package br.com.gostoudaaula.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.delegate.LoginAlunoDelegate;
import br.com.gostoudaaula.delegate.LoginProfessorDelegate;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Professor;
import br.com.gostoudaaula.task.LoginAlunoTokenTask;
import br.com.gostoudaaula.task.LoginProfessorTokenTask;
import br.com.gostoudaaula.utils.TokenUtils;

import static br.com.gostoudaaula.utils.AlunoUtils.ALUNO_PREFERENCES;
import static br.com.gostoudaaula.utils.AlunoUtils.ALUNO_TOKEN_APP;
import static br.com.gostoudaaula.utils.ProfessorUtils.PROFESSOR_PREFENCES;
import static br.com.gostoudaaula.utils.ProfessorUtils.PROFESSOR_TOKEN_APP;

/**
 * Created by alexf on 24/02/16.
 */
public class SplashActivity extends AppCompatActivity implements LoginAlunoDelegate, LoginProfessorDelegate {

    private final int TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences alunoPreferences = getSharedPreferences(ALUNO_PREFERENCES, MODE_PRIVATE);
                SharedPreferences professorPreferences = getSharedPreferences(PROFESSOR_PREFENCES, MODE_PRIVATE);


                if (alunoPreferences.contains(ALUNO_TOKEN_APP)) {
                    new LoginAlunoTokenTask(SplashActivity.this, alunoPreferences.getString(ALUNO_TOKEN_APP, "")).execute();
                } else if (professorPreferences.contains((PROFESSOR_TOKEN_APP))) {
                    new LoginProfessorTokenTask(SplashActivity.this, professorPreferences.getString(PROFESSOR_TOKEN_APP, "")).execute();
                } else {
                    carregaPaginaLogin();
                }
            }
        }, TIME);

    }

    private void carregaPaginaLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void trataErros(Exception e) {
        e.printStackTrace();
        carregaPaginaLogin();
    }

    @Override
    public void carregaTurmasDoProfesor(Professor professor) {
        new TokenUtils(this).armazenaToken(professor);
        Intent intent = new Intent(this, ListaTurmasActivity.class);
        intent.putExtra("professor", professor);
        startActivity(intent);
        finish();
    }

    @Override
    public void carregaAulasDoAluno(Aluno aluno) {
        new TokenUtils(this).armazenaToken(aluno);
        Intent intent = new Intent(this, ListaAulaActivity.class);
        intent.putExtra("aluno", aluno);
        startActivity(intent);
        finish();
    }
}

