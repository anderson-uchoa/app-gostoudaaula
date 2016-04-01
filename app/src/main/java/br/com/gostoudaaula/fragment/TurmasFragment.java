package br.com.gostoudaaula.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.adapter.ListaTurmasAdapter;
import br.com.gostoudaaula.delegate.ListaDisciplinaDelegate;
import br.com.gostoudaaula.model.PeriodoLetivo;
import br.com.gostoudaaula.model.Professor;
import br.com.gostoudaaula.model.Turma;
import br.com.gostoudaaula.task.ListaDisciplinaTask;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by alexf on 16/03/16.
 */
public class TurmasFragment extends Fragment {

    @Bind(R.id.fragment_turmas_lista)
    ListView listaDeTurmas;
    private List<Turma> turmas;
    private ListaDisciplinaDelegate delegate;
    private Professor professor;
    private PeriodoLetivo periodo;

    private void setUp() {
        Bundle bundle = getArguments();
        this.turmas = bundle.getParcelableArrayList("turmas");
        this.professor = bundle.getParcelable("professor");
        periodo = new PeriodoLetivo();
        this.delegate = (ListaDisciplinaDelegate) getActivity();
        setTitle("Turmas");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setUp();

        View view = inflater.inflate(R.layout.fragment_turmas, container, false);

        ButterKnife.bind(this, view);

        ListaTurmasAdapter adapter = new ListaTurmasAdapter(turmas, getActivity());

        listaDeTurmas.setAdapter(adapter);

        return view;
    }

    @OnItemClick(R.id.fragment_turmas_lista)
    public void carregaDisciplina(int position) {

        Turma turma = (Turma) listaDeTurmas.getItemAtPosition(position);

        periodo.setTurma(turma);

        new ListaDisciplinaTask(delegate, periodo, professor, getActivity()).execute();
    }

    private void setTitle(String title) {
        getActivity().setTitle(title);
    }
}
