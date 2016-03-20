package br.com.gostoudaaula.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.model.Turma;

/**
 * Created by alexf on 16/03/16.
 */
public class ListaTurmasAdapter extends BaseAdapter {

    private final List<Turma> turmas;
    private final Activity act;

    public ListaTurmasAdapter(List<Turma> turmas, Activity act) {
        this.act = act;
        this.turmas = turmas;
    }

    @Override
    public int getCount() {
        return turmas.size();
    }

    @Override
    public Turma getItem(int position) {
        return turmas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = act.getLayoutInflater().inflate(R.layout.lista_turma_personalizada, parent, false);
        TextView titulo = (TextView) convertView.findViewById(R.id.lista_turma_titulo);

        Turma turma = turmas.get(position);

        titulo.setText(turma.getDescricao().toString());

        return convertView;
    }
}
