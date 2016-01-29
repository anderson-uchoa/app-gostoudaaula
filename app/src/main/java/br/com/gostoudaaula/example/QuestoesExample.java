package br.com.gostoudaaula.example;

import java.util.ArrayList;
import java.util.List;

import br.com.gostoudaaula.model.Questoes;

/**
 * Created by alexf on 27/01/16.
 */
public class QuestoesExample {


    public List<Questoes> getListQuestoes() {
        List<Questoes> questoes = new ArrayList<>();
        questoes.add(getExample1());
        questoes.add(getExample2());
        questoes.add(getExample3());
        return questoes;
    }

    public Questoes getExample1() {
        Questoes questoes = new Questoes();
        questoes.setDescricao("questao de exemplo 1");
        return questoes;
    }

    public Questoes getExample2() {
        Questoes questoes = new Questoes();
        questoes.setDescricao("questao de exemplo 2");
        return questoes;
    }

    public Questoes getExample3() {
        Questoes questoes = new Questoes();
        questoes.setDescricao("questao de exemplo 3");
        return questoes;
    }
}
