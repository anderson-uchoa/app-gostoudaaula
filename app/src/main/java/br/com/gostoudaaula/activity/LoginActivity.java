package br.com.gostoudaaula.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.delegate.LoginAlunoDelegate;
import br.com.gostoudaaula.delegate.LoginProfessorDelegate;
import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Professor;
import br.com.gostoudaaula.task.LoginAlunoTask;
import br.com.gostoudaaula.task.LoginProfessorTask;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static br.com.gostoudaaula.utils.AlunoUtils.ALUNO_PREFERENCES;
import static br.com.gostoudaaula.utils.AlunoUtils.ALUNO_TOKEN_APP;
import static br.com.gostoudaaula.utils.ProfessorUtils.PROFESSOR_PREFENCES;
import static br.com.gostoudaaula.utils.ProfessorUtils.PROFESSOR_TOKEN_APP;


public class LoginActivity extends AppCompatActivity implements LoginAlunoDelegate, LoginProfessorDelegate {


    @Bind(R.id.login_id)
    EditText id;
    @Bind(R.id.login_senha)
    EditText senha;
    @Bind(R.id.login_radio_group)
    RadioGroup radioGroup;
    private boolean ehAluno = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.login_aluno:
                        ehAluno = true;
                        break;
                    case R.id.login_professor:
                        ehAluno = false;
                        break;
                }
            }
        });
    }

    @OnClick(R.id.login_botao)
    protected void efetuaLogin() {
        if (ehAluno)
            new LoginAlunoTask(this, constroiAluno(), this).execute();
        else
            new LoginProfessorTask(this, constroiProfessor(), this).execute();
    }

    private Professor constroiProfessor() {
        Professor professor = new Professor();
        professor.setId(Long.parseLong(String.valueOf(id.getText())));
        professor.setSenha(String.valueOf(senha.getText()));
        return professor;
    }

    @OnClick(R.id.login_cadastro)
    protected void cadastro() {
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }

    private void armazenaTokenDoAluno(Aluno aluno) {
        SharedPreferences preferences = getSharedPreferences(ALUNO_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(ALUNO_TOKEN_APP, aluno.getToken()).commit();
    }

    private void armazenaTokenDoProfessor(Professor professor) {
        SharedPreferences preferences = getSharedPreferences(PROFESSOR_PREFENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PROFESSOR_TOKEN_APP, professor.getToken()).commit();
    }

    private Aluno constroiAluno() {
        Aluno aluno = new Aluno();
        aluno.setId(Long.parseLong(String.valueOf(id.getText())));
        aluno.setSenha(String.valueOf(senha.getText()));
        return aluno;
    }

    @Override
    public void carregaAulasDoAluno(Aluno aluno) {
        armazenaTokenDoAluno(aluno);
        Intent intent = new Intent(this, ListaAulaActivity.class);
        intent.putExtra("aluno", aluno);
        startActivity(intent);
        finish();
    }

    @Override
    public void carregaTurmasDoProfesor(Professor professor) {
        armazenaTokenDoProfessor(professor);
        Intent intent = new Intent(this, ListaTurmasActivity.class);
        intent.putExtra("professor", professor);
        startActivity(intent);
        finish();
    }

    @Override
    public void trataErros(Exception e) {
        e.printStackTrace();
        Toast.makeText(this, "Problema de autentiçao", Toast.LENGTH_SHORT).show();
    }
}
