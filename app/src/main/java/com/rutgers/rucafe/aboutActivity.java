package com.rutgers.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents the Activity used to
 * display the functions of the app made.
 *
 * @author Mark Holleran, Abhitej Bokka
 */
public class aboutActivity extends AppCompatActivity {

    public static final String[] DESCRIPTIONS = {
            "This program can...",
            "Customize the Type & Flavor of a Donut",
            "Select Size and Addins of a Coffee",
            "Add and Remove Donut and Coffee Orders to a List",
            "Calculate the Subtotal & Total of the Order",
            "And store All Orders in a Store Orders List",
            "",
            "This Program was made by Abhitej Bokka & Mark Holleran"
    };

    /**
     * Initializes variables and creates Adapter to view descriptions.
     *
     * @param savedInstanceState Bundle just helps pass saved information from previous states.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ListView programDescriptionListView = findViewById(R.id.aboutProgramInformation);
        programDescriptionListView.setBackgroundColor(Color.GRAY);
        ArrayList<String> description = new ArrayList<>();
        Collections.addAll(description, DESCRIPTIONS);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1,description);
        programDescriptionListView.setAdapter(arrayAdapter);
    }
}