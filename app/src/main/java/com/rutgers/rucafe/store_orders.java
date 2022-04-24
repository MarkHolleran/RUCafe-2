package com.rutgers.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

/**
 * Class that represents the functionality of the interface for an Store Order Object
 *
 * Within this class are all the components of editing an Store Order
 * and their corresponding functionality
 *
 * @author Mark Holleran, Abhitej Bokka
 */
public class store_orders extends AppCompatActivity {

    public static StoreOrders storeOrders = new StoreOrders();

    private objectAdapter itemAdapter;

    /**
     * Initializes variables and creates buttons and the view to edit an Order.
     *
     * @param savedInstanceState Bundle just helps pass saved information from previous states
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);

        createViews();
        itemAdapter.setOnItemClickListener(this::removeItem);

    }

    /**
     * Method that sets up the RecyclerView and lets adapter convert the Orders.
     */
    public void createViews(){

        RecyclerView itemList = findViewById(R.id.ordersListView);
        itemList.setHasFixedSize(true);
        RecyclerView.LayoutManager itemLayout = new LinearLayoutManager(this);
        itemAdapter = new objectAdapter(storeOrders.getOrderList());

        itemList.setLayoutManager(itemLayout);
        itemList.setAdapter(itemAdapter);
    }

    /**
     * Removes a selected Order Object from the RecyclerView
     * and updates the RecyclerView along with the total.
     *
     * @param position indicating the specific Order to remove.
     */
    public void removeItem(int position) {
        storeOrders.getOrderList().remove(storeOrders.getOrderList().get(position));
        itemAdapter.notifyItemRemoved(position);

        finish();
        startActivity(getIntent());
    }

}