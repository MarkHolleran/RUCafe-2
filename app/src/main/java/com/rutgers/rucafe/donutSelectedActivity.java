package com.rutgers.rucafe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class donutSelectedActivity extends AppCompatActivity {
    public static Order donutOrder = new Order();
    private Button addDonutToOrderButton;
    public static final int INITIAL_QUANTITY = 1;
    private TextView donutToAdd;

    private Spinner donutOrderQuantity;

    // add to order button
    @Override
    protected void onCreate(Bundle savedInstanceState){


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_selected);
        donutOrderQuantity = findViewById(R.id.donutQuantitySpinner);
        donutToAdd = findViewById(R.id.donutToAdd);


        addDonutToOrderButton = findViewById(R.id.addDonutToOrderButton);
        Intent intent = getIntent();
        addDonutToOrderButton.setText(intent.getStringExtra("DONUT"));
        setAddDonutToOrderButton(findViewById(R.id.addDonutToOrderButton));
        createViews();
        donutToAdd.setText(getIntent().getStringExtra("DONUTFLAVORSELECTED") + " " +getIntent().getStringExtra("DONUTTYPESELECTED"));


    }

    private void setAddDonutToOrderButton(@NonNull View itemView){


        addDonutToOrderButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){



                AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                alert.setTitle("Add to order");
                Donut newDonut = new Donut(getIntent().getStringExtra("DONUTTYPESELECTED"), getIntent().getStringExtra("DONUTFLAVORSELECTED"), 1);

                newDonut.setQuantity(donutOrderQuantity.getSelectedItemPosition()+1);

                alert.setMessage(newDonut.toString());

                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        addItem();
                        Toast.makeText(itemView.getContext(), newDonut.toString() + " added.",Toast.LENGTH_LONG).show();



                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        if (newDonut.getQuantity() == 1){
                            Toast.makeText(itemView.getContext(),newDonut.toString()+  " not added.",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(itemView.getContext(), newDonut.toString() + " were not added.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();

            }
        });
    }

    public void createViews(){
        Integer[] items = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, items);

        donutOrderQuantity.setAdapter(adapter);
        donutOrderQuantity.setSelection(0);

    }

    public void addItem() {

        //int donutQuantity = donutOrderQuantity.getSelectedItemPosition();
        Donut newDonut = new Donut(getIntent().getStringExtra("DONUTTYPESELECTED"), getIntent().getStringExtra("DONUTFLAVORSELECTED"), 1);
        //newDonut.setQuantity(INITIAL_QUANTITY);
        newDonut.setQuantity(donutOrderQuantity.getSelectedItemPosition());
        //have to work this out first
        //System.out.println(newDonut.toString());

        boolean duplicateDonutFound = false;

        for (MenuItem num : donutOrder.getOrder()) {

            if (num.compare(newDonut)) {
                duplicateDonutFound = true;
                num.setQuantity(num.getQuantity() + newDonut.getQuantity());
            }

        }

        if (duplicateDonutFound == false){
                newDonut.setQuantity(donutOrderQuantity.getSelectedItemPosition());
                donutOrder.add(newDonut);
            }

        System.out.println(donutOrder.toString());
    }


}
