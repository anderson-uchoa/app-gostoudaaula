package br.com.gostoudaaula.fragment;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;

import org.joda.time.LocalDate;

import java.util.Calendar;

import br.com.gostoudaaula.R;

/**
 * Created by alexf on 20/03/16.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    private LocalDate data;
    private boolean inicio;
    private boolean fim;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.inicio = getArguments().getBoolean("inicio");
        this.fim = getArguments().getBoolean("fim");
        Log.i("inicio", String.valueOf(inicio));
        Log.i("fim", String.valueOf(fim));
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.data = new LocalDate(year, monthOfYear, dayOfMonth);
        if (inicio) {
            EditText inicio = (EditText) getActivity().findViewById(R.id.fragment_avaliacoes_inicio);
            inicio.setText(data.toString());
        } else if (fim) {
            EditText inicio = (EditText) getActivity().findViewById(R.id.fragment_avaliacoes_fim);
            inicio.setText(data.toString());
        }
    }

    public LocalDate getData() {
        return data;
    }
}
