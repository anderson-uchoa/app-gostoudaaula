package br.com.gostoudaaula.delegate;

import java.util.List;

import br.com.gostoudaaula.model.Aula;

/**
 * Created by alexf on 19/01/16.
 */
public interface AulasDoAlunoDelegate {
    public void populaListaDeAulas(List<Aula> aulas);
    public void trataErro(Exception e);
    public void alertSemAulas();
}
