package com.example.eltur.parkinsonbp;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Medicines extends AppCompatActivity {
    GridView myGrid;
    List<String> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);
      //  GridView gv = (GridView) findViewById(R.id.grid);

        // Get the widgets reference from XML layout
        GridView gv = (GridView) findViewById(R.id.gv);

        // Initializing a new String Array
        String[] plants = new String[]{
                "רקוויפ",
                "קומטן",
                "סיפרול",
                "אפוקין/ אפו-גו",
                " ארטן",
                "דקינט",
                "פ.ק.מרץ",
                "אזילקט",
                "אלדפריל / ג'ומקס",
                "Stalevo"
        };

        // Populate a List from Array elements
        final List<String> plantsList = new ArrayList<String>(Arrays.asList(plants));

        // Data bind GridView with ArrayAdapter (String Array elements)
        gv.setAdapter(new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, plantsList){

            public View getView(int position, View convertView, ViewGroup parent) {

                // Return the GridView current item as a View
                View view = super.getView(position,convertView,parent);

                // Convert the view as a TextView widget
                final TextView tv = (TextView) view;
                tv.setTextColor(Color.BLUE);

                // Set the layout parameters for TextView widget
                RelativeLayout.LayoutParams lp =  new RelativeLayout.LayoutParams(
                        ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT
                );
                tv.setLayoutParams(lp);


                // Get the TextView LayoutParams
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)tv.getLayoutParams();

                params.width = getPixelsFromDPs(Medicines.this,188);

                // Set the TextView layout parameters
                tv.setLayoutParams(params);

                // Display TextView text in center position
                tv.setGravity(Gravity.CENTER);

                // Set the TextView text font family and text size
                tv.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

                // Set the TextView text (GridView item text)
                tv.setText(plantsList.get(position));

                // Set the TextView background color
                tv.setBackgroundColor(Color.WHITE);

                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(tv.getCurrentTextColor() == Color.RED)
                        {
                            tv.setTextColor(Color.BLUE);
                        }
                        else
                        {tv.setTextColor(Color.RED);}
                    }
                });
                // Return the TextView widget as GridView item
                return tv;
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Medicines.this, firstpage.class));
        finish();
    }

    // Method for converting DP value to pixels
    public static int getPixelsFromDPs(AppCompatActivity activity, int dps){
        Resources r = activity.getResources();
        int  px = (int) (TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dps, r.getDisplayMetrics()));
        return px;
    }
}






