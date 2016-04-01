package br.com.gostoudaaula.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.model.Avaliacao;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexf on 21/03/16.
 */
public class ListaAvaliacoesAdapter extends BaseAdapter {

    private final List<Avaliacao> avaliacoes;
    private final Activity activity;
    @Bind(R.id.lista_avaliacao_personalizada_titulo)
    TextView titulo;
    @Bind(R.id.lista_avaliacao_personalizada_data)
    TextView data;

    public ListaAvaliacoesAdapter(Activity activity, List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return avaliacoes.size();
    }

    @Override
    public Avaliacao getItem(int position) {
        return avaliacoes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = activity.getLayoutInflater().inflate(R.layout.lista_avaliacao_personalizada, parent, false);
        ButterKnife.bind(this, convertView);

        Avaliacao avaliacao = getItem(position);

        titulo.setText(avaliacao.getAula().getPeriodoLetivo().getDisciplina().getDescricao());
        data.setText(avaliacao.getData().toString("dd/MM/YYYY"));

        return convertView;
    }
}
