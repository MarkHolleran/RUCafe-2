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

/**
 * Class that represents the functionality of the interface for ordering a Donut.
 *
 * Within this class are all the components of creating a
 * donut order and their corresponding functionality.
 *
 * @author Mark Holleran, Abhitej Bokka
 */
public class donut_order extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static Order donutOrder = new Order();

    private RecyclerView donutOrderRecyclerView;
    private RecyclerView.LayoutManager donutLayout;
    private objectAdapter coffeeAdapter;
    private TextView donutOrderSubtotal;
    private ArrayList<Donut> donuts = new ArrayList<>();
    private final int [] donutImages = {R.drawable.apple, R.drawable.banana, R.drawable.grapes,
            R.drawable.mango, R.drawable.orange, R.drawable.peach, R.drawable.pineapple,
            R.drawable.strawberry, R.drawable.strawberry, R.drawable.strawberry,
            R.drawable.strawberry, R.drawable.strawberry};

    /**
     * Initializes variables and creates buttons and the view to order Donut.
     *
     * @param savedInstanceState Bundle just helps pass saved information from previous states
     */
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

    /**
     * Method that sets up the RecyclerView and lets adapter convert the Order.
     */
    private void createViews(){
        donutOrderRecyclerView = findViewById(R.id.donutListView);
        donutLayout = new LinearLayoutManager(this);
        donutOrderRecyclerView = findViewById(R.id.donutListView);
        donutOrderRecyclerView.setHasFixedSize(true);
        donutOrderSubtotal = findViewById(R.id.donutOrderSubtotal);
        donutLayout = new LinearLayoutManager(this);
    }

    /**
     * Method that updates activity information once Donut selection is made
     * in a separate activity.
     */
    @Override
    public void onResume(){
        super.onResume();
        updateBalance();
        updateAllOrders();
    }

    /**
     * Method that helps update the new order list after any Donut
     * is added or deleted.
     */
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

    /**
     * Method that helps update the balance of an order when changes are made.
     */
    public void updateBalance(){

        double sum = 0;
        for (MenuItem items : donutOrder.getOrder()) {
            sum += items.itemPrice();
        }
        donutOrderSubtotal.setText(String.format("$" + "%.2f", donutOrder.orderPrice()));
    }


    /**
     * Method that sets up RecyclerView of different donuts that can
     * be ordered by the user and be placed in the basket.
     */
    private void setupMenuItems() {

        String [] donutFlavors = getResources().getStringArray(R.array.donut_names);
        String [] donutTypes = getResources().getStringArray(R.array.donut_types);

        for (int i = 0; i < donutFlavors.length; i++) {
            donuts.add(new Donut(donutTypes[i] ,donutFlavors[i]  ,donutImages[i] ));
        }

        donutOrderRecyclerView = findViewById(R.id.donutListView);
        donutOrderRecyclerView.setHasFixedSize(true);
        donutLayout = new LinearLayoutManager(this);
    }

    /**
     * Removes a selected Donut Object from the RecyclerView
     * and updates the RecyclerView along with the total.
     *
     * @param position indicating the specific Donut to remove.
     */
    public void removeItem(int position) {
        donutOrder.getOrder().remove(position);
        coffeeAdapter.notifyItemRemoved(position);
        updateBalance();
        updateAllOrders();
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * Method that allows us to perform an action if no selection happens.
     *
     * @param parent AdapterView containing the location of selection
     */
    public void onNothingSelected(AdapterView<?> parent) {

    }


}