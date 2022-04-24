package com.rutgers.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class donut_order extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static Order donutOrder = new Order();

    private RecyclerView donutOrderRecyclerView;
    private RecyclerView.LayoutManager donutLayout;
    private objectAdapter coffeeAdapter;
    private TextView donutOrderSubtotal;
    private ArrayList<Donut> donuts = new ArrayList<>();
    private final int [] donutImages = {R.drawable.apple, R.drawable.banana, R.drawable.grapes,
            R.drawable.mango, R.drawable.orange, R.drawable.peach, R.drawable.pineapple,
            R.drawable.strawberry, R.drawable.strawberry, R.drawable.strawberry, R.drawable.strawberry, R.drawable.strawberry};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_order);

        RecyclerView rcview = findViewById(R.id.rcView_menu);
        setupMenuItems();
        createViews();

        donutAdapter adapter = new donutAdapter(this,donuts);
        rcview.setAdapter(adapter);
        rcview.setLayoutManager(new LinearLayoutManager(this));

        coffeeAdapter = new objectAdapter(donutOrder.getOrder());
        donutOrderRecyclerView.setLayoutManager(donutLayout);
        donutOrderRecyclerView.setAdapter(coffeeAdapter);

        donutOrderSubtotal.setText(String.format("$"+"%.2f", donutOrder.orderPrice()));
        updateBalance();
    }

    private void createViews(){
        donutOrderRecyclerView = findViewById(R.id.donutListView);
        donutLayout = new LinearLayoutManager(this);
        donutOrderRecyclerView = findViewById(R.id.donutListView);
        donutOrderRecyclerView.setHasFixedSize(true);
        donutOrderSubtotal = findViewById(R.id.donutOrderSubtotal);
        donutLayout = new LinearLayoutManager(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        updateBalance();
        updateAllOrders();
    }

    public void updateAllOrders() {

        coffeeAdapter = new objectAdapter(donutOrder.getOrder());
        donutOrderRecyclerView.setLayoutManager(donutLayout);
        donutOrderRecyclerView.setAdapter(coffeeAdapter);

        coffeeAdapter.setOnItemClickListener(position -> {
            removeItem(position);
            updateAllOrders();
            updateBalance();

            System.out.println(donutOrder.getOrder().toString());

        });
    }



    public void updateBalance(){

        double sum = 0;
        for (MenuItem items : donutOrder.getOrder()) {
            sum += items.itemPrice();
        }
        donutOrderSubtotal.setText(String.format("$" + "%.2f", donutOrder.orderPrice()));
        //donutAdapter.notifyDataSetChanged();

    }



    private void setupMenuItems() {

        String [] donutFlavors = getResources().getStringArray(R.array.donut_names);
        String [] donutTypes = getResources().getStringArray(R.array.donut_types);
        /* Add the items to the ArrayList.
         * Note that I use hardcoded prices for demo purpose. This should be replace by other
         * data sources.
         */
        for (int i = 0; i < donutFlavors.length; i++) {
            donuts.add(new Donut(donutTypes[i] ,donutFlavors[i]  ,donutImages[i] ));
        }

        donutOrderRecyclerView = findViewById(R.id.donutListView);
        donutOrderRecyclerView.setHasFixedSize(true);
        donutLayout = new LinearLayoutManager(this);
    }



    public void removeItem(int position) {
        donutOrder.getOrder().remove(position);
        coffeeAdapter.notifyItemRemoved(position);
        updateBalance();
        updateAllOrders();
    }

    //this doesn't do anything
    public void  onItemSelected(AdapterView<?> parent, View view, int position, long id){
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }



}