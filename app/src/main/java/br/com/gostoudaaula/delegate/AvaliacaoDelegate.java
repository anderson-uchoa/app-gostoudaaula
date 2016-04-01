package br.com.gostoudaaula.delegate;

import br.com.gostoudaaula.model.Avaliacao;

/**
 * Created by alexf on 31/01/16.
 */
public interface AvaliacaoDelegate {

    public void lidaComAvaliacao(Avaliacao avaliacoes);

    public void lidaComErro(Exception erro);
}
