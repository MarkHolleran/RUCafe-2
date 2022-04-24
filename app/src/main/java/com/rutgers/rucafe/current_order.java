package com.rutgers.rucafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class current_order extends AppCompatActivity {

    public static Order allOrders = new Order();
    public static int orderNumber = 1;

    private RecyclerView itemList;
    private coffeeAdapt itemAdapter;
    private RecyclerView.LayoutManager itemLayout;

    private TextView subtotal;
    private TextView tax;
    private TextView total;
    private TextView subtotalDisplay;
    private TextView taxDisplay;
    private TextView totalDisplay;

    private Button placeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);

        createButtons();

        updateAllOrders();
        updateBalance();

        createViews();


        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allOrders.getOrder().isEmpty()) {
                    Resources res = getResources();
                    String text = res.getString(R.string.EmptyBasket);
                    Toast.makeText(itemList.getContext(), text, Toast.LENGTH_LONG).show();
                } else {

                    AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                    alert.setTitle("Place order");


                    alert.setMessage("Would you like to place the order?");

                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {

                            placeOrder();
                            Toast.makeText(view.getContext(), "Order has been placed.", Toast.LENGTH_LONG).show();


                        }
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {


                            Toast.makeText(view.getContext(),  "Order not placed.", Toast.LENGTH_LONG).show();

                        }
                    });

                    AlertDialog dialog = alert.create();
                    dialog.show();

                }


            }
        });

        itemAdapter.setOnItemClickListener(new coffeeAdapt.OnItemClickListener() {

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
}

    private void placeOrder(){

        allOrders.setOrderNumber(orderNumber);
        store_orders.storeOrders.getOrderList().add(allOrders);
        coffee_order.coffeeOrders = new Order();
        donut_order.donutOrder = new Order();
        updateAllOrders();
        finish();
        startActivity(getIntent());
        updateBalance();
        orderNumber++;

    }


    private void createButtons(){
        subtotal = findViewById(R.id.subTotal);
        tax = findViewById(R.id.salesTax);
        total = findViewById(R.id.total);
        subtotalDisplay = findViewById(R.id.subTotalDisplay);
        taxDisplay = findViewById(R.id.salesTaxDisplay);
        totalDisplay = findViewById(R.id.totalDisplay);
        placeOrder =  findViewById(R.id.placeOrderButton);

    }

    public void createViews(){

        itemList = findViewById(R.id.itemListView);
        itemList.setHasFixedSize(true);
        itemLayout = new LinearLayoutManager(this);
        itemAdapter = new coffeeAdapt(allOrders.getOrder());
        itemList.setLayoutManager(itemLayout);
        itemList.setAdapter(itemAdapter);

    }

    public void removeItem(int position) {

        System.out.println("Before: "+ allOrders.getOrder());
        if(allOrders.getOrder().get(position) instanceof Donut){
            donut_order.donutOrder.remove(allOrders.getOrder().get(position));
        }else{
            coffee_order.coffeeOrders.remove(allOrders.getOrder().get(position));
        }

        updateAllOrders();

        System.out.println("After: "+ allOrders.getOrder());
        itemAdapter.notifyItemRemoved(position);

        finish();
        startActivity(getIntent());
        updateBalance();

        //OP Strategy

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