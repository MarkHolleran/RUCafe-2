package com.rutgers.rucafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class objectAdapter extends RecyclerView.Adapter<objectAdapter.objectViewHolder> {
    private final ArrayList<?> itemArrayList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public static class objectViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView deleteImage;

        public objectViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            deleteImage = itemView.findViewById(R.id.deleteButton);

            deleteImage.setOnClickListener(v -> {

                AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                alert.setTitle("Remove");
                alert.setMessage("Would you like to remove this Item?");

                    alert.setPositiveButton("yes", (dialogInterface, which) -> {

                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                    Toast.makeText(itemView.getContext(), "Item has been removed.", Toast.LENGTH_LONG).show();
                }).setNegativeButton("no", (dialogInterface, which) ->
                        Toast.makeText(itemView.getContext(),
                                "Item has not been removed.", Toast.LENGTH_LONG).show());

                AlertDialog dialog = alert.create();
                dialog.show();
            });
        }
    }

    public objectAdapter(ArrayList<?> list) {
        itemArrayList = list;
    }

    @NonNull
    @Override
    public objectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewss = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new objectViewHolder(viewss, listener);
    }

    @Override
    public void onBindViewHolder(objectViewHolder holder, int position) {
        holder.textView.setText(itemArrayList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }
}
