package br.com.gostoudaaula.helper;

import android.widget.ListView;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.activity.ListaAulaActivity;

/**
 * Created by alexf on 04/01/16.
 */
public class ListaAulaHelper {

    private final ListaAulaActivity act;
    private final ListView listaAulas;

    public ListaAulaHelper(ListaAulaActivity act) {
        this.act = act;
        this.listaAulas = (ListView) act.findViewById(R.id.lista_aula_aulas);
    }

    public ListView getLista(){
        return this.listaAulas;
    }


}
