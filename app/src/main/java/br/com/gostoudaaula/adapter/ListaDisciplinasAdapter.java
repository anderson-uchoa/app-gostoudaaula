package br.com.gostoudaaula.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.model.Disciplina;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexf on 16/03/16.
 */
public class ListaDisciplinasAdapter extends BaseAdapter {

    @Bind(R.id.lista_disciplina_titulo)
    TextView titulo;
    private List<Disciplina> disciplinas;
    private Activity activity;

    public ListaDisciplinasAdapter(List<Disciplina> disciplinas, Activity activity) {
        this.disciplinas = disciplinas;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return disciplinas.size();
    }

    @Override
    public Disciplina getItem(int position) {
        return disciplinas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = activity.getLayoutInflater().inflate(R.layout.lista_disciplina_personalizada, parent, false);
        ButterKnife.bind(this, convertView);

        Disciplina disciplina = disciplinas.get(position);

        titulo.setText(disciplina.getDescricao());

        return convertView;
    }
}
