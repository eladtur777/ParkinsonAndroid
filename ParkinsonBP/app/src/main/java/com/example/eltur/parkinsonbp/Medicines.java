package com.example.eltur.parkinsonbp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class Medicines extends AppCompatActivity {
    Button btnSave;
    private static ArrayList<String> medicine;
    String userid ="";
    private CheckBox[] cb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);

        btnSave = (Button) findViewById(R.id.btnSaveMedicine);
        medicine = new ArrayList<String>();
        AddChkBox();

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ArrayList<String> userchoice = new ArrayList<String>();
                //userchoice.clear();
                boolean ischkbox = false;
                for (int i = 0; i < medicine.size(); i++) {
                    if (cb[i].isChecked()) {
                        String val = cb[i].getText().toString();
                        userchoice.add(val);
                        ischkbox = true;
                    }
                }

                if(!ischkbox)
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(Medicines.this).create();
                    alertDialog.setTitle("הודעת מערכת");
                    alertDialog.setMessage("נא לבחור תרופות");
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


                //TODO:
                connectToDB addDataToDB= new connectToDB();

                userid = getIntent().getStringExtra("EXTRA_SESSION_ID");
                String returnVal = addDataToDB.AddDataToDB(userid,null,null,userchoice,null,null,null);


                if(returnVal == "Success")
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(Medicines.this).create();
                    alertDialog.setTitle("הודעת מערכת");
                    alertDialog.setMessage("הבחירה נשמרה בהצלחה");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(getBaseContext(), firstpage.class);
                                    intent.putExtra("EXTRA_SESSION_ID", userid);
                                    startActivity(intent);
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                        finishAffinity();
                                    }
                                }
                            });
                    alertDialog.show();
                }
                else if(returnVal == "Faild")
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(Medicines.this).create();
                    alertDialog.setTitle("הודעת מערכת");
                    alertDialog.setMessage("לא ניתן לעדכן תרופות אנא נסה מאוחר יותר");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(getBaseContext(), firstpage.class);
                                    intent.putExtra("EXTRA_SESSION_ID", userid);
                                    startActivity(intent);
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
        final LinearLayout MyFramelaoyout = (LinearLayout )findViewById(R.id.Linearlayout);
        connectToDB conn= new connectToDB();
        //medicine.clear();
       // MyFramelaoyout.clearAnimation();

        //TODO:
        medicine = conn.getAllMedicines();

        cb = new CheckBox[medicine.size()];
        for (int i = 0; i < medicine.size(); i++) {
            cb[i] = new CheckBox(this);
            cb[i].setTextColor(Color.BLACK);
            cb[i].setText(medicine.get(i).toString());
            cb[i].setTextSize(22);
            cb[i].setId(i + 6);
            int states[][] = {{android.R.attr.state_checked}, {}};
            int colors[] = {Color.BLACK, Color.parseColor("#00793c")};
            CompoundButtonCompat.setButtonTintList(cb[i], new ColorStateList(states, colors));
            if(i%2 !=0) {
                cb[i].setBackgroundColor(Color.parseColor("#E8F5E9"));
            }
            else
            {
                cb[i].setBackgroundColor(Color.parseColor("#E0E0E0"));

            }
            MyFramelaoyout.addView(cb[i]);
        }
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getBaseContext(), firstpage.class);
        intent.putExtra("EXTRA_SESSION_ID", getIntent().getStringExtra("EXTRA_SESSION_ID"));
        startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            finishAffinity();
        }
    }

}






