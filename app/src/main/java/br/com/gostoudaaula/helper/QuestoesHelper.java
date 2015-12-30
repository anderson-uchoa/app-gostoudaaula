package br.com.gostoudaaula.helper;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import br.com.gostoudaaula.R;
import br.com.gostoudaaula.activity.QuestoesActivity;
import br.com.gostoudaaula.model.Questoes;

/**
 * Created by alexf on 26/12/2015.
 */
public class QuestoesHelper {

    private final RadioGroup alternativas;
    private final TextView pergunta;
    private QuestoesActivity act;
    private Map<Integer, String> resultado;


    public QuestoesHelper(QuestoesActivity act) {
        this.act = act;
        this.pergunta = (TextView) act.findViewById(R.id.questoes_pergunta);
        this.alternativas = (RadioGroup) act.findViewById(R.id.questoes_alternativas);
        this.resultado = new HashMap();

    }

    public void inserePergunta(Questoes questoes) {
        this.pergunta.setText(questoes.getDescricao());
    }

    public void addAlterinativa(String descricao) {
        RadioButton radioButton = new RadioButton(act);
        radioButton.setText(descricao);
        this.alternativas.addView(radioButton);
    }

    public void setResposta() {
        RadioButton radioButton = (RadioButton) this.act.findViewById(alternativas.getCheckedRadioButtonId());

        Toast.makeText(this.act
                , radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
