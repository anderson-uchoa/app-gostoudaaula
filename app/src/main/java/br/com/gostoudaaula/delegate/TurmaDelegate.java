package br.com.gostoudaaula.delegate;

import java.util.ArrayList;
import java.util.List;

import br.com.gostoudaaula.model.Turma;

/**
 * Created by alexf on 22/03/16.
 */
public interface TurmaDelegate {

    public void lidaComTurmas(ArrayList<Turma> turmas);

    public void lidaComErro(Exception erro);
}
