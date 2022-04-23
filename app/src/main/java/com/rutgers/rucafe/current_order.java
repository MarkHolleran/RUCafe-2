package com.rutgers.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class current_order extends AppCompatActivity {

    public static Order allOrders = new Order();

    private RecyclerView itemList;
    private coffeeAdapt itemAdapter;
    private RecyclerView.LayoutManager itemLayout;

    private TextView subtotal;
    private TextView tax;
    private TextView total;
    private TextView subtotalDisplay;
    private TextView taxDisplay;
    private TextView totalDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        createButtons();

        updateAllOrders();
        updateBalance();

        createViews();





        //itemAdapter.notifyDataSetChanged();
    }

    private void createButtons(){
        subtotal = findViewById(R.id.subTotal);
        tax = findViewById(R.id.salesTax);
        total = findViewById(R.id.total);
        subtotalDisplay = findViewById(R.id.subTotalDisplay);
        taxDisplay = findViewById(R.id.salesTaxDisplay);
        totalDisplay = findViewById(R.id.totalDisplay);

    }

    public void createViews(){

        itemList = findViewById(R.id.itemListView);
        itemList.setHasFixedSize(true);
        itemLayout = new LinearLayoutManager(this);
        itemAdapter = new coffeeAdapt(allOrders.getOrder());

        itemList.setLayoutManager(itemLayout);
        itemList.setAdapter(itemAdapter);

        itemAdapter.setOnItemClickListener(new coffeeAdapt.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    public void removeItem(int position) {

        if(allOrders.getOrder().get(position) instanceof Donut){
            donut_order.donutOrder.remove(allOrders.getOrder().get(position));
        }else{
            coffee_order.coffeeOrders.remove(allOrders.getOrder().get(position));
        }

        updateAllOrders();
        updateBalance();
        itemAdapter.notifyItemRemoved(position);

//        //if(!allOrders.getOrder().isEmpty()){
//
//        System.out.println(allOrders.getOrder());
//            System.out.println("Before: "+allOrders.getOrder().size());
//            if(allOrders.getOrder().get(position) instanceof Donut){
//                //donut_order.donutOrders.remove(allOrders.getOrder().get(position));
//                itemAdapter.notifyItemRemoved(position);
//                itemAdapter.notifyDataSetChanged();
//            }else if(allOrders.getOrder().get(position) instanceof Coffee){
//                coffee_order.coffeeOrders.remove(allOrders.getOrder().get(position));
//                itemAdapter.notifyItemRemoved(position);
//                itemAdapter.notifyDataSetChanged();
//            }else{
//                //ERROR
//            }
//        System.out.println("After: "+allOrders.getOrder().size());
//
//            updateAllOrders();
//
//        System.out.println("After Update: "+allOrders.getOrder().size());
//
//            // idk if u need this? or u need - notify Data Set Changed
//
//        System.out.println("Before update");
//            System.out.println(allOrders.getOrder());
//            updateAllOrders();
//            updateBalance();
//        System.out.println("After update");
//        System.out.println(allOrders.getOrder());
//
//
//        //}

    }

    private void updateBalance(){
        double sum = 0;
        for(MenuItem items: allOrders.getOrder()){
            sum += items.itemPrice();
        }
        subtotalDisplay.setText(String.format("$"+"%.2f", allOrders.orderPrice()));
        taxDisplay.setText(String.format("$"+"%.2f", allOrders.orderPriceTax() - allOrders.orderPrice()));
        totalDisplay.setText(String.format("$"+"%.2f", allOrders.orderPriceTax()));
    }

    public ArrayList<MenuItem> updateAllOrders() {
        allOrders = new Order();
        allOrders.getOrder().addAll(coffee_order.coffeeOrders.getOrder());
        allOrders.getOrder().addAll(donut_order.donutOrder.getOrder());
        return this.allOrders.getOrder();
    }
}