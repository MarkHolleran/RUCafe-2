package com.rutgers.rucafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.os.Bundle;

import java.util.ArrayList;

public class coffee_order extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static Order coffeeOrders = new Order();
//i think these strings count as being hardcoded
    public static final String[] sizes = {"Short", "Tall", "Grande", "Venti"};

    public static final int INITIAL_QUANTITY = 1;

    private RecyclerView coffeeList;
    private coffeeAdapt coffeeAdapter;
    private RecyclerView.LayoutManager coffeeLayout;

    private Button addButton;
    private Spinner spinner;
    private CheckBox cream;
    private CheckBox syrup;
    private CheckBox caramel;
    private CheckBox whippedCream;
    private TextView subtotal;

    public static final String CREAM = "Cream";
    public static final String SYRUP = "Syrup";
    public static final String CARAMEL = "Caramel";
    public static final String WHIPPEDCREAM = "Whipped Cream";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_order);

        createViews();
        createButtons();


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
                updateBalance();
            }
        });

    }

    private void createButtons(){
        addButton = findViewById(R.id.addButton);
        spinner = findViewById(R.id.spinnerCoffee);
        cream = findViewById(R.id.creamCoffee);
        syrup = findViewById(R.id.syrupCoffee);
        caramel = findViewById(R.id.caramelCoffee);
        whippedCream = findViewById(R.id.whippedCreamCoffee);
        subtotal = findViewById(R.id.coffeeSubTotal);
        subtotal.setText(String.format("$"+"%.2f", coffeeOrders.orderPrice()));
    }

    private void updateBalance(){
        double sum = 0;
        for(MenuItem items: coffeeOrders.getOrder()){
            sum += items.itemPrice();
        }
        subtotal.setText(String.format("$"+"%.2f", coffeeOrders.orderPrice()));
        coffeeAdapter.notifyDataSetChanged();
    }

    public void addItem(){

        Resources res = getResources();
        String size = res.getStringArray(R.array.coffeeSizes)[spinner.getSelectedItemPosition()];

        //String size = sizes[spinner.getSelectedItemPosition()];
        ArrayList<String> toppings = new ArrayList<>();
        checkToppings(toppings);
        Coffee item = new Coffee(size, toppings);
        boolean duplicateDonutFound = false;

        if(item.getQuantity() < 1){
            item.setQuantity(INITIAL_QUANTITY);
        }

        for (MenuItem num : coffeeOrders.getOrder()) {
            if (num.compare(item)) {
                duplicateDonutFound = true;
                num.setQuantity(num.getQuantity() + item.getQuantity());
            }
        }
        if(!duplicateDonutFound){
            item.setQuantity(INITIAL_QUANTITY);
            coffeeOrders.add(item);
        }
        duplicateDonutFound = false;



        System.out.println(item.toString());
    }

    private void checkToppings(ArrayList<String> toppings){
        System.out.println("Toppings before: "+ toppings);
        //toppings.clear();
        toppings.remove(CREAM);
        toppings.remove(SYRUP);
        toppings.remove(CARAMEL);
        toppings.remove(WHIPPEDCREAM);

        if(cream.isChecked()){
            toppings.add(CREAM);
        }
        if(syrup.isChecked()){
            toppings.add(SYRUP);
        }
        if(caramel.isChecked()){
            toppings.add(CARAMEL);
        }
        if(whippedCream.isChecked()){
            toppings.add(WHIPPEDCREAM);
        }
        System.out.println("Toppings After: " + toppings);
    }


    public void removeItem(int position) {
        coffeeOrders.getOrder().remove(position);

        coffeeAdapter.notifyItemRemoved(position);
        updateBalance();
    }

    public void createViews(){
        Spinner spinner = findViewById(R.id.spinnerCoffee);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.coffeeSizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        coffeeList = findViewById(R.id.coffeeListView);
        coffeeList.setHasFixedSize(true);
        coffeeLayout = new LinearLayoutManager(this);
        coffeeAdapter = new coffeeAdapt(coffeeOrders.getOrder());

        coffeeList.setLayoutManager(coffeeLayout);
        coffeeList.setAdapter(coffeeAdapter);

        coffeeAdapter.setOnItemClickListener(new coffeeAdapt.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}