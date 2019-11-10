package com.simplex.atlasstudy.activity.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.simplex.atlasstudy.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Men on 19/10/2017.
 */

public class ConfigActivity extends AppCompatActivity {

        private Button btnVoltarConquista;

        @Override
        protected void onCreate(Bundle saveInstanceState){
            super.onCreate(saveInstanceState);
            setContentView(R.layout.activity_config);

            btnVoltarConquista = (Button) findViewById(R.id.btnVoltarConfig);

            btnVoltarConquista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ConfigActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
