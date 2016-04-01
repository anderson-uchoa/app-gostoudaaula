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
import android.widget.Toast;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.adapter.ListaAvaliacoesAdapter;
import br.com.gostoudaaula.delegate.ChartDelegate;
import br.com.gostoudaaula.delegate.ListaAvaliacaoDelegate;
import br.com.gostoudaaula.dto.PeriodoDTO;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.PeriodoLetivo;
import br.com.gostoudaaula.model.Professor;
import br.com.gostoudaaula.task.ChartTask;
import br.com.gostoudaaula.task.ListaAvaliacaoTask;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by alexf on 21/03/16.
 */
public class AvaliacoesFragment extends Fragment implements ListaAvaliacaoDelegate {

    @Bind(R.id.fragment_avaliacoes_inicio)
    EditText inicio;
    @Bind(R.id.fragment_avaliacoes_fim)
    EditText fim;
    @Bind(R.id.fragment_avaliacoes_lista)
    ListView listaDeAvaliacoes;
    private ArrayList<Avaliacao> avaliacoes;
    private Professor professor;
    private PeriodoLetivo periodo;
    private ChartDelegate delegate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        this.professor = bundle.getParcelable("professor");
        this.periodo = bundle.getParcelable("periodo");
        this.avaliacoes = new ArrayList<>();
        this.delegate = (ChartDelegate) getActivity();
        setTitle("Avaliações");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_avaliacoes, container, false);
        ButterKnife.bind(this, view);

        carregaLista();

        return view;
    }

    private void carregaLista() {
        ListaAvaliacoesAdapter adapter = new ListaAvaliacoesAdapter(getActivity(), this.avaliacoes);
        listaDeAvaliacoes.setAdapter(adapter);
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

    @OnItemClick(R.id.fragment_avaliacoes_lista)
    public void carregaGrafico(int position) {
        Avaliacao avaliacao = (Avaliacao) listaDeAvaliacoes.getItemAtPosition(position);
        new ChartTask(delegate, avaliacao, getActivity()).execute();
    }

    @OnClick(R.id.fragment_avaliacoes_pesquisar)
    public void carregaAvaliacoes() {
        PeriodoDTO periodoDTO = new PeriodoDTO();

        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");

        periodoDTO.setInicio(LocalDate.parse(inicio.getText().toString(), formatter));
        periodoDTO.setFim(LocalDate.parse(fim.getText().toString(), formatter));
        periodoDTO.setPeriodoLetivo(this.periodo);
        new ListaAvaliacaoTask(this, periodoDTO, professor, getActivity()).execute();
    }

    @Override
    public void lidaComErro(Exception e) {
        e.printStackTrace();
        Toast.makeText(getActivity(), "Ocorreu um erro", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void lidaComAvaliacoes(ArrayList<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
        carregaLista();
    }


    private void setTitle(String title) {
        getActivity().setTitle(title);
    }
}
