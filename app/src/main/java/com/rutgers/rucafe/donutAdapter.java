package com.rutgers.rucafe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

class donutAdapter extends RecyclerView.Adapter<donutAdapter.donutHolder> {
    private Context context;
    private ArrayList<MenuItem> donuts; //need the data binding to each row of recyclerview

    public donutAdapter(Context context, ArrayList<MenuItem> donuts){
        this.context = context;
        this.donuts = donuts;
    }





    @NonNull
    @Override
    public donutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);

        return new donutHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull donutHolder holder, int position) {

        holder.donutName.setText(donuts.get(position).getItemName());

    }

    @Override
    public int getItemCount() {
        return donuts.size();
    }

    public static class donutHolder extends RecyclerView.ViewHolder{

        private TextView donutName, donutPrice;
        private ImageView donutImage;
        private Button addDonut;
        private ConstraintLayout parentLayout;

        public donutHolder (@NonNull View donutView){

            super(donutView);
            donutName = donutView.findViewById(R.id.donutFlavor);
            donutPrice = donutView.findViewById(R.id.donutPrice);
            donutImage = donutView.findViewById(R.id.donutImage);
            addDonut = donutView.findViewById(R.id.donutAdd);
            parentLayout = itemView.findViewById(R.id.rowLayout);
            setAddButtonOnClick(donutView);



            parentLayout.setOnClickListener(new View.OnClickListener(){

            @Override
                    public void onClick(View view){
                    Intent intent = new Intent(donutView.getContext(), donutSelectedActivity.class);
                    intent.putExtra("DONUT", donutName.getText());
                    donutView.getContext().startActivity(intent);

            }
        });
    }

        private void setAddButtonOnClick(@NonNull View itemView) {
            addDonut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    alert.setTitle("Add to order");
                    alert.setMessage(donutName.getText().toString());
                    //handle the "YES" click
                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(),
                                    donutName.getText().toString() + " added.", Toast.LENGTH_LONG).show();
                        }
                        //handle the "NO" click
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(),
                                    donutName.getText().toString() + " not added.", Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            });
        }
        }
    }


