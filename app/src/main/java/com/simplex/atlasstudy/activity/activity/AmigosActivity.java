package com.simplex.atlasstudy.activity.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.simplex.atlasstudy.R;
import com.simplex.atlasstudy.activity.activity.config.ConfigFirebase;
import com.simplex.atlasstudy.activity.activity.helér.Base64Custom;
import com.simplex.atlasstudy.activity.activity.helér.Preferencias;
import com.simplex.atlasstudy.activity.activity.model.Contato;
import com.simplex.atlasstudy.activity.activity.model.Usuario;

import java.util.ArrayList;

/**
 * Created by Jonatas on 04/11/2017.
 */

public class AmigosActivity extends AppCompatActivity {

    private Button btnVoltar;
    private Button btnAddFriend;

    private ListView lv_amigos;
    private ArrayAdapter adapter;
    private ArrayList<String> amigos;

    private String identificadorContato;
    private DatabaseReference referenceFirebase;
    private FirebaseAuth authentication;

    @Override
    protected void onCreate (Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_amigos);

        authentication = ConfigFirebase.getFirebaseAuth();

        btnVoltar = (Button) findViewById(R.id.btnVoltarAmigos);
        btnAddFriend = (Button) findViewById(R.id.btnAddFriend);

        //Intanciar array
        amigos = new ArrayList<>();

        //Montagem listview e adapter
        lv_amigos = (ListView) findViewById(R.id.lv_amigos);
        adapter = new ArrayAdapter(AmigosActivity.this, android.R.layout.simple_list_item_1, amigos);
        lv_amigos.setAdapter(adapter);

        //recuperar amigos firebase
        Preferencias preferencias = new Preferencias(AmigosActivity.this);
        String identificadorUsuarioLogado = preferencias.getIdentificador();
        referenceFirebase = ConfigFirebase.getFirebase().child("contato").child(identificadorUsuarioLogado);

        //listener para recuperar contatos
        referenceFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Limpar lista
                amigos.clear();

                //Liastar amigos
                for(DataSnapshot dados: dataSnapshot.getChildren()){
                    Contato contato = dados.getValue(Contato.class);
                    amigos.add(contato.getNome());
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //passagem de activity
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AmigosActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnAddFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AmigosActivity.this);

                //Configuração do Dialog
                alertDialog.setTitle("Novo Amigo");
                alertDialog.setMessage("E-mail do usuário");
                alertDialog.setCancelable(false);

                final EditText editText = new EditText(AmigosActivity.this);
                alertDialog.setView(editText);

                //configurar botões
                alertDialog.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String emailContato = editText.getText().toString();

                        //Validar se email foi digitado
                        if(emailContato.isEmpty()){
                            Toast.makeText(AmigosActivity.this, "Preencha o e-mail", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            //Verifica se usuario consta no Firebase
                            identificadorContato = Base64Custom.codificarBase64(emailContato);

                            //Recuperar instancia Firebase
                            referenceFirebase = ConfigFirebase.getFirebase().child("usuarios").child(identificadorContato);

                            referenceFirebase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    if(dataSnapshot.getValue() != null){
                                        //Recuperar dados do contato a ser add
                                        Usuario usuarioContato = dataSnapshot.getValue(Usuario.class);

                                        //Recuperar identificador
                                        Preferencias preferencias = new Preferencias(AmigosActivity.this);
                                        String identificadorUsuarioLogado = preferencias.getIdentificador();

                                        referenceFirebase = ConfigFirebase.getFirebase();
                                        referenceFirebase = referenceFirebase.child("contato").child(identificadorUsuarioLogado).child(identificadorContato);
                                        Contato contato = new Contato();
                                        contato.setIdentificadorUsuario(identificadorContato);
                                        contato.setEmail(usuarioContato.getEmail());
                                        contato.setNome(usuarioContato.getName());

                                        referenceFirebase.setValue(contato);
                                    }
                                    else{
                                        Toast.makeText(AmigosActivity.this, "Usuário não possue cadastro.", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }

                    }
                });

                alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialog.create();
                alertDialog.show();
            }
        });
    }
}
