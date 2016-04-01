package br.com.gostoudaaula.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.delegate.ChartDelegate;
import br.com.gostoudaaula.delegate.ListaDisciplinaDelegate;
import br.com.gostoudaaula.delegate.ListaTurmaDelegate;
import br.com.gostoudaaula.delegate.PeriodoLetivoDelegate;
import br.com.gostoudaaula.fragment.AvaliacoesFragment;
import br.com.gostoudaaula.fragment.ChartFragment;
import br.com.gostoudaaula.fragment.DisciplinaFragment;
import br.com.gostoudaaula.fragment.TurmasFragment;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Disciplina;
import br.com.gostoudaaula.model.PeriodoLetivo;
import br.com.gostoudaaula.model.Professor;
import br.com.gostoudaaula.model.Respostas;
import br.com.gostoudaaula.model.Turma;
import br.com.gostoudaaula.task.ListaTurmaTask;
import br.com.gostoudaaula.utils.TokenUtils;


/**
 * Created by alexf on 11/03/16.
 */
public class ProfessorMainActivity extends AppCompatActivity implements ListaDisciplinaDelegate, ListaTurmaDelegate, PeriodoLetivoDelegate, ChartDelegate {

    private Professor professor;
    private PeriodoLetivo periodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_frame_layout);

        if (periodo == null) {
            periodo = new PeriodoLetivo();
        }

        if (getIntent().hasExtra("professor")) {
            this.professor = getIntent().getParcelableExtra("professor");
        } else {
            finish();
            new TokenUtils(this).deslogaProfessor();
        }

        new ListaTurmaTask(this, professor, this).execute();
    }

    private void carregaListaTurma(ArrayList<Turma> turmas) {
        Bundle bundle = getArguments();
        bundle.putParcelableArrayList("turmas", turmas);
        Fragment fragment = new TurmasFragment();
        fragment.setArguments(bundle);
        carregaFragment(fragment);
    }

    private Bundle getArguments() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("professor", professor);
        bundle.putParcelable("periodo", periodo);
        return bundle;
    }

    private void carregaListaDisciplina(ArrayList<Disciplina> disciplinas) {
        Bundle bundle = getArguments();
        bundle.putParcelableArrayList("disciplinas", disciplinas);
        Fragment fragment = new DisciplinaFragment();
        fragment.setArguments(bundle);
        carregaFragment(fragment);
    }

    private void carregaListaAvaliacao() {
        Bundle bundle = getArguments();
        Fragment fragment = new AvaliacoesFragment();
        fragment.setArguments(bundle);
        carregaFragment(fragment);
    }


    private void carregaFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.activity_frame_layout_default, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void lidaComDisciplina(ArrayList<Disciplina> disciplina, PeriodoLetivo periodo) {
        this.periodo = periodo;
        carregaListaDisciplina(disciplina);
    }

    @Override
    public void lidaComTurmas(ArrayList<Turma> turmas) {
        carregaListaTurma(turmas);
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

    @Override
    public void lidaComPeriodoLetivo(PeriodoLetivo periodo) {
        this.periodo = periodo;
        carregaListaAvaliacao();
    }

    @Override
    public void carregaGrafico(Avaliacao avaliacao, ArrayList<Respostas> respostas) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("avaliacao", avaliacao);
        bundle.putParcelableArrayList("respostas", respostas);
        Fragment fragment = new ChartFragment();
        fragment.setArguments(bundle);
        carregaFragment(fragment);
    }

    @Override
    public void lidaComErro(Exception erro) {
        Toast.makeText(ProfessorMainActivity.this, "Ocorreu um erro", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i("chama on save", "chamando");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("chama on restore", "chamando");
    }
}
