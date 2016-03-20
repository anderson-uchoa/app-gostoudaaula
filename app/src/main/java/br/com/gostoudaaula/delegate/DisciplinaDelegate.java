package br.com.gostoudaaula.delegate;

import java.util.ArrayList;

import br.com.gostoudaaula.model.Disciplina;

/**
 * Created by alexf on 16/03/16.
 */
public interface DisciplinaDelegate {

    public void lidaComDisciplina(ArrayList<Disciplina> disciplina);

    public void lidaComErro(Exception erro);

}
