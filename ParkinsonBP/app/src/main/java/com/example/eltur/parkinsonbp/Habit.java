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
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.eltur.parkinsonbp.HttpClient.HttpClient;
import com.example.eltur.parkinsonbp.ServerClass.HabitUpdate;
import com.example.eltur.parkinsonbp.ServerClass.SubMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Eltur on 12/06/2017.
 */

public class Habit extends AppCompatActivity {
    Button btnSave;
    private HabitUpdate[] ha;
    private static ArrayList<String> Hergelim;
    String userid ="";
    private CheckBox[] cb;
    private  CheckBox[] cb1;
    private int Jindex=0;
    private int JindexForMassege =0;
    ArrayList<String> subMenuCoffe = new ArrayList<String>();
    ArrayList<String> subMenuTea = new ArrayList<String>();
    ArrayList<String> subMenuCola = new ArrayList<String>();
    ArrayList<String> subMenuCigarettes = new ArrayList<String>();
    private static ArrayList<String> consumption;
    private static ArrayList<String> finalconsumption;
    ArrayList<SubMenu> subMenuItems = new ArrayList<SubMenu>();
    ArrayList<String> subMenuClearItems = new ArrayList<>();
    ArrayList<String> subMenuForUpdate = new ArrayList<>(100);
    private List<List<SubMenu>> subMenuList1 = new ArrayList<List<SubMenu>>();
    private  ArrayList<Long> subMenuGroupId = new ArrayList<Long>();
    ArrayList<SubMenu> subMenuItemsLowermenu = new ArrayList<SubMenu>();
    private List<List<SubMenu>> subMenuList2 = new ArrayList<List<SubMenu>>();
    ArrayList<String> subMenuForUpdate2 = new ArrayList<>(100);
    private boolean IsCheckedFromSubMenuUpper = false;
    private boolean IsCheckedFromSubMenuLower = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hergelim);
        btnSave = (Button) findViewById(R.id.btnSaveHergelim);
        Hergelim = new ArrayList<String>();
        consumption = new ArrayList<String>();
        finalconsumption =  new ArrayList<String>();
        AddChkBox();
        ha = new HabitUpdate[Hergelim.size()];

        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ArrayList<String> userchoice = new ArrayList<String>();
               // userchoice.clear();
                String subString = "";
                boolean ischkbox = false;
                int getindex = 0;
                for (int i = 0; i < consumption.size(); i++) {
                    if (cb[i].isChecked()) {
                        ha[i] = new HabitUpdate();
                        ha[i].setHabitName(consumption.get(i).toString());
                        ha[i].setHabitDescription(subMenuForUpdate.get(i).toString());
                        getindex = i;
                        ischkbox = true;
                    }
                }
                getindex += 1;
                for (int i = 0; i < finalconsumption.size(); i++) {
                    if (cb[i].isChecked()) {
                        ha[getindex] = new HabitUpdate();
                        ha[getindex].setHabitName(finalconsumption.get(i).toString());
                        ha[getindex].setHabitDescription(subMenuForUpdate2.get(i).toString());
                        getindex += 1;
                        ischkbox = true;
                    }
                }

                if(!ischkbox)
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(Habit.this).create();
                    alertDialog.setTitle("הודעת מערכת");
                    alertDialog.setMessage("נא לבחור הרגלים");
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
               // String returnVal = addactivities.AddDataToDB(userid,ac,null,null,null,null,null);
                String returnVal = addDataToDB.AddDataToDB(userid,null,ha,null,null,null,null);


                if(returnVal == "Success")
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(Habit.this).create();
                    alertDialog.setTitle("הודעת מערכת");
                    alertDialog.setMessage("הרגלים נשמרו בהצלחה");
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
                                   // finish();
                                }
                            });
                    alertDialog.show();
                }

                else if(returnVal == "Faild")
                {
                    AlertDialog alertDialog = new AlertDialog.Builder(Habit.this).create();
                    alertDialog.setTitle("הודעת מערכת");
                    alertDialog.setMessage("לא ניתן לעדכן הרגלים אנא נסה מאוחר יותר");
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
        final LinearLayout MyFramelaoyoutHabit = (LinearLayout )findViewById(R.id.LinearlayoutHabit);
        connectToDB conn= new connectToDB();

        Hergelim = conn.getAllHergelim();
        subMenuGroupId = HttpClient.getSubMenuListHabitMenuGroupId();
        //finalconsumption.add("מניטול");
        //finalconsumption.add("סיגריות");
        //finalconsumption.add("קנאביס רפואי");

        for (int i = 0; i < Hergelim.size(); i++) {
            //if((!Hergelim.get(i).toString().equals("מניטול")))
              //  if((!Hergelim.get(i).toString().equals("סיגריות")))
                    if(subMenuGroupId.get(i).toString().equals("0"))

            {   //upper menu
                consumption.add(Hergelim.get(i).toString());
            }
            if(subMenuGroupId.get(i).toString().equals("1"))
            {
                //lower menu
                finalconsumption.add(Hergelim.get(i).toString());
            }
        }



        cb = new CheckBox[consumption.size()];
        for (int i = 0; i < consumption.size(); i++) {
                //region CheckBox Style

                cb[i] = new CheckBox(this);
                cb[i].setText(consumption.get(i).toString());
                cb[i].setTextSize(22);
                cb[i].setId(i + 6);
                cb[i].setTextColor(Color.BLACK);
                int states[][] = {{android.R.attr.state_checked}, {}};
                int colors[] = {Color.BLACK, Color.parseColor("#00793c")};
                CompoundButtonCompat.setButtonTintList(cb[i], new ColorStateList(states, colors));
                if (i % 2 != 0) {
                    cb[i].setBackgroundColor(Color.parseColor("#E8F5E9"));
                } else {
                    cb[i].setBackgroundColor(Color.parseColor("#E0E0E0"));

                }
                //endregion

                //region OnClickListener CheckBox
            final int finalI = i;
                cb[i].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        for (int j = 0; j < consumption.size(); j++) {

                            if (cb[finalI].isChecked()) {
                                if(!subMenuList1.get(finalI).isEmpty()) {
                                    subMenuItems.addAll(subMenuList1.get(finalI));
                                    if (subMenuItems.isEmpty()) {
                                    }
                                    else {
                                        getPopUpSubMenu(cb, finalI);
                                    }
                                    subMenuItems.clear();
                                }
                            }
                            else
                            {
                                cb[finalI].setText(consumption.get(finalI).toString());
                                if(!subMenuForUpdate.get(finalI).isEmpty())
                                    subMenuForUpdate.remove(finalI);
                            }



                            /*
                           //region COFFEE
                            if (cb[j].isChecked() && consumption.get(j).toString().equals("קפה") && !IsCheckedFlagCoffee) {
                                IsCheckedFlagCoffee = true;
                                HabitSubMenu(cb, j, Menu.COFFE);
                            }
                            if (!cb[j].isChecked() && consumption.get(j).toString().equals("קפה")) {
                                cb[j].setText("קפה");
                                IsCheckedFlagCoffee = false;
                                if (!subMenuCoffe.isEmpty()) {
                                    subMenuCoffe.remove(0);
                                    cb[j].setText("קפה");
                                }
                            }
                            //endregion


                            //regionTEA
                            if (cb[j].isChecked() && consumption.get(j).toString().equals("תה") && !IsCheckedFlagTea) {
                                IsCheckedFlagTea = true;
                                HabitSubMenu(cb, j, Menu.TEA);
                            }
                            if (!cb[j].isChecked() && consumption.get(j).toString().equals("תה")) {
                                cb[j].setText("תה");
                                IsCheckedFlagTea = false;
                                if (!subMenuTea.isEmpty()) {
                                    subMenuTea.remove(0);
                                    cb[j].setText("תה");
                                }
                            }
                            //endregion


                            //regionCOLA
                            if (cb[j].isChecked() && consumption.get(j).toString().equals("קולה") && !IsCheckedFlagCola) {
                                IsCheckedFlagCola = true;
                                HabitSubMenu(cb, j, Menu.COLA);
                            }
                            if (!cb[j].isChecked() && consumption.get(j).toString().equals("קולה")) {
                                cb[j].setText("קולה");
                                IsCheckedFlagCola = false;
                                if (!subMenuCola.isEmpty()) {
                                    subMenuCola.remove(0);
                                    cb[j].setText("קולה");
                                }
                            }
                            //endregion
                         */
                        }

                    }
                });

                //endregion
                //region LastCheck
               // if (subMenuCoffe.isEmpty() && cb[JindexForMassege].isChecked()) {

                 //   cb[JindexForMassege].setChecked(false);
               // }
                //if (subMenuTea.isEmpty() && cb[JindexForMassege].isChecked()) {

                  //  cb[JindexForMassege].setChecked(false);
                //}
                //if (subMenuCola.isEmpty() && cb[JindexForMassege].isChecked()) {

                  //  cb[JindexForMassege].setChecked(false);
                //}
                //endregion

                // if(!(cb[i].getText().equals("סיגריות") || cb[i].getText().equals("קנאביס רפואי") || cb[i].getText().equals("מניטול")))
                MyFramelaoyoutHabit.addView(cb[i]);



        }

        TextView tv = new TextView(this);
        tv.setText("האם צרכת היום?");
        tv.setTextSize(18);
       // @color/common_google_signin_btn_text_light_default
        tv.setTextColor(Color.GRAY);
        MyFramelaoyoutHabit.addView(tv);
        cb1 = new CheckBox[finalconsumption.size()];

        for (int i = 0; i < finalconsumption.size(); i++) {
            //region CheckBox Style
            cb1[i] = new CheckBox(this);
            cb1[i].setText(finalconsumption.get(i).toString());
            cb1[i].setTextSize(22);
            cb1[i].setId(i + 6);
            cb1[i].setTextColor(Color.BLACK);
            int states[][] = {{android.R.attr.state_checked}, {}};
            int colors[] = {Color.BLACK, Color.parseColor("#00793c")};
            CompoundButtonCompat.setButtonTintList(cb1[i], new ColorStateList(states, colors));
            if (i % 2 != 0) {
                cb1[i].setBackgroundColor(Color.parseColor("#E8F5E9"));
            } else {
                cb1[i].setBackgroundColor(Color.parseColor("#E0E0E0"));

            }
            final int finalIS = i;
            cb1[i].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    for (int j = 0; j < finalconsumption.size(); j++) {

                        if (cb1[finalIS].isChecked()) {
                            if(!subMenuList2.get(finalIS).isEmpty()) {
                                subMenuItemsLowermenu.addAll(subMenuList2.get(finalIS));
                                if (subMenuItemsLowermenu.isEmpty()) {
                                }
                                else {
                                    getPopUpSubMenu(cb1, finalIS);
                                }
                                subMenuItemsLowermenu.clear();
                            }
                        }
                        else
                        {
                            cb1[finalIS].setText(finalconsumption.get(finalIS).toString());
                            if(!subMenuForUpdate2.get(finalIS).isEmpty())
                                subMenuForUpdate2.remove(finalIS);
                        }



                       /*
                        //region SIGARIOT
                        if (cb1[j].isChecked() && finalconsumption.get(j).toString().equals("סיגריות") && !IsCheckedFlagCigarettes) {
                            IsCheckedFlagCigarettes = true;
                            CigarettesSubMenu(cb1, j);
                        }
                        if (!cb1[j].isChecked() && finalconsumption.get(j).toString().equals("סיגריות")) {
                            cb1[j].setText("סיגריות");
                            IsCheckedFlagCigarettes = false;
                            if (!subMenuCigarettes.isEmpty()) {
                                subMenuCigarettes.remove(0);
                                cb1[j].setText("סיגריות");
                            }
                        }
                        //endregion

                     */
                    }
                }
            });


            //region LastCheck
          //  if (subMenuCigarettes.isEmpty() && cb1[JindexForMassege].isChecked()) {

            //    cb1[JindexForMassege].setChecked(false);
           // }
            MyFramelaoyoutHabit.addView(cb1[i]);
        }

    }

    /*private void HabitSubMenu(CheckBox[]cbx, int index, final Menu men) {
        final int ind = index;
        final PopupMenu popup = new PopupMenu(Habit.this, cbx[index]);
        popup.getMenuInflater()
                .inflate(R.menu.popup_menu_habits, popup.getMenu());
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
                switch (men)
                {
                    case COFFE:
                        cb[ind].setText("קפה" + " " + "[" + item.getTitle().toString() + "]");
                        subMenuCoffe.add(item.getTitle().toString());
                        break;
                    case TEA:
                        cb[ind].setText("תה" + " " + "[" + item.getTitle().toString() + "]");
                        subMenuTea.add(item.getTitle().toString());
                        break;
                    case COLA:
                        cb[ind].setText("קולה" + " " + "[" + item.getTitle().toString() + "]");
                        subMenuCola.add(item.getTitle().toString());
                        break;
                }

                return true;
            }
        });
        popup.show();

        switch (men)
        {
            case COFFE:
                if(subMenuCoffe.isEmpty()) {
                    cb[ind].setText("קפה" + " " + "[" + "1-2" + "]");
                }
                break;
            case TEA:
                if(subMenuTea.isEmpty()) {
                    cb[ind].setText("תה" + " " + "[" + "1-2" + "]");
                }
                break;
            case COLA:
                if(subMenuCola.isEmpty()) {
                    cb[ind].setText("קולה" + " " + "[" + "1-2" + "]");
                }
                break;
        }

    }

    private void CigarettesSubMenu(CheckBox[]cbx, int index) {
        final int ind = index;
        final PopupMenu popup = new PopupMenu(Habit.this, cbx[index]);
        popup.getMenuInflater()
                .inflate(R.menu.popup_menu_habits_tzrahim, popup.getMenu());
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

                        cb1[ind].setText("סיגריות" + " " + "[" + item.getTitle().toString() + "]");
                        subMenuCigarettes.add(item.getTitle().toString());


                return true;
            }
        });
                popup.show();
                if(subMenuCigarettes.isEmpty()) {
                    cb1[ind].setText("סיגריות" + " " + "[" + "1-10" + "]");
                }
    }

*/
    private void getPopUpSubMenuLower(CheckBox[]cbx, int index)
    {
        final int ind = index;
        final PopupMenu popup = new PopupMenu(Habit.this, cbx[index]);
        popup.getMenuInflater()
                .inflate(R.menu.popup_menu_activities_general, popup.getMenu());

        subMenuList2 = HttpClient.getSubMenuListActivities();
        subMenuItemsLowermenu.clear();
        subMenuItemsLowermenu.addAll(subMenuList2.get(ind));
        getClearSubMenuList(subMenuItemsLowermenu);
        popup.dismiss();
        //Add Dynamic submenu items
        for(int i = 0; i < subMenuClearItems.size() ;i++) {
            popup.getMenu().add(1, i ,i, subMenuClearItems.get(i).toString());
            //groupId,ItemId,order,title
        }
        IsCheckedFromSubMenuLower =false;
        popup.getMenu().setGroupCheckable(1,true,true);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                item.setChecked(true);
                cb[ind].setText(finalconsumption.get(ind).toString() + " " + "[" + item.getTitle().toString() + "]");
                subMenuForUpdate2.add(ind,item.getTitle().toString());
                IsCheckedFromSubMenuLower = true;
                return true;
            }
        });

        popup.show();
        if(!IsCheckedFromSubMenuLower) {
            getClearSubMenuList(subMenuItems);
            cb[ind].setText(finalconsumption.get(ind).toString()+ " " + "["+subMenuClearItems.get(0).toString()+"]");
            subMenuForUpdate.add(ind,subMenuClearItems.get(0).toString());
        }

    }

    private void getPopUpSubMenu(CheckBox[]cbx, int index)
    {

        final int ind = index;
        final PopupMenu popup = new PopupMenu(Habit.this, cbx[index]);
        popup.getMenuInflater()
                .inflate(R.menu.popup_menu_activities_general, popup.getMenu());

        subMenuList1 = HttpClient.getSubMenuListActivities();
        subMenuItems.clear();
        subMenuItems.addAll(subMenuList1.get(ind));
        getClearSubMenuList(subMenuItems);
        popup.dismiss();
        //Add Dynamic submenu items
        for(int i = 0; i < subMenuClearItems.size() ;i++) {
            popup.getMenu().add(1, i ,i, subMenuClearItems.get(i).toString());
            //groupId,ItemId,order,title
        }
        IsCheckedFromSubMenuUpper =false;
        popup.getMenu().setGroupCheckable(1,true,true);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                item.setChecked(true);
                cb[ind].setText(consumption.get(ind).toString() + " " + "[" + item.getTitle().toString() + "]");
                subMenuForUpdate.add(ind,item.getTitle().toString());
                IsCheckedFromSubMenuUpper = true;
                return true;
            }
        });

        popup.show();
        if(!IsCheckedFromSubMenuUpper) {
            getClearSubMenuList(subMenuItems);
            cb[ind].setText(finalconsumption.get(ind).toString()+ " " + "["+subMenuClearItems.get(0).toString()+"]");
            subMenuForUpdate.add(ind,subMenuClearItems.get(0).toString());
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


    private void getClearSubMenuList(ArrayList<SubMenu> SuBmenuList)
    {
        subMenuClearItems.clear();
        for(int i =0;i<SuBmenuList.size();i++)
        {
            String temp = SuBmenuList.get(i).toString().substring(9,SuBmenuList.get(i).toString().length()-1);
            String subMenuItemsForScreen = temp.replaceAll("^\"|\"$", "");
            subMenuClearItems.add(subMenuItemsForScreen);
        }
    }
    }
