package br.com.gostoudaaula.utils;

import android.content.Context;
import android.content.SharedPreferences;

import br.com.gostoudaaula.model.Aluno;
import br.com.gostoudaaula.model.Professor;

import static br.com.gostoudaaula.utils.AlunoUtils.ALUNO_PREFERENCES;
import static br.com.gostoudaaula.utils.AlunoUtils.ALUNO_TOKEN_APP;
import static br.com.gostoudaaula.utils.ProfessorUtils.PROFESSOR_PREFENCES;
import static br.com.gostoudaaula.utils.ProfessorUtils.PROFESSOR_TOKEN_APP;

/**
 * Created by alexf on 22/03/16.
 */
public class TokenUtils {

    private Context ctx;

    public TokenUtils(Context ctx) {
        this.ctx = ctx;
    }

    public void armazenaToken(Aluno aluno) {
        SharedPreferences preferences = ctx.getSharedPreferences(ALUNO_PREFERENCES, ctx.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(ALUNO_TOKEN_APP, aluno.getToken()).commit();
    }

    public void armazenaToken(Professor professor) {
        SharedPreferences preferences = ctx.getSharedPreferences(PROFESSOR_PREFENCES, ctx.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PROFESSOR_TOKEN_APP, professor.getToken()).commit();
    }

    public void deslogaProfessor() {
        ctx.getSharedPreferences(PROFESSOR_PREFENCES, ctx.MODE_PRIVATE).edit().remove(PROFESSOR_TOKEN_APP).commit();
    }

    public void deslogaAluno() {
        ctx.getSharedPreferences(ALUNO_PREFERENCES, ctx.MODE_PRIVATE).edit().remove(ALUNO_TOKEN_APP).commit();
    }
}
