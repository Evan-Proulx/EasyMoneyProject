package com.example.easymoney.adapters;

import android.app.LauncherActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easymoney.ListItem;
import com.example.easymoney.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter{
    private ArrayList<ListItem> myItems;

    public CustomRecyclerViewAdapter(ArrayList<ListItem> myItems){this.myItems = myItems;}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row,null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        final ListItem item = myItems.get(position);
        final CustomViewHolder holder1 = (CustomViewHolder) holder;

        holder1.title.setText(item.getTitle());
        holder1.description.setText(item.getDescription());

    }

    @Override
    public int getItemCount() {
        return (myItems != null ? myItems.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        protected TextView title;
        protected TextView description;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.title = itemView.findViewById(R.id.recycleTitle);
            this.description = itemView.findViewById(R.id.recycleDescription);
        }
    }
}
