package br.com.gostoudaaula.example;

import org.joda.time.LocalDate;

import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Projeto;

/**
 * Created by alexf on 27/01/16.
 */
public class AvaliacaoExample {

    public Avaliacao getFullExample1(){
        Avaliacao avaliacao = new Avaliacao();
        Projeto projeto = new ProjetoExample().getFullExample();
        avaliacao.setProjeto(projeto);
        avaliacao.setData(LocalDate.now());
        return avaliacao;
    }
}
