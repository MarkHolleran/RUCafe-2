package com.rutgers.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class store_orders extends AppCompatActivity {

    public static StoreOrders storeOrders = new StoreOrders();

    private RecyclerView itemList;
    private coffeeAdapt itemAdapter;
    private RecyclerView.LayoutManager itemLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);;


        createViews();

        itemAdapter.setOnItemClickListener(new coffeeAdapt.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {







                removeItem(position);
            }
        });


        //itemAdapter.notifyDataSetChanged();
    }


    public void createViews(){

        itemList = findViewById(R.id.ordersListView);
        itemList.setHasFixedSize(true);
        itemLayout = new LinearLayoutManager(this);
        itemAdapter = new coffeeAdapt(storeOrders.getOrderList());

        itemList.setLayoutManager(itemLayout);
        itemList.setAdapter(itemAdapter);


    }

    public void removeItem(int position) {

        System.out.println("Before: "+ storeOrders.getOrderList());



        storeOrders.getOrderList().remove(storeOrders.getOrderList().get(position));


        System.out.println("After: "+ storeOrders.getOrderList());
        itemAdapter.notifyItemRemoved(position);

        finish();
        startActivity(getIntent());
        //OP Strategy


    }




}