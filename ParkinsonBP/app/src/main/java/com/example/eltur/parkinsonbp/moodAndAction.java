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

    public Button getBtnClickMe() {
        return btnClickMe;
    }

    public void setBtnClickMe(Button btnClickMe) {
        this.btnClickMe = btnClickMe;
    }

    public CheckBox getChkBoxwolking() {
        return chkBoxwolking;
    }

    public void setChkBoxwolking(CheckBox chkBoxwolking) {
        this.chkBoxwolking = chkBoxwolking;
    }

    public CheckBox getChkBoxswim() {
        return chkBoxswim;
    }

    public void setChkBoxswim(CheckBox chkBoxswim) {
        this.chkBoxswim = chkBoxswim;
    }

    public CheckBox getChkBoxruning() {
        return chkBoxruning;
    }

    public void setChkBoxruning(CheckBox chkBoxruning) {
        this.chkBoxruning = chkBoxruning;
    }

    public CheckBox getChkBoxather() {
        return chkBoxather;
    }

    public void setChkBoxather(CheckBox chkBoxather) {
        this.chkBoxather = chkBoxather;
    }

    public CheckBox getChkBoxsocial() {
        return chkBoxsocial;
    }

    public void setChkBoxsocial(CheckBox chkBoxsocial) {
        this.chkBoxsocial = chkBoxsocial;
    }

    public CheckBox getChkBoxhomework() {
        return chkBoxhomework;
    }

    public void setChkBoxhomework(CheckBox chkBoxhomework) {
        this.chkBoxhomework = chkBoxhomework;
    }

    public CheckBox getChkBoxDoctorVisit() {
        return chkBoxDoctorVisit;
    }

    public void setChkBoxDoctorVisit(CheckBox chkBoxDoctorVisit) {
        this.chkBoxDoctorVisit = chkBoxDoctorVisit;
    }

    public CheckBox getChkBoxbike() {
        return chkBoxbike;
    }

    public void setChkBoxbike(CheckBox chkBoxbike) {
        this.chkBoxbike = chkBoxbike;
    }

    public ArrayList<String> getActivities() {
        return Activities;
    }

    public void setActivities(ArrayList<String> activities) {
        Activities = activities;
    }

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
               btnClickMe.setText("נלחץ");
                //connect to server and get all activities into list
                Activities =  connectToDB.GetAllActivitiesFromServer();
              //  for(int i=0 ; i < Activities.size();i++) {
                //   chkBoxwolking.setText(Activities.get(i).toString());

                //}

            }
        });

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