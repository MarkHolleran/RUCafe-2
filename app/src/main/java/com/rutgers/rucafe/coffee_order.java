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

/**
 * Class that represents the functionality of the interface for ordering a Coffee.
 *
 * Within this class are all the components of creating a
 * coffee order and their corresponding functionality.
 *
 * @author Mark Holleran, Abhitej Bokka
 */
public class coffee_order extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private objectAdapter coffeeAdapter;
    private Button addButton;
    private Spinner spinner;
    private CheckBox cream;
    private CheckBox syrup;
    private CheckBox caramel;
    private CheckBox whippedCream;
    private CheckBox milk;
    private TextView subtotal;
    public static final String CREAM = "Cream";
    public static final String SYRUP = "Syrup";
    public static final String CARAMEL = "Caramel";
    public static final String WHIPPEDCREAM = "Whipped Cream";
    public static final String MILK = "Milk";
    public static Order coffeeOrders = new Order();
    public static final int INITIAL_QUANTITY = 1;
    public static final int INITIAL_PRICE = 0;

    /**
     * Initializes variables and creates buttons and the view to order Coffee.
     *
     * @param savedInstanceState Bundle just helps pass saved information from previous states
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_order);
        createViews();
        createButtons();
        addButton.setOnClickListener(view -> {
            addItem();
            updateBalance();
        });
    }

    /**
     * Method that sets up all the IDs of buttons and relevant text output.
     */
    private void createButtons(){
        addButton = findViewById(R.id.addButton);
        spinner = findViewById(R.id.spinnerCoffee);
        cream = findViewById(R.id.creamCoffee);
        syrup = findViewById(R.id.syrupCoffee);
        caramel = findViewById(R.id.caramelCoffee);
        whippedCream = findViewById(R.id.whippedCreamCoffee);
        milk = findViewById(R.id.milkCoffee);
        subtotal = findViewById(R.id.coffeeSubTotal);
        subtotal.setText(String.format("Subtotal: $"+"%.2f", coffeeOrders.orderPrice()));
        coffeeAdapter.notifyDataSetChanged();
    }

    /**
     * Method that helps update the balance of an order when changes are made.
     */
    private void updateBalance(){
        double sum = INITIAL_PRICE;
        for(MenuItem items: coffeeOrders.getOrder()){
            sum += items.itemPrice();
        }
        subtotal.setText(String.format("Subtotal: $"+"%.2f", coffeeOrders.orderPrice()));
        coffeeAdapter.notifyDataSetChanged();
    }

    /**
     * When the Add button is pressed, a Coffee Object is made
     * and added to the RecyclerView of Coffee Objects
     * and the current total is updated
     */
    public void addItem(){
        Resources res = getResources();
        String size = res.getStringArray(R.array.coffeeSizes)[spinner.getSelectedItemPosition()];

        ArrayList<String> toppings = new ArrayList<>();
        checkToppings(toppings);
        Coffee item = new Coffee(size, toppings);
        boolean duplicateDonutFound = false;

        if(item.getQuantity() < INITIAL_QUANTITY){
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

        cream.setChecked(false);
        syrup.setChecked(false);
        caramel.setChecked(false);
        whippedCream.setChecked(false);
        milk.setChecked(false);
    }


    /**
     *
     * Method that clears and then determines what toppings
     * the customer asked for on their coffee.
     *
     * @param toppings Arraylist to be used to store the toppings requested.
     */
    private void checkToppings(ArrayList<String> toppings){
        toppings.remove(CREAM);
        toppings.remove(SYRUP);
        toppings.remove(CARAMEL);
        toppings.remove(WHIPPEDCREAM);
        toppings.remove(MILK);

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
        if(milk.isChecked()){
            toppings.add(MILK);
        }

    }


    /**
     * Removes a selected Coffee Object from the RecyclerView
     * and updates the RecyclerView along with the total.
     *
     * @param position indicating the specific Coffee to remove.
     */
    public void removeItem(int position) {
        coffeeOrders.getOrder().remove(position);
        coffeeAdapter.notifyItemRemoved(position);
        updateBalance();
    }

    /**
     * Method that sets up the RecyclerView and lets adapter convert the Order.
     */
    public void createViews(){
        Spinner spinner = findViewById(R.id.spinnerCoffee);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.coffeeSizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        RecyclerView coffeeList = findViewById(R.id.coffeeListView);
        coffeeList.setHasFixedSize(true);
        RecyclerView.LayoutManager coffeeLayout = new LinearLayoutManager(this);
        coffeeAdapter = new objectAdapter(coffeeOrders.getOrder());

        coffeeList.setLayoutManager(coffeeLayout);
        coffeeList.setAdapter(coffeeAdapter);

        coffeeAdapter.setOnItemClickListener(this::removeItem);
    }


    /**
     *
     * Method to show a Toast of which size is selected by user
     *
     * @param parent AdapterView containing the location of selection
     * @param view View where selection happened
     * @param position integer containing the position of size needed to be displayed.
     * @param id The row of the item selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * Method that allows us to perform an action if no selection happens.
     *
     * @param parent AdapterView containing the location of selection
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}