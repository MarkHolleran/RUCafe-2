package com.rutgers.rucafe;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class coffeeAdapt extends RecyclerView.Adapter<coffeeAdapt.coffeeViewHolder> {
    private ArrayList<MenuItem> itemArrayList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {

        void onDeleteClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public static class coffeeViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView deleteImage;

        public coffeeViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            deleteImage = itemView.findViewById(R.id.deleteButton);

            deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }


    public coffeeAdapt(ArrayList<MenuItem> list) {
        itemArrayList = list;
    }

    @Override
    public coffeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewss = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        coffeeViewHolder coffeeViewHolder = new coffeeViewHolder(viewss, listener);
        return coffeeViewHolder;
    }

    @Override
    public void onBindViewHolder(coffeeViewHolder holder, int position) {
        MenuItem currentItem = itemArrayList.get(position);
        holder.textView.setText(currentItem.toString());
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }
}
