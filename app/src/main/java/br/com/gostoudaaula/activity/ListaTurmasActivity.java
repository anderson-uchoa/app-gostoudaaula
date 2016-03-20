package br.com.gostoudaaula.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.delegate.DisciplinaDelegate;
import br.com.gostoudaaula.fragment.DisciplinaFragment;
import br.com.gostoudaaula.fragment.TurmasFragment;
import br.com.gostoudaaula.model.Disciplina;
import br.com.gostoudaaula.model.Professor;
import br.com.gostoudaaula.model.Turma;

/**
 * Created by alexf on 11/03/16.
 */
public class ListaTurmasActivity extends AppCompatActivity implements DisciplinaDelegate {

    private Professor professor;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_frame_layout);

        if (getIntent().hasExtra("professor")) {
            this.professor = getIntent().getParcelableExtra("professor");
        }

        carregaListaTumas();
    }

    private void carregaListaTumas() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("turmas", getTurmas());
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fragment = new TurmasFragment();
        fragment.setArguments(bundle);
        carregaFragment(ft, fragment);
    }

    private ArrayList<Turma> getTurmas() {
        Turma turma1 = new Turma();
        turma1.setId(1L);
        turma1.setDescricao("CCO");
        Turma turma2 = new Turma();
        turma2.setId(2L);
        turma2.setDescricao("EGC");
        ArrayList<Turma> turmas = new ArrayList<>();
        turmas.addAll(Arrays.asList(turma1, turma2));
        return turmas;
    }

    @Override
    public void lidaComDisciplina(ArrayList<Disciplina> disciplina) {

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("disciplinas", disciplina);

        fragment = new DisciplinaFragment();

        fragment.setArguments(bundle);

    }

    private void carregaFragment(FragmentTransaction ft, Fragment fragment) {
        ft.replace(R.id.activity_frame_layout_default, fragment);
        ft.commit();
    }

    @Override
    public void lidaComErro(Exception erro) {
        Toast.makeText(ListaTurmasActivity.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
    }
}
