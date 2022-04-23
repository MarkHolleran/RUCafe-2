package com.rutgers.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button donutOrderButton;
    private Button allStoreOrdersButton;
    private Button coffeeOrderButton;
    private Button currentOrderButton;
    private Button aboutProgram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.donutOrderButton = findViewById(R.id.OrderDonutButton);
        donutOrderButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, donut_order.class));
            }
        });

        this.coffeeOrderButton = findViewById(R.id.OrderCoffeeButton);
        coffeeOrderButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public  void onClick(View v){
                Intent intent = new Intent(MainActivity.this, coffee_order.class);
                startActivity(intent);
            }
        });

        this.currentOrderButton = findViewById(R.id.CurrentOrderButton);
        currentOrderButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public  void onClick(View v){
                Intent intent = new Intent(MainActivity.this, current_order.class);
                startActivity(intent);
            }
        });

        this.allStoreOrdersButton = findViewById(R.id.AllOrdersButton);
        allStoreOrdersButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public  void onClick(View v){
                Intent intent = new Intent(MainActivity.this, store_orders.class);
                startActivity(intent);
            }
        });

        this.aboutProgram = findViewById(R.id.aboutButton);
        aboutProgram.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, aboutActivity.class));
            }
        });
    }
}