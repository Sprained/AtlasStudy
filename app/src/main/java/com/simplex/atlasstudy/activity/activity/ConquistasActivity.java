package com.simplex.atlasstudy.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.simplex.atlasstudy.R;

/**
 * Created by Men on 18/10/2017.
 */

public class ConquistasActivity extends AppCompatActivity {

    Button btnVoltar;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_conquistas);

        btnVoltar = (Button) findViewById(R.id.btnVoltarConquistas);

        //passagem de activity
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConquistasActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
