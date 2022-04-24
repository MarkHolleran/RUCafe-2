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

/**
 * This class represents the Adapter
 * that resides within a Recyclerview
 * in multiple classes.
 *
 * @author Mark Holleran, Abhitej Bokka
 */
public class objectAdapter extends RecyclerView.Adapter<objectAdapter.objectViewHolder> {
    private final ArrayList<?> itemArrayList;
    private OnItemClickListener listener;

    /**
     * Needed for when the Object's delete button
     * is pressed
     */
    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    /**
     * Will alert the listener when the Item is clicked
     *
     * @param listener Waits for button click
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * This is the Object displayed within the RecyclerView
     */
    public static class objectViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView deleteImage;

        /**
         * Assigns a textview & button to it's xml counterpart
         * and executes commands when the listener is activated
         *
         * @param itemView View representing Object within RecyclerView
         * @param listener Sends data when Button is clicked
         */
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

    /**
     * Addapts ArrayList to be usable in the RecyclerView
     *
     * @param list ArrayList to be adapted for use in the RecyclerView
     */
    public objectAdapter(ArrayList<?> list) {
        itemArrayList = list;
    }

    /**
     * Creates a new RecyclerView ViewHolder
     * and initializes some fields used by RecyclerView
     *
     * @param parent ViewGroup which the new View will be added to
     * @param viewType ViewType of the new View
     * @return ObjectViewHolder containing View
     */
    @NonNull
    @Override
    public objectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewss = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new objectViewHolder(viewss, listener);
    }

    /**
     * Sets the holder Object's text to the item
     * in the ArrayList that was selected
     *
     * @param holder Holder object
     * @param position Selected object in an ArrayList
     */
    @Override
    public void onBindViewHolder(objectViewHolder holder, int position) {
        holder.textView.setText(itemArrayList.get(position).toString());
    }

    /**
     * This method is implemented
     * to satisfy all the requirements for creating
     * the object view holder
     *
     * @return Integer representing ArrayList size
     */
    @Override
    public int getItemCount() { return itemArrayList.size();}


}
