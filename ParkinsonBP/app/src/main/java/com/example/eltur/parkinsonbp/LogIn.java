package com.example.eltur.parkinsonbp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class LogIn extends AppCompatActivity {

    Button EnterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EnterButton = (Button) findViewById(R.id.button3);

        EnterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(LogIn.this, firstpage.class);
                startActivity(i);
                finish();


            }
        });

    }

}


