package br.com.gostoudaaula.delegate;

import java.util.ArrayList;

import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Respostas;

/**
 * Created by alexf on 26/03/16.
 */
public interface ChartDelegate {

    public void carregaGrafico(Avaliacao avaliacao, ArrayList<Respostas> respostas);

    public void lidaComErro(Exception erro);

}