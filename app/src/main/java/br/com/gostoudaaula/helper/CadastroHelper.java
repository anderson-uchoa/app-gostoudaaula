package br.com.gostoudaaula.helper;

import android.widget.EditText;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.activity.CadastroActivity;
import br.com.gostoudaaula.model.Aluno;

/**
 * Created by alexf on 24/12/2015.
 */
public class CadastroHelper {

    private final EditText sobrenome;
    private final EditText nome;
    private final EditText prontuario;
    private final EditText senha;

    public CadastroHelper(CadastroActivity act) {
        this.nome = (EditText) act.findViewById(R.id.cadastro_nome);
        this.sobrenome = (EditText) act.findViewById(R.id.cadastro_sobrenome);
        this.prontuario = (EditText) act.findViewById(R.id.cadastro_prontuario);
        this.senha = (EditText) act.findViewById(R.id.cadastro_senha);
    }


    public Aluno constroi() {
        Aluno aluno = new Aluno(Integer.parseInt(prontuario.getText().toString()));
        aluno.setNome(nome.getText().toString());
        aluno.setSobrenome(sobrenome.getText().toString());
        aluno.setSenha(senha.getText().toString());
        return aluno;
    }
}
