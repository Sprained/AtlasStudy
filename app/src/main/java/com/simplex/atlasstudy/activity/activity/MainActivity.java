package com.simplex.atlasstudy.activity.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.simplex.atlasstudy.R;
import com.simplex.atlasstudy.activity.activity.config.ConfigFirebase;

public class MainActivity extends AppCompatActivity {

    private Button btnSair;
    private Button btnConquistas;
    private Button btnConfig;
    private Button btnAgendas;
    private Button btnAmigos;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSair = (Button) findViewById(R.id.btnSair);
        btnConquistas = (Button) findViewById(R.id.btnConquistas);
        btnConfig = (Button) findViewById(R.id.btnConfig);
        btnAgendas = (Button) findViewById(R.id.btnAgendas);
        btnAmigos = (Button) findViewById(R.id.btnAmigos);


        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auth = ConfigFirebase.getFirebaseAuth();
                auth.signOut();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
                startActivity(intent);
            }
        });

        btnAgendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AgendaActivity.class);
                startActivity(intent);
            }
        });
        btnAmigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AmigosActivity.class);
                startActivity(intent);
            }
        });
    }
}
