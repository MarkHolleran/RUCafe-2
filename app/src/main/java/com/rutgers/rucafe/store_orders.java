package com.rutgers.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class store_orders extends AppCompatActivity {

    public static StoreOrders storeOrders = new StoreOrders();

    private objectAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);

        createViews();
        itemAdapter.setOnItemClickListener(this::removeItem);

    }

    public void createViews(){

        RecyclerView itemList = findViewById(R.id.ordersListView);
        itemList.setHasFixedSize(true);
        RecyclerView.LayoutManager itemLayout = new LinearLayoutManager(this);
        itemAdapter = new objectAdapter(storeOrders.getOrderList());

        itemList.setLayoutManager(itemLayout);
        itemList.setAdapter(itemAdapter);
    }

    public void removeItem(int position) {
        storeOrders.getOrderList().remove(storeOrders.getOrderList().get(position));
        itemAdapter.notifyItemRemoved(position);

        finish();
        startActivity(getIntent());
    }

}