package br.com.gostoudaaula.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.adapter.ListaDisciplinasAdapter;
import br.com.gostoudaaula.delegate.PeriodoLetivoDelegate;
import br.com.gostoudaaula.model.Disciplina;
import br.com.gostoudaaula.model.PeriodoLetivo;
import br.com.gostoudaaula.model.Professor;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by alexf on 16/03/16.
 */
public class DisciplinaFragment extends Fragment {

    @Bind(R.id.fragment_disciplinas_lista)
    ListView listaDeDisciplinas;
    private ArrayList<Disciplina> disciplinas;
    private PeriodoLetivoDelegate delegate;
    private Professor professor;
    private PeriodoLetivo periodo;

    private void setUp() {
        Bundle bundle = getArguments();
        this.disciplinas = bundle.getParcelableArrayList("disciplinas");
        this.professor = bundle.getParcelable("professor");
        this.periodo = bundle.getParcelable("periodo");
        this.delegate = (PeriodoLetivoDelegate) getActivity();
        setTitle("Disciplina");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setUp();

        View view = inflater.inflate(R.layout.fragment_disciplinas, container, false);

        ButterKnife.bind(this, view);

        ListaDisciplinasAdapter adapter = new ListaDisciplinasAdapter(disciplinas, getActivity());

        listaDeDisciplinas.setAdapter(adapter);

        return view;
    }

    @OnItemClick(R.id.fragment_disciplinas_lista)
    public void carregaAvaliacoes(int position) {
        Disciplina disciplina = (Disciplina) listaDeDisciplinas.getItemAtPosition(position);
        periodo.setDisciplina(disciplina);
        delegate.lidaComPeriodoLetivo(periodo);
    }


    private void setTitle(String title) {
        getActivity().setTitle(title);
    }
}
