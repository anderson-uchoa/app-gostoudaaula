package br.com.gostoudaaula.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.model.Avaliacao;
import br.com.gostoudaaula.model.Respostas;
import butterknife.Bind;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by alexf on 25/03/16.
 */
public class ChartFragment extends Fragment {


    private final int COLOR_BLACK = Color.parseColor("#000000");
    @Bind(R.id.fragment_column_chart)
    ColumnChartView chart;
    @Bind(R.id.fragment_chart_titulo)
    TextView titulo;
    @Bind(R.id.fragment_chart_data)
    TextView dataAula;


    private List<Respostas> respostas;
    private Avaliacao avaliacao;
    private ArrayList<Column> columns;
    private ColumnChartData data;
    private int UmATres;
    private int QuatroASeis;
    private int SeteADez;


    private void setUp() {
        Bundle bundle = getArguments();
        this.avaliacao = bundle.getParcelable("avaliacao");
        this.respostas = bundle.getParcelableArrayList("respostas");

        setTitle("Total de notas");

        titulo.setText(avaliacao.getAula().getPeriodoLetivo().getDisciplina().getDescricao());
        dataAula.setText(avaliacao.getAula().getData().toString("dd/MM/yyyy"));

    }

    private void defaultConfigChart() {
        this.columns = new ArrayList<>();
        this.data = new ColumnChartData();
        adicionaColunas(columns, 3);
        this.chart.setInteractive(false);
        for (Respostas resposta : respostas) {
            int valor = resposta.getResposta();
            if (valor < 4) {
                UmATres++;
            } else if (valor < 7) {
                QuatroASeis++;
            } else {
                SeteADez++;
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        ButterKnife.bind(this, view);

        setUp();
        defaultConfigChart();

        adicionaValorPraColuna(columns, 1, UmATres, "1 - 3", Color.parseColor("#FF0000"));
        adicionaValorPraColuna(columns, 2, QuatroASeis, "4 - 6", Color.parseColor("#fff518"));
        adicionaValorPraColuna(columns, 3, SeteADez, "7 - 10", Color.parseColor("#1bc500"));


        data.setColumns(columns);

        adicionaIndice(data, 10, 1);

        chart.setColumnChartData(data);


        return view;
    }

    private void adicionaIndice(ColumnChartData data, int total, int intervalo) {
        Axis axisY = new Axis().setHasLines(true);
        axisY.setLineColor(COLOR_BLACK);
        axisY.setTextColor(COLOR_BLACK);

        List<AxisValue> axisValues = new ArrayList<>();

        for (int i = 0; i < total; i += intervalo) {
            axisValues.add(new AxisValue(i));
        }

        axisY.setValues(axisValues);
        data.setAxisYLeft(axisY);
    }

    private void adicionaColunas(List<Column> columns, int amount) {
        for (int i = 0; i < amount; i++) {
            columns.add(new Column());
        }
    }

    private List<SubcolumnValue> pegaValoresParaColuna(int value, String label, int color) {
        List<SubcolumnValue> values = new ArrayList<>();
        values.add(new SubcolumnValue(value).setLabel(label).setColor(color));
        return values;
    }

    private void adicionaValorPraColuna(List<Column> columns, int position, int value, String label, int color) {
        Column column = columns.get(position - 1);
        column.setValues(pegaValoresParaColuna(value, label, color));
        column.setHasLabels(true);
    }

    private void setTitle(String title) {
        getActivity().setTitle(title);
    }
}
