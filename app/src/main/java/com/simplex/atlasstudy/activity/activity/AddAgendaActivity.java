package com.simplex.atlasstudy.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.simplex.atlasstudy.R;
import com.simplex.atlasstudy.activity.activity.config.ConfigFirebase;
import com.simplex.atlasstudy.activity.activity.helér.Preferencias;
import com.simplex.atlasstudy.activity.activity.model.Agenda;

/**
 * Created by Men on 27/11/2017.
 */

public class AddAgendaActivity extends AppCompatActivity {

    private DatabaseReference referenceFirebase;
    private FirebaseAuth authentication;

    private Button btn_salvar;
    private Button btn_cancelar;
    private Button btnVoltatAgenda;

    private EditText nomeCompromisso;
    private EditText importaciaCompromisso;
    private EditText descricaoCompromisso;
    private EditText dataCompromisso;

    @Override
    protected void onCreate(Bundle saveInstaceState){
        super.onCreate(saveInstaceState);
        setContentView(R.layout.activity_addagenda);

        authentication = ConfigFirebase.getFirebaseAuth();

        btn_salvar = (Button) findViewById(R.id.btn_salvar);
        btn_cancelar = (Button) findViewById(R.id.btn_cancelar);
        btnVoltatAgenda = (Button) findViewById(R.id.btnVoltarAddAgenda);

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAgendaActivity.this, AgendaActivity.class);
                startActivity(intent);
            }
        });

        btnVoltatAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddAgendaActivity.this, AgendaActivity.class);
                startActivity(intent);
            }
        });

        nomeCompromisso = (EditText) findViewById(R.id.edit_nomeCompromisso);
        importaciaCompromisso = (EditText) findViewById(R.id.edit_importancia);
        descricaoCompromisso = (EditText) findViewById(R.id.edit_descricao);
        dataCompromisso = (EditText) findViewById(R.id.edit_data);

        btn_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = nomeCompromisso.getText().toString();
                String importacia = importaciaCompromisso.getText().toString();
                String descricao = descricaoCompromisso.getText().toString();
                String data = dataCompromisso.getText().toString();

                //Recuperar identificador
                Preferencias preferencias = new Preferencias(AddAgendaActivity.this);
                String identificadorUsuarioLogado = preferencias.getIdentificador();

                referenceFirebase = ConfigFirebase.getFirebase();
                referenceFirebase = referenceFirebase.child("agenda").child(identificadorUsuarioLogado).child(nome);

                Agenda agenda = new Agenda();
                agenda.setNomeAgenda(nome);
                agenda.setImportanciaCompromisso(importacia);
                agenda.setConteudoAgenda(descricao);
                agenda.setSemanasAgenda(data);

                referenceFirebase.setValue(agenda);

                Toast.makeText(AddAgendaActivity.this,"Agenda Salva", Toast.LENGTH_LONG).show();
            }
        });
    }
}
