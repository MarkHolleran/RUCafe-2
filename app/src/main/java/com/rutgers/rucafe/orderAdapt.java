package com.rutgers.rucafe;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class orderAdapt extends RecyclerView.Adapter<orderAdapt.orderViewHolder> {
    private ArrayList<MenuItem> itemArrayList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {

        //void onItemClick(int position);

        void onDeleteClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public static class orderViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView deleteImage;

        public orderViewHolder(View itemView, OnItemClickListener listener) {
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


    public orderAdapt(ArrayList<MenuItem> list) {
        itemArrayList = list;
    }

    @Override
    public orderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewss = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        orderViewHolder cViewHolder = new orderViewHolder(viewss, listener);
        return cViewHolder;
    }

    @Override
    public void onBindViewHolder(orderViewHolder holder, int position) {
        MenuItem currentItem = itemArrayList.get(position);
        holder.textView.setText(currentItem.toString());
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }
}
