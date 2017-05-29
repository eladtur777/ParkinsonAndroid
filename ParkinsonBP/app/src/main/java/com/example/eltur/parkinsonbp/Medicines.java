package com.example.eltur.parkinsonbp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.GridView;
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
        GridView gv = (GridView) findViewById(R.id.grid);


        // Initializing a new String Array
        String[] plants = new String[]{
                "אקמול 3 פעמים ביום",
                "סירופ שיעול פעמיים ביום",
                "עוד תרופה 3 פעמים ביום"

        };

        final List<String> plantsList = new ArrayList<String>(Arrays.asList(plants));

        // Create a new ArrayAdapter
        final ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1, plantsList);

        // Data bind GridView with ArrayAdapter (String Array elements)
        gv.setAdapter(gridViewArrayAdapter);
    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(Medicines.this, firstpage.class));
        finish();
    }







}

