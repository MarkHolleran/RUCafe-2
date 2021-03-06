package com.rutgers.rucafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Class that represents the functionality of the interface for an Order Object
 *
 * Within this class are all the components of editing an Order
 * and their corresponding functionality
 *
 * @author Mark Holleran, Abhitej Bokka
 */
public class current_order extends AppCompatActivity {

    public static final int NO_BALANCE = 0;
    public static final int INITIAL_NUMBER = 1;
    public static int orderNumber = INITIAL_NUMBER;
    public static Order allOrders = new Order();
    private RecyclerView itemList;
    private objectAdapter itemAdapter;
    private RecyclerView.LayoutManager itemLayout;
    private TextView subtotalDisplay;
    private TextView taxDisplay;
    private TextView totalDisplay;
    private Button placeOrder;

    /**
     * Initializes variables and creates buttons and the view to edit an Order.
     *
     * @param savedInstanceState Bundle just helps pass saved information from previous states
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        createButtons();
        updateAllOrders();
        updateBalance();
        createViews();
        placeOrder.setOnClickListener(new View.OnClickListener() {
            /**
             * Method that handles a confirmation to place an order.
             *
             * @param view View containing where the selection happened.
             */
            @Override
            public void onClick(View view) {
                onClickFunctionality(view);
            }
        });
        itemAdapter.setOnItemClickListener(this::removeItem);
    }

    /**
     * The actual functionality of confirmations to place an order.
     *
     * @param view View containing where the selection happened.
     */
    private void onClickFunctionality(View view){
        if (allOrders.getOrder().isEmpty()) {
            Resources res = getResources();
            String text = res.getString(R.string.EmptyBasket);
            Toast.makeText(itemList.getContext(), text, Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
            alert.setTitle("Place order");
            alert.setMessage("Would you like to place the order?");
            alert.setPositiveButton("yes", (dialogInterface, which) -> {
                placeOrder();
                Toast.makeText(view.getContext(), "Order has been placed.",
                        Toast.LENGTH_SHORT).show();

            }).setNegativeButton("no", (dialogInterface, which) ->
                    Toast.makeText(view.getContext(),
                            "Order not placed.", Toast.LENGTH_SHORT).show());

            AlertDialog dialog = alert.create();
            dialog.show();
        }
    }

    /**
     * When the place Order button is pressed, the Order Object
     * containing all MenuItems is added to the StoreOrders Object
     * containing all Orders.
     */
    private void placeOrder() {

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

    /**
     * Method that sets up all the IDs of buttons and relevant text output.
     */
    private void createButtons(){
        subtotalDisplay = findViewById(R.id.subTotalDisplay);
        taxDisplay = findViewById(R.id.salesTaxDisplay);
        totalDisplay = findViewById(R.id.totalDisplay);
        placeOrder =  findViewById(R.id.placeOrderButton);
    }

    /**
     * Method that sets up the RecyclerView and lets adapter convert the Order.
     */
    public void createViews(){
        itemList = findViewById(R.id.itemListView);
        itemList.setHasFixedSize(true);
        itemLayout = new LinearLayoutManager(this);
        itemAdapter = new objectAdapter(allOrders.getOrder());
        itemList.setLayoutManager(itemLayout);
        itemList.setAdapter(itemAdapter);
    }

    /**
     * Removes a selected Coffee/Donut Object from the RecyclerView
     * and updates the RecyclerView along with the total.
     *
     * @param position indicating the specific MenuItem to remove.
     */
    public void removeItem(int position) {
        if(allOrders.getOrder().get(position) instanceof Donut){
            donut_order.donutOrder.remove(allOrders.getOrder().get(position));
        }else{
            coffee_order.coffeeOrders.remove(allOrders.getOrder().get(position));
        }
        updateAllOrders();
        itemAdapter.notifyItemRemoved(position);
        finish();
        startActivity(getIntent());
        updateBalance();
    }

    /**
     * Method that helps update the balance of an order when changes are made.
     */
    private void updateBalance(){
        double sum = NO_BALANCE;
        for(MenuItem items: allOrders.getOrder()){
            sum += items.itemPrice();
        }
        subtotalDisplay.setText(String.format("$"+"%.2f", allOrders.orderPrice()));
        taxDisplay.setText(String.format("$"+"%.2f", allOrders.orderPriceTax() - allOrders.orderPrice()));
        totalDisplay.setText(String.format("$"+"%.2f", allOrders.orderPriceTax()));
    }

    /**
     * Method that helps update the new order list after any MenuItem
     * is added or deleted.
     */
    public void updateAllOrders() {
        allOrders = new Order();
        allOrders.getOrder().addAll(coffee_order.coffeeOrders.getOrder());
        allOrders.getOrder().addAll(donut_order.donutOrder.getOrder());
    }
}