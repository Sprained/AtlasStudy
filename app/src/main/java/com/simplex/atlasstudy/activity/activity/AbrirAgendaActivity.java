package com.simplex.atlasstudy.activity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.simplex.atlasstudy.R;

/**
 * Created by Men on 27/11/2017.
 */

public class AbrirAgendaActivity extends AppCompatActivity {

    private EditText nomeCompromissoAlt;
    private EditText importanciaCompromissoAlt;
    private EditText descricaoCompromissoAlt;
    private EditText dataCompromissoAlt;

    private String nomeCompromisso;
    private String importanciiaCompromisso;
    private String descricaoCompromisso;
    private String dataCompromisso;

    @Override
    protected void onCreate(Bundle saveInstaceState){
        super.onCreate(saveInstaceState);
        setContentView(R.layout.activity_abriragenda);

        nomeCompromissoAlt = (EditText) findViewById(R.id.edit_nomeCompromissoAlt);
        importanciaCompromissoAlt = (EditText) findViewById(R.id.edit_importanciaAlt);
        descricaoCompromissoAlt = (EditText) findViewById(R.id.edit_descricaoAlt);
        dataCompromissoAlt = (EditText) findViewById(R.id.edit_dataAlt);

        Bundle extra = getIntent().getExtras();

        if(extra != null){
            nomeCompromisso = extra.getString("nome");
            importanciiaCompromisso = extra.getString("importancia");
            descricaoCompromisso = extra.getString("descicao");
            dataCompromisso = extra.getString("data");
        }

        nomeCompromissoAlt.setText(nomeCompromisso);
        importanciaCompromissoAlt.setText(importanciiaCompromisso);
        descricaoCompromissoAlt.setText(descricaoCompromisso);
        dataCompromissoAlt.setText(dataCompromisso);
    }
}
