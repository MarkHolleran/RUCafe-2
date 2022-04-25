package com.rutgers.rucafe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

/**
 * This class represents the Activity
 * present when a specific Donut is
 * selected from the Recyclerview in
 * the donut_order class.
 *
 * Within this class are methods for
 * initialization of the Activity,
 * Setting up the functionality for
 * the Add button.
 *
 * @author Mark Holleran, Abhitej Bokka
 */
public class donutSelectedActivity extends AppCompatActivity {
    private Button addDonutToOrderButton;
    private TextView donutToAdd;
    private TextView donutPrice;
    private Spinner donutOrderQuantity;
    private int DEFAULT_SPINNER_SELECTION = 1;
    private int ONE_DONUT_TO_BE_ADDED = 1;
    private int SPINNER_OFFSET = 1;
    private Integer[] Items = {1,2,3,4,5,6,7,8,9,10,11,12};

    /**
     * This method overrides the onCreate method
     * to set the contentview and set up all of the
     * xml objects
     *
     * @param savedInstanceState current state of program
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_selected);
        donutOrderQuantity = findViewById(R.id.donutQuantitySpinner);
        donutToAdd = findViewById(R.id.donutToAdd);
        donutPrice = findViewById(R.id.donutPriceInDonutActivity);
        addDonutToOrderButton = findViewById(R.id.addDonutToOrderButton);
        Intent intent = getIntent();
        addDonutToOrderButton.setText("Add");
        donutPrice.setText(intent.getStringExtra("DONUT"));
        AddDonutToOrderButton(findViewById(R.id.addDonutToOrderButton));
        Integer[] items = Items;
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        donutOrderQuantity.setAdapter(adapter);
        donutOrderQuantity.setSelection(DEFAULT_SPINNER_SELECTION);
        donutToAdd.setText(getIntent().getStringExtra("DONUTFLAVORSELECTED")
                + "\n" +getIntent().getStringExtra("DONUTTYPESELECTED"));
    }

    /**
     * This method has an OnclickListener
     * used for the Add button. When it's clicked
     * an alert dialog pops up to confirm choice
     * to add the selected donut to an Order Object
     *
     * @param itemView current View information
     */
    private void AddDonutToOrderButton(@NonNull View itemView){

        addDonutToOrderButton.setOnClickListener(view -> {

            AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
            alert.setTitle("Add to order");
            Donut newDonut = new Donut(getIntent().getStringExtra("DONUTTYPESELECTED"),
                    getIntent().getStringExtra("DONUTFLAVORSELECTED"));

            newDonut.setQuantity(donutOrderQuantity.getSelectedItemPosition()+1);
            alert.setMessage(newDonut.toString());
            alert.setPositiveButton("yes", (dialogInterface, which) -> {
                addItem();
                Toast.makeText(itemView.getContext(), newDonut + " added." ,Toast.LENGTH_LONG).show();
            }).setNegativeButton("no", (dialogInterface, which) -> {

                if (newDonut.getQuantity() == ONE_DONUT_TO_BE_ADDED){
                    Toast.makeText(itemView.getContext(),
                            newDonut+  " not added.",
                            Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(itemView.getContext(),
                            newDonut + " were not added.",
                            Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();
        });
    }

    /**
     * Creates the chosen Donut Object
     * and adds it to the Order
     * or increments the quantity if
     * the Donut already exists in the Order
     */
    public void addItem() {

        Donut newDonut = new Donut(getIntent().getStringExtra("DONUTTYPESELECTED"), getIntent().getStringExtra("DONUTFLAVORSELECTED"));
        newDonut.setQuantity(donutOrderQuantity.getSelectedItemPosition()+SPINNER_OFFSET);
        boolean duplicateDonutFound = false;

        for (MenuItem num : donut_order.donutOrder.getOrder()) {
            if (num.compare(newDonut)) {
                duplicateDonutFound = true;
                num.setQuantity(num.getQuantity() + newDonut.getQuantity());
            }
        }

        if (!duplicateDonutFound){
                newDonut.setQuantity(donutOrderQuantity.getSelectedItemPosition()+1);
            donut_order.donutOrder.add(newDonut);
        }
    }
}
