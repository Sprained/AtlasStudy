package com.simplex.atlasstudy.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.simplex.atlasstudy.R;
import com.simplex.atlasstudy.activity.activity.config.ConfigFirebase;
import com.simplex.atlasstudy.activity.activity.helér.Preferencias;
import com.simplex.atlasstudy.activity.activity.model.Agenda;

import java.util.ArrayList;

/**
 * Created by Men on 26/10/2017.
 */

public class AgendaActivity extends AppCompatActivity {

    private Button btnAddAgenda;
    private String identificadorAgenda;

    private ListView lv_agendas;
    private Button btnVoltarAgenda;
    private ArrayAdapter adapter;
    private ArrayList<Agenda> agendas;

    private DatabaseReference referenceFirebase;
    private FirebaseAuth authentication;

    @Override
    protected void onCreate(Bundle saveInstaceState){
        super.onCreate(saveInstaceState);
        setContentView(R.layout.activity_agenda);

        btnVoltarAgenda = (Button) findViewById(R.id.btnVoltarAgenda);
        btnAddAgenda = (Button) findViewById(R.id.btnAddAgenda);


        //voltar menu
        btnVoltarAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgendaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnAddAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgendaActivity.this, AddAgendaActivity.class);
                startActivity(intent);
            }
        });

        //Instanciar Array
        agendas = new ArrayList<>();

        //Montagem List View e Adapter
        lv_agendas = (ListView) findViewById(R.id.lv_agenda);
        adapter = new ArrayAdapter(AgendaActivity.this, android.R.layout.simple_list_item_1, agendas);
        lv_agendas.setAdapter(adapter);

        //Recuperar agenda do firebase
        Preferencias preferencias = new Preferencias(AgendaActivity.this);
        String identificadorUsuarioLogado = preferencias.getIdentificador();
        referenceFirebase = ConfigFirebase.getFirebase().child("agenda").child(identificadorUsuarioLogado);

        //Listener para recuperar contatos
        referenceFirebase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //limpar lista
                agendas.clear();

                //Listar amigos
                for(DataSnapshot dados: dataSnapshot.getChildren()){
                    Agenda agenda = dados.getValue(Agenda.class);
                    adapter.add(agenda.getNomeAgenda());
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lv_agendas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AgendaActivity.this, AbrirAgendaActivity.class);

                //recuperar dados
                Agenda agenda = agendas.get(position);

                //enviar dados para abriragenda
                intent.putExtra("nome", agenda.getNomeAgenda());
                intent.putExtra("importancia", agenda.getImportanciaCompromisso());
                intent.putExtra("descicao", agenda.getConteudoAgenda());
                intent.putExtra("data", agenda.getSemanasAgenda());

                startActivity(intent);
            }
        });
    }

}
