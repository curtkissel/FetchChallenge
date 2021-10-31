package com.example.fetchchallenge.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fetchchallenge.R;

import com.example.fetchchallenge.model.Item;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Item> itemList;
    private Context context;

    public RecyclerAdapter(Context context, ArrayList<Item> itemList){
        this.itemList = itemList;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_items, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        // Display the information in the TextViews
        ((ViewHolder)viewHolder).idTV.setText(""+itemList.get(i).getId());
        ((ViewHolder)viewHolder).lidTV.setText(""+itemList.get(i).getListId());
        ((ViewHolder)viewHolder).nameTV.setText(""+itemList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        if(itemList==null) {
            //Log.d("Item list ", "is null");
            return 0;
        }
        return itemList.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{
        TextView idTV;
        TextView lidTV;
        TextView nameTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTV = itemView.findViewById(R.id.textView);
            lidTV = itemView.findViewById(R.id.textView2);
            nameTV = itemView.findViewById(R.id.textView3);

        }
    }
}
