package br.com.gostoudaaula.delegate;

import java.util.ArrayList;

import br.com.gostoudaaula.model.Disciplina;
import br.com.gostoudaaula.model.PeriodoLetivo;

/**
 * Created by alexf on 16/03/16.
 */
public interface ListaDisciplinaDelegate {

    public void lidaComDisciplina(ArrayList<Disciplina> disciplina, PeriodoLetivo periodo);

    public void lidaComErro(Exception erro);

}
