package br.com.gostoudaaula.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.model.Aula;

/**
 * Created by alexf on 04/01/16.
 */
public class ListaAulasAdapter extends BaseAdapter {


    private final List<Aula> aulas;
    private final Activity act;

    public ListaAulasAdapter(Activity activity, List<Aula> aulas) {
        this.aulas = aulas;
        this.act = activity;
    }

    @Override
    public int getCount() {
        return aulas.size();
    }

    @Override
    public Object getItem(int position) {
        return aulas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return aulas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = act.getLayoutInflater().inflate(R.layout.lista_aula_personalizada, parent, false);

        Aula aula = aulas.get(position);

        TextView nomeAula = (TextView) convertView.findViewById(R.id.lista_aula_nomeAula);
        TextView nomeProfessor = (TextView) convertView.findViewById(R.id.lista_aula_nomeProfessor);
        TextView data = (TextView) convertView.findViewById(R.id.lista_aula_data);

        nomeAula.setText(aula.getPeriodoLetivo().getDisciplina().getDescricao().toString());
        nomeProfessor.setText(aula.getProfessor().getNome().toString());
        data.setText(aula.getData().toString("dd/MM/yyyy"));

        return convertView;
    }
}
