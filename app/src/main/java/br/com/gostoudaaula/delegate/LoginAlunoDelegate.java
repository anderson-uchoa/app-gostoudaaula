package br.com.gostoudaaula.delegate;

import br.com.gostoudaaula.model.Aluno;

/**
 * Created by alexf on 19/01/16.
 */
public interface LoginAlunoDelegate {

    public void carregaAulasDoAluno(Aluno aluno);

    public void lidaComErro(Exception e);

}
