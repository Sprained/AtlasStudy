package com.simplex.atlasstudy.activity.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.simplex.atlasstudy.R;
import com.simplex.atlasstudy.activity.activity.config.ConfigFirebase;
import com.simplex.atlasstudy.activity.activity.helér.Base64Custom;
import com.simplex.atlasstudy.activity.activity.helér.Preferencias;
import com.simplex.atlasstudy.activity.activity.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText login;
    private EditText senha;
    private Button btnLogar;
    private Button btnCadastrar;
    private Usuario usuario;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        verificarUsuarioLogado();

        login = (EditText) findViewById(R.id.txtLoginEsc);
        senha = (EditText) findViewById(R.id.txtSenhaEsc);
        btnLogar = (Button) findViewById(R.id.btnEntrar1);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar1);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTelaCadastrar();
            }
        });

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuario = new Usuario();
                usuario.setEmail(login.getText().toString());
                usuario.setSenha(senha.getText().toString());
                validarLogin();
            }
        });
    }

    private void verificarUsuarioLogado(){
        auth = ConfigFirebase.getFirebaseAuth();
        if(auth.getCurrentUser() != null){
            openTelaPrincipal();
        }
    }

    private void validarLogin(){

        auth = ConfigFirebase.getFirebaseAuth();
        auth.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Preferencias preferencias = new Preferencias(LoginActivity.this);
                    String identificadorUsuarioLogado = Base64Custom.codificarBase64(usuario.getEmail());
                    preferencias.salvarDados(identificadorUsuarioLogado);

                    openTelaPrincipal();
                    Toast.makeText(LoginActivity.this, "Sucesso ao fazer login!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Erro ao fazer login!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void openTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void openTelaCadastrar(){
        Intent intent = new Intent(LoginActivity.this,CadastroActivity.class);
        startActivity(intent);
    }


}
