package com.rutgers.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.button = findViewById(R.id.OrderDonutButton);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                startActivity(new Intent(MainActivity.this, DonutActivity.class));

            }
        });

    }
}