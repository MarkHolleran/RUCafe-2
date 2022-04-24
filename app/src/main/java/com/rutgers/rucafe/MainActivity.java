package com.rutgers.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

/**
 * This class represents the Main Activity used to act as a
 * home screen to the app and showcase the different activities
 * that can be performed to create a successful order.
 *
 * @author Mark Holleran, Abhitej Bokka
 */
public class MainActivity extends AppCompatActivity {

    private ImageView donutMainMenuImage;
    private ImageView x;
    private ImageView c;
    private ImageView z;

    /**
     * Initializes variables and creates Buttons to view Activities to order Items.
     *
     * @param savedInstanceState Bundle just helps pass saved information from previous states.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // donutMainMenuImage.setImageResource(R.drawable.donutmainmenuimage);

        Button donutOrderButton = findViewById(R.id.OrderDonutButton);
        donutOrderButton.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, donut_order.class)));

        Button coffeeOrderButton = findViewById(R.id.OrderCoffeeButton);
        coffeeOrderButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, coffee_order.class);
            startActivity(intent);
        });

        Button currentOrderButton = findViewById(R.id.CurrentOrderButton);
        currentOrderButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, current_order.class);
            startActivity(intent);
        });

        Button allStoreOrdersButton = findViewById(R.id.AllOrdersButton);
        allStoreOrdersButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, store_orders.class);
            startActivity(intent);
        });

        Button aboutProgram = findViewById(R.id.aboutButton);
        aboutProgram.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, aboutActivity.class)));
        // donutMainMenuImage.setImageResource(R.drawable.donutmainmenuimage);

    }

}
