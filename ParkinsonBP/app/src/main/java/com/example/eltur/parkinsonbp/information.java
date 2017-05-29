package com.example.eltur.parkinsonbp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.Toast;
import android.view.View;
import android.view.KeyEvent;
import android.widget.Button;


public class information extends AppCompatActivity {

    WebView myWebView;
    Button backToMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        backToMenuButton = (Button) findViewById(R.id.button2);
        myWebView = (WebView) findViewById(R.id.webview1);
        myWebView.loadUrl("http://www.parkinson.org.il/%D7%97%D7%95%D7%9C%D7%99%D7%9D-%D7%97%D7%93%D7%A9%D7%99%D7%9D");

        backToMenuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(information.this, firstpage.class);
                startActivity(i);
                finish();


            }
        });

    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(information.this, firstpage.class));
        finish();
    }

}


