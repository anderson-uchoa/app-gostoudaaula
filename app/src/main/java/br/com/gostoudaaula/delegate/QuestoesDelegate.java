package br.com.gostoudaaula.delegate;

import java.util.ArrayList;
import java.util.List;

import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Respostas;

/**
 * Created by alexf on 31/01/16.
 */
public interface QuestoesDelegate {

    public void enviaRespostas(List<Respostas> respostas);

    public void lidaComErro(Exception erro);

    public void avaliacaoRespondida();

    public void proximaQuestao(Avaliacao avaliacao, ArrayList<Respostas> respostas);
}
