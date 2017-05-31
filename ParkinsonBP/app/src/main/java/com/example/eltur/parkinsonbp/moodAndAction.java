package com.example.eltur.parkinsonbp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableRow;

import com.example.eltur.parkinsonbp.HttpClient.HttpClient;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class moodAndAction extends AppCompatActivity {

    public Button getBtnClickMe() {
        return btnClickMe;
    }

    public void setBtnClickMe(Button btnClickMe) {
        this.btnClickMe = btnClickMe;
    }

    public ArrayList<String> getActivities() {
        return Activities;
    }


    public void setActivities(ArrayList<String> activities) {
        Activities = activities;
    }
   private CheckBox[] cb = new CheckBox[6];
    Button btnClickMe;
    private static ArrayList<String> Activities = new ArrayList<>();

   // private ArrayList<String> Activities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        btnClickMe = (Button) findViewById(R.id.btnSaveActivity);
        Activities = new ArrayList<String>();
        AddChkBox();

      //  cb.setOnClickListener(new View.OnClickListener() {
          //  public void onClick(View v) {
           //     if(cb.isChecked()) {
             //       Activities.add(cb.getText().toString());
             //   }
               // else
               // {
               //     Activities.remove(cb.getText().toString());
               // }
      //      }
      //  });


        btnClickMe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               btnClickMe.setText("נלחץ");

               // saveAnswers();

            }
        });

    }

    public void AddChkBox()
    {
        final LinearLayout  MyFramelaoyout = (LinearLayout )findViewById(R.id.Linearlayout);
        connectToDB conn= new connectToDB();
        Activities = conn.getAllActivies();
        CheckBox[] cb = new CheckBox[Activities.size()];
        for (int i = 0; i < Activities.size(); i++) {
            cb[i] = new CheckBox(this);
            MyFramelaoyout.addView(cb[i]);
            cb[i].setText(Activities.get(i).toString());
            cb[i].setTextSize(25);
            cb[i].setId(i + 6);

        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(moodAndAction.this, firstpage.class));
        finish();
    }

    public ArrayList<String> getActivityList() {
        return this.Activities;
    }

}