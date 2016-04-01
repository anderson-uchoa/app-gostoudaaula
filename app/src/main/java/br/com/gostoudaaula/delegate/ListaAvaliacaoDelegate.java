package br.com.gostoudaaula.delegate;

import java.util.ArrayList;

import br.com.gostoudaaula.model.Avaliacao;

/**
 * Created by alexf on 24/03/16.
 */
public interface ListaAvaliacaoDelegate {

    public void lidaComErro(Exception e);

    public void lidaComAvaliacoes(ArrayList<Avaliacao> avaliacoes);
}

