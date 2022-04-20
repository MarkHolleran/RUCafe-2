package com.rutgers.rucafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

class donutAdapter extends RecyclerView.Adapter<donutAdapter.ItemsHolder> {
    private Context context;
    private ArrayList<MenuItem> donuts; //need the data binding to each row of recyclerview

    public donutAdapter(Context context, ArrayList<MenuItem> donuts){
        this.context = context;
        this.donuts = donuts;
    }





    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);

        return ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdaper.ItemsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
