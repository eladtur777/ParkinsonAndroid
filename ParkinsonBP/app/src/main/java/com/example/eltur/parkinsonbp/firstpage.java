package com.example.eltur.parkinsonbp;

import android.widget.ImageButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;


public class firstpage extends AppCompatActivity {

    ImageButton ImageButton1;
    ImageButton ImageButton2;
    ImageButton Imagebutton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);
        ImageButton1 = (ImageButton) findViewById(R.id.imageButton);
        ImageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        Imagebutton3 = (ImageButton) findViewById(R.id.imageButton4);

        ImageButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(firstpage.this, moodAndAction.class);
                startActivity(i);
                finish();


                    }
                });

        ImageButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(firstpage.this, information.class);
                startActivity(i);
                finish();
            }
        });


        Imagebutton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(firstpage.this, Medicines.class);
                startActivity(i);
                finish();
            }
        });


    }
}
