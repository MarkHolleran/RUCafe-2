package com.rutgers.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

import android.os.Bundle;

public class donut_order extends AppCompatActivity {

    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_order);

        addButton = findViewById(R.id.donutAdd);
    }
}