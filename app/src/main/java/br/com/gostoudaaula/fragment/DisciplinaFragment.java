package br.com.gostoudaaula.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.adapter.ListaDisciplinasAdapter;
import br.com.gostoudaaula.model.Disciplina;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by alexf on 16/03/16.
 */
public class DisciplinaFragment extends Fragment {

    @Bind(R.id.fragment_disciplinas_lista)
    ListView litaDeDisciplinas;
    private List<Disciplina> disciplinas;

    private void setUp() {
        Bundle bundle = getArguments();
        this.disciplinas = bundle.getParcelable("disciplinas");
        Log.i("chega aqui", "fragment hue");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setUp();

        View view = inflater.inflate(R.layout.fragment_disciplinas, container, false);

        ButterKnife.bind(this, view);

        ListaDisciplinasAdapter adapter = new ListaDisciplinasAdapter(disciplinas, getActivity());

        litaDeDisciplinas.setAdapter(adapter);

        return view;
    }
}
