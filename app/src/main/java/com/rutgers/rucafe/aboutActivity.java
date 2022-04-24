package com.rutgers.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class aboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ListView programDescriptionListView = findViewById(R.id.aboutProgramInformation);
        programDescriptionListView.setBackgroundColor(Color.GRAY);

        ArrayList<String> description = new ArrayList<>();
        description.add("This program can...");
        description.add("Customize the Type & Flavor of a Donut");
        description.add("Select Size and Addins of a Coffee");
        description.add("Add and Remove Donut and Coffee Orders to a List");
        description.add("Calculate the Subtotal & Total of the Order");
        description.add("and store All Orders in a Store Orders List");

        description.add("");
        description.add("This Program was made by Abhitej Bokka & Mark Holleran");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_list_item_1,description);
        programDescriptionListView.setAdapter(arrayAdapter);

    }
}