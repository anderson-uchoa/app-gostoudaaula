package br.com.gostoudaaula.delegate;

import br.com.gostoudaaula.model.Professor;

/**
 * Created by alexf on 11/03/16.
 */
public interface LoginProfessorDelegate {
    public void lidaComErro(Exception erro);
    public void carregaTurmasDoProfesor(Professor professor);
}
