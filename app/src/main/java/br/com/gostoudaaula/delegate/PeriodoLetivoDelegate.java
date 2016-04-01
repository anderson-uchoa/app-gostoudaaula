package br.com.gostoudaaula.delegate;

import br.com.gostoudaaula.model.PeriodoLetivo;

/**
 * Created by alexf on 25/03/16.
 */
public interface PeriodoLetivoDelegate {

    public void lidaComPeriodoLetivo(PeriodoLetivo periodo);

    public void lidaComErro(Exception erro);
}
