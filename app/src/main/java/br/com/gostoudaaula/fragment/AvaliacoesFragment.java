package br.com.gostoudaaula.fragment;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.adapter.ListaAvaliacoesAdapter;
import br.com.gostoudaaula.model.Avaliacao;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by alexf on 21/03/16.
 */
public class AvaliacoesFragment extends Fragment {

    @Bind(R.id.fragment_avaliacoes_inicio)
    EditText inicio;
    @Bind(R.id.fragment_avaliacoes_fim)
    EditText fim;
    @Bind(R.id.fragment_avaliacoes_lista)
    ListView listaDeAvaliacoes;
    private List<Avaliacao> avaliacoes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        this.avaliacoes = bundle.getParcelableArrayList("avaliacoes");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_avaliacoes, container, false);
        ButterKnife.bind(this, view);

        ListaAvaliacoesAdapter adapter = new ListaAvaliacoesAdapter(getActivity(), this.avaliacoes);
        listaDeAvaliacoes.setAdapter(adapter);

        return view;
    }


    @OnClick(R.id.fragment_avaliacoes_inicio)
    public void dataInicio() {
        exibirDialog(true, false);
    }

    @OnClick(R.id.fragment_avaliacoes_fim)
    public void datafim() {
        exibirDialog(false, true);
    }

    private void exibirDialog(boolean inicio, boolean fim) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("inicio", inicio);
        bundle.putBoolean("fim", fim);
        DialogFragment fragment = new DatePickerFragment();
        fragment.setArguments(bundle);
        fragment.show(getFragmentManager(), "datePicker");
    }


}
