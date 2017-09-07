package com.example.eltur.parkinsonbp;

import android.content.Context;
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
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

/**
 * Created by Eltur on 12/06/2017.
 */

public class Sleep extends AppCompatActivity {

    Button btnSave;
    EditText txtBoxHoursNum;
    EditText txtBoxQualitySleep;
    private static ArrayList<String> SleepDisorder;
    String userid ="";
    private CheckBox[] cb;
    ArrayList<String> subMenuSleepQuality = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        btnSave = (Button) findViewById(R.id.btnSleepSave);
        txtBoxHoursNum= (EditText)findViewById(R.id.txtBoxHoursNumber);
        txtBoxQualitySleep =(EditText)findViewById(R.id.txtBoxQualitySleepNumber);
        SleepDisorder = new ArrayList<String>();

       // AddChkBox();
        txtBoxQualitySleep.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    txtBoxQualitySleep.setInputType(0);
                    SleepQualitySubMenu(txtBoxQualitySleep);
                    txtBoxQualitySleep.clearFocus();

                }
                else
                {
                    txtBoxQualitySleep.clearFocus();
                }
            }
        });

       /* txtBoxQualitySleep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(txtBoxQualitySleep.getWindowToken(), 0);
                //SleepQualitySubMenu(txtBoxQualitySleep);
            }
        });*/

        AddChkBox();
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ArrayList<String> userchoice = new ArrayList<String>();

                boolean ischkbox = false;
                for (int i = 0; i < SleepDisorder.size(); i++) {
                    if (cb[i].isChecked()) {
                        String val = cb[i].getText().toString();
                        userchoice.add(val);
                        ischkbox = true;
                    }

                }

                if(!ischkbox)
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(Sleep.this).create();
                    alertDialog.setTitle("הודעת מערכת");
                    alertDialog.setMessage("נא לבחור הפרעות שינה");
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

                connectToDB addDataToDB= new connectToDB();
                userid = getIntent().getStringExtra("EXTRA_SESSION_ID");
                ArrayList<String> arrOfSleepCondition = new ArrayList<String>();
                arrOfSleepCondition.add(txtBoxHoursNum.getText().toString());
                arrOfSleepCondition.add(txtBoxQualitySleep.getText().toString());
                String returnVal = addDataToDB.AddDataToDB(userid,null,null,null,null,userchoice,arrOfSleepCondition);

                if(returnVal == "Success")
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(Sleep.this).create();
                    alertDialog.setTitle("הודעת מערכת");
                    alertDialog.setMessage("המידע נשמר בהצלחה");
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
                    AlertDialog alertDialog = new AlertDialog.Builder(Sleep.this).create();
                    alertDialog.setTitle("הודעת מערכת");
                    alertDialog.setMessage("לא ניתן לעדכן שינה אנא נסה מאוחר יותר");
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
       // SleepDisorder.clear();
        MyFramelaoyout.clearAnimation();

        //TODO:
        SleepDisorder = conn.getAllSleepDisorder();

        cb = new CheckBox[SleepDisorder.size()];
        for (int i = 0; i < SleepDisorder.size(); i++) {
            cb[i] = new CheckBox(this);
            cb[i].setText(SleepDisorder.get(i).toString());
            cb[i].setTextSize(22);
            cb[i].setTextColor(Color.BLACK);
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
            cb[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                   // cb[i].setSelected(false);
                    txtBoxQualitySleep.clearFocus();
                }
            });
            MyFramelaoyout.addView(cb[i]);
        }

      /*  txtBoxQualitySleep.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    txtBoxQualitySleep.setInputType(0);
                    SleepQualitySubMenu(txtBoxQualitySleep);


                }
                else
                {
                    txtBoxQualitySleep.clearFocus();

                }
            }
        });*/


    }

    private void SleepQualitySubMenu(EditText edtxt) {
       // final int ind = index;
        final PopupMenu popup = new PopupMenu(Sleep.this, edtxt);
        popup.getMenuInflater()
                .inflate(R.menu.popup_menu_sleep_quality, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.one:
                        item.setChecked(true);
                        break;
                    case R.id.two:
                        item.setChecked(true);
                        break;
                    case R.id.three:
                        item.setChecked(true);
                        break;
                }
                txtBoxQualitySleep.setHint("איך היית מגדיר/ה את שנת הלילה שלך?"+"["+item.getTitle().toString()+"]");
                subMenuSleepQuality.add(item.getTitle().toString());


                return true;
            }
        });
        InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(txtBoxQualitySleep.getWindowToken(), 0);
        popup.show();
        if(subMenuSleepQuality.isEmpty()) {
            txtBoxQualitySleep.setHint("איך היית מגדיר/ה את שנת הלילה שלך?");
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
