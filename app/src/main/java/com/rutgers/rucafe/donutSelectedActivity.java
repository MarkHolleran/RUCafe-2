package com.rutgers.rucafe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class donutSelectedActivity extends AppCompatActivity {
    private Button btn_donutName;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_selected);
        btn_donutName = findViewById(R.id.btn1);
        Intent intent = getIntent();
        btn_donutName.setText(intent.getStringExtra("DONUT"));
    }
}
