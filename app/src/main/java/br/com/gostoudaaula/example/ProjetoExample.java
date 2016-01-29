package br.com.gostoudaaula.example;

import java.util.List;

import br.com.gostoudaaula.model.Projeto;
import br.com.gostoudaaula.model.Questoes;

/**
 * Created by alexf on 27/01/16.
 */
public class ProjetoExample {


    public Projeto getFullExample() {
        Projeto projeto = getExample1();
        List<Questoes> questoes = new QuestoesExample().getListQuestoes();
        projeto.setQuestoes(questoes);
        return projeto;
    }

    public Projeto getExample1() {
        Projeto projeto = new Projeto();
        projeto.setDescricao("Projeto de exemplo 1");
        return projeto;
    }
}
