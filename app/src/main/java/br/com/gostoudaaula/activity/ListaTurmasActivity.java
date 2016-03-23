package br.com.gostoudaaula.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.delegate.DisciplinaDelegate;
import br.com.gostoudaaula.delegate.TurmaDelegate;
import br.com.gostoudaaula.fragment.DisciplinaFragment;
import br.com.gostoudaaula.fragment.TurmasFragment;
import br.com.gostoudaaula.model.Disciplina;
import br.com.gostoudaaula.model.Professor;
import br.com.gostoudaaula.model.Turma;
import br.com.gostoudaaula.task.TurmaTask;
import br.com.gostoudaaula.utils.TokenUtils;


/**
 * Created by alexf on 11/03/16.
 */
public class ListaTurmasActivity extends AppCompatActivity implements DisciplinaDelegate, TurmaDelegate {

    private Professor professor;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_frame_layout);

        if (getIntent().hasExtra("professor")) {
            this.professor = getIntent().getParcelableExtra("professor");
        }

        new TurmaTask(this, professor, this).execute();
    }

    private void carregaListaTumas(ArrayList<Turma> turmas) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("turmas", turmas);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fragment = new TurmasFragment();
        fragment.setArguments(bundle);
        carregaFragment(ft, fragment);
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
    public void lidaComTurmas(ArrayList<Turma> turmas) {
        carregaListaTumas(turmas);
    }

    @Override
    public void lidaComErro(Exception erro) {
        Toast.makeText(ListaTurmasActivity.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista_aulas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_lista_aula_logout:
                new TokenUtils(this).deslogaProfessor();
                Intent intent = new Intent(this, SplashActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
