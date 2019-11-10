package com.simplex.atlasstudy.activity.activity.helér;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Men on 26/11/2017.
 */

public class Preferencias {

    private Context context;
    private SharedPreferences preferences;
    private final String NOME_ARQUIVO = "atlas.preferences";
    private final int MODE = 0;
    private SharedPreferences.Editor editor;

    private final String CHAVE_IDENTIFICADOR = "identificadorUsuarioLogado";

    public Preferencias(Context contextoParametro){
        context = contextoParametro;
        preferences = context.getSharedPreferences(NOME_ARQUIVO, MODE);
        editor = preferences.edit();
    }

    public void salvarDados(String identificadorUsuarioLogado){
        editor.putString(CHAVE_IDENTIFICADOR, identificadorUsuarioLogado);
        editor.commit();
    }

    public String getIdentificador(){
        return preferences.getString(CHAVE_IDENTIFICADOR, null);
    }
}
