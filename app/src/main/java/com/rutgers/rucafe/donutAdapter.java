package com.rutgers.rucafe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class donutAdapter extends RecyclerView.Adapter<donutAdapter.donutHolder> {
    private Context context;
    private ArrayList<Donut> donuts; //need the data binding to each row of recyclerview

    public donutAdapter(Context context, ArrayList<Donut> donuts){
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

        holder.donutFlavor.setText(donuts.get(position).getFlavor());
        holder.donutType.setText(donuts.get(position).getDonutType());
        double d = donuts.get(position).itemPrice();
        String s = String.valueOf(d);
        holder.donutPrice.setText(s);
        holder.donutImage.setImageResource(donuts.get(position).getImage());


    }

    @Override
    public int getItemCount() {
        return donuts.size();
    }

    public static class donutHolder extends RecyclerView.ViewHolder{

        private TextView donutType, donutPrice,donutFlavor;
        private ImageView donutImage;
        private Button addDonut;
        private ConstraintLayout parentLayout;


        public donutHolder (@NonNull View donutView){

            super(donutView);
            donutFlavor = donutView.findViewById(R.id.donutFlavor);
            donutType = donutView.findViewById(R.id.donutType);
            donutPrice = donutView.findViewById(R.id.donutPrice);
            donutImage = donutView.findViewById(R.id.donutImage);
            addDonut = donutView.findViewById(R.id.fucj);
            parentLayout = itemView.findViewById(R.id.rowLayout);
            setAddButtonOnClick(donutView);


            //if you click an imageview the program crashes bc of this
            parentLayout.setOnClickListener(new View.OnClickListener(){

            @Override
                    public void onClick(View view){
                    Intent intent = new Intent(donutView.getContext(), donutSelectedActivity.class);
                    intent.putExtra("DONUT", donutPrice.getText());
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
                    alert.setMessage(donutFlavor.getText().toString() + " " + donutType.getText().toString());
                    //handle the "YES" click
                    alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(),
                                    donutFlavor.getText().toString() + " " + donutType.getText().toString() + " added.", Toast.LENGTH_LONG).show();
                        }
                        //handle the "NO" click
                    }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(itemView.getContext(),
                                    donutFlavor.getText().toString() + " " + donutType.getText().toString() + " not added.", Toast.LENGTH_LONG).show();
                        }
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            });
        }
        }
    }


