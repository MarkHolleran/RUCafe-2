package com.rutgers.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class DonutActivity extends AppCompatActivity {

    private ArrayList<Donut> donuts = new ArrayList<>();

    private int [] donutImages = {R.drawable.apple, R.drawable.banana, R.drawable.grapes,
            R.drawable.mango, R.drawable.orange, R.drawable.peach, R.drawable.pineapple,
            R.drawable.strawberry, R.drawable.strawberry, R.drawable.strawberry, R.drawable.strawberry, R.drawable.strawberry};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rcview = findViewById(R.id.rcView_menu);
        setupMenuItems();
        donutAdapter adapter = new donutAdapter(this,donuts);
        rcview.setAdapter(adapter);
        rcview.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setupMenuItems() {

        String [] donutNames = getResources().getStringArray(R.array.donut_names);
        String [] donutTypes = getResources().getStringArray(R.array.donut_types);
        /* Add the items to the ArrayList.
         * Note that I use hardcoded prices for demo purpose. This should be replace by other
         * data sources.
         */
        for (int i = 0; i < donutNames.length; i++) {
            donuts.add(new Donut(donutNames[i] ,donutNames[i]  ,donutImages[i] ));
        }
    }

}