package br.com.gostoudaaula.helper;

import android.widget.EditText;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.activity.LoginActivity;
import br.com.gostoudaaula.model.Aluno;

/**
 * Created by alexf on 23/12/2015.
 */
public class LoginHelper {


    private final EditText prontuario;
    private final EditText senha;

    public LoginHelper(LoginActivity act) {
        this.prontuario = (EditText)act.findViewById(R.id.login_prontuario);
        this.senha = (EditText)act.findViewById(R.id.login_senha);
    }


    public Aluno constroi() {
        Aluno aluno = new Aluno(Integer.parseInt(prontuario.getText().toString()));
        aluno.setSenha(senha.getText().toString());
        return aluno;
    }
}
