package com.rutgers.rucafe;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class donutSelectedActivity extends AppCompatActivity {
    private Button addDonutToOrderButton;
    // add to order button
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_selected);
        addDonutToOrderButton = findViewById(R.id.addDonutToOrderButton);
        Intent intent = getIntent();
        addDonutToOrderButton.setText(intent.getStringExtra("DONUT"));
        setAddDonutToOrderButton(findViewById(R.id.addDonutToOrderButton));
    }

    private void setAddDonutToOrderButton(@NonNull View itemView){


        addDonutToOrderButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                alert.setTitle("Add to order");
                alert.setMessage(getIntent().getStringExtra("DONUTFLAVORSELECTED")+ " " + getIntent().getStringExtra("DONUTTYPESELECTED"));

                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        Toast.makeText(itemView.getContext(),getIntent().getStringExtra("DONUTFLAVORSELECTED")+ " " + getIntent().getStringExtra("DONUTTYPESELECTED") + " added.",Toast.LENGTH_LONG).show();

                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        Toast.makeText(itemView.getContext(),getIntent().getStringExtra("DONUTFLAVORSELECTED")+ " " + getIntent().getStringExtra("DONUTTYPESELECTED") + " not added.",Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();



            }


        });




    }
}
