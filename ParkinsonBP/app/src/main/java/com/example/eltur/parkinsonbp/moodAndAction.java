package com.example.eltur.parkinsonbp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import javax.net.ssl.HttpsURLConnection;

public class moodAndAction extends AppCompatActivity  {

    Button btnClickMe;
    CheckBox chkBoxwolking;
    CheckBox chkBoxswim;
    CheckBox chkBoxruning;
    CheckBox chkBoxather;
    CheckBox chkBoxsocial;
    CheckBox chkBoxhomework;
    CheckBox chkBoxDoctorVisit;
    CheckBox chkBoxbike;
    private ArrayList<String> Activities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        btnClickMe = (Button) findViewById(R.id.btnSaveActivity);
        chkBoxwolking = (CheckBox) findViewById(R.id.checkBox_wolking);
        chkBoxruning = (CheckBox) findViewById(R.id.checkBox_running);
        chkBoxswim = (CheckBox) findViewById(R.id.checkBox_swim);
        chkBoxather = (CheckBox) findViewById(R.id.checkBox_ather);
        chkBoxsocial = (CheckBox) findViewById(R.id.checkBox_social);
        chkBoxhomework = (CheckBox) findViewById(R.id.checkBox_Homework);
        chkBoxDoctorVisit = (CheckBox) findViewById(R.id.checkBox_visitDoctor);
        chkBoxbike = (CheckBox) findViewById(R.id.checkBox_bike);
        Activities = new ArrayList<String>();


        chkBoxwolking.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(chkBoxwolking.isChecked()) {
                    Activities.add(chkBoxwolking.getText().toString());
                }
                else
                {
                    Activities.remove(chkBoxwolking.getText().toString());
                }
            }
        });

        chkBoxruning.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(chkBoxruning.isChecked()) {
                    Activities.add(chkBoxruning.getText().toString());
                }
                else
                {
                    Activities.remove(chkBoxruning.getText().toString());
                }
            }
        });

        chkBoxswim.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(chkBoxswim.isChecked()) {
                    Activities.add(chkBoxswim.getText().toString());
                }
                else
                {
                    Activities.remove(chkBoxswim.getText().toString());
                }
            }
        });
        chkBoxather.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(chkBoxather.isChecked()) {
                    Activities.add(chkBoxather.getText().toString());
                }
                else
                {
                    Activities.remove(chkBoxather.getText().toString());
                }
            }
        });
        chkBoxsocial.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(chkBoxsocial.isChecked()) {
                    Activities.add(chkBoxsocial.getText().toString());
                }
                else
                {
                    Activities.remove(chkBoxsocial.getText().toString());
                }
            }
        });
        chkBoxhomework.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(chkBoxhomework.isChecked()) {
                    Activities.add(chkBoxhomework.getText().toString());
                }
                else
                {
                    Activities.remove(chkBoxruning.getText().toString());
                }
            }
        });
        chkBoxDoctorVisit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(chkBoxDoctorVisit.isChecked()) {
                    Activities.add(chkBoxDoctorVisit.getText().toString());
                }
                else
                {
                    Activities.remove(chkBoxDoctorVisit.getText().toString());
                }
            }
        });
        chkBoxbike.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(chkBoxbike.isChecked()) {
                    Activities.add(chkBoxbike.getText().toString());
                }
                else
                {
                    Activities.remove(chkBoxbike.getText().toString());
                }
            }
        });

        btnClickMe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //connect to server
                 connectToDB conn = new connectToDB();
                 conn.AddActivities("1");
                btnClickMe.setText("נשמר בהצלחה");

            }
        });

    }

    private String getQuery(ArrayList<String> activities)
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (int i=0; i<activities.size();i++)
        {
            if (!activities.get(i).isEmpty())
            {
                result.append("name:");
                result.append(activities.get(i).toString());
                result.append(",");
            }
          //  {name:%s,activityType:%s,activityLemitation:%s}

       }

        return result.toString();
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