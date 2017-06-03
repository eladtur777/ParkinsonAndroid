package com.example.eltur.parkinsonbp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableRow;

import com.example.eltur.parkinsonbp.HttpClient.HttpClient;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;

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
   private CheckBox[] cb;
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

        btnClickMe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            ArrayList<String> userchoice = new ArrayList<String>();
                userchoice.clear();
                boolean ischkbox = false;
                for (int i = 0; i < Activities.size(); i++) {
                    if (cb[i].isChecked()) {
                        String val = cb[i].getText().toString();
                        userchoice.add(val);
                        ischkbox = true;
                    }
                }

                if(!ischkbox)
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(moodAndAction.this).create();
                    alertDialog.setTitle("הודעת מערכת");
                    alertDialog.setMessage("נא לבחור פעילויות");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                   // Intent i = new Intent(moodAndAction.this, firstpage.class);
                                  // startActivity(i);
                                   // finish();
                                }
                            });
                    alertDialog.show();
                    return;
                }
                    connectToDB addactivities= new connectToDB();
                    String returnVal = addactivities.AddActivities("1",userchoice);
                    userchoice.clear();
                    if(returnVal == "Success")
                    {
                        AlertDialog alertDialog = new AlertDialog.Builder(moodAndAction.this).create();
                        alertDialog.setTitle("הודעת מערכת");
                        alertDialog.setMessage("הפעילויות נשמרו בהצלחה");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        Intent i = new Intent(moodAndAction.this, firstpage.class);
                                        startActivity(i);
                                        finish();
                                    }
                                });
                        alertDialog.show();
                    }

                }

        });

    }

    public void AddChkBox()
    {
        final LinearLayout  MyFramelaoyout = (LinearLayout )findViewById(R.id.Linearlayout);
        connectToDB conn= new connectToDB();
        Activities.clear();
        Activities = conn.getAllActivies();
        cb = new CheckBox[Activities.size()];
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