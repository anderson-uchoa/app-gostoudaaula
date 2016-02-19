package br.com.gostoudaaula.helper;

import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.activity.ListaAulaActivity;

/**
 * Created by alexf on 04/01/16.
 */
public class ListaAulaHelper {

    private final ListaAulaActivity act;
    private final ListView listaAulas;
    private final SwipeRefreshLayout swipe;

    public ListaAulaHelper(ListaAulaActivity act) {
        this.act = act;
        this.listaAulas = (ListView) act.findViewById(R.id.lista_aula_aulas);
        this.swipe = (SwipeRefreshLayout) act.findViewById(R.id.lista_aula_swipe);
    }

    public ListView listaDeAulas() {
        return listaAulas;
    }

    public ListView getLista() {
        return this.listaAulas;
    }

    public void finalizaSwipe() {
        this.swipe.setRefreshing(false);
        this.swipe.clearAnimation();
    }

    public SwipeRefreshLayout getSwipe() {
        return swipe;
    }
}
