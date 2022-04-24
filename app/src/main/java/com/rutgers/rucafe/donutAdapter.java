package com.rutgers.rucafe;


import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class donutAdapter extends RecyclerView.Adapter<donutAdapter.donutHolder> {
    private Context context;
    private ArrayList<Donut> donuts;
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
        holder.donutPrice.setText("Price per: $" +s);
    }

    @Override
    public int getItemCount() {
        return donuts.size();
    }

    public static class donutHolder extends RecyclerView.ViewHolder{

        private TextView donutType, donutPrice,donutFlavor;
        private ConstraintLayout parentLayout;


        public donutHolder (@NonNull View donutView){

            super(donutView);
            donutFlavor = donutView.findViewById(R.id.donutFlavor);
            donutType = donutView.findViewById(R.id.donutType);
            donutPrice = donutView.findViewById(R.id.donutPrice);
            parentLayout = itemView.findViewById(R.id.rowLayout);

                parentLayout.setOnClickListener(view -> {
                Intent intent = new Intent(donutView.getContext(), donutSelectedActivity.class);
                intent.putExtra("DONUT", donutPrice.getText());
                intent.putExtra("DONUTTYPESELECTED", donutType.getText());
                intent.putExtra("DONUTFLAVORSELECTED", donutFlavor.getText());
                donutView.getContext().startActivity(intent);
            });
        }
    }
}


