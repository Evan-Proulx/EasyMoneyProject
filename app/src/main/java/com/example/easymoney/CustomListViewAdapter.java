package com.example.easymoney;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class CustomListViewAdapter extends ArrayAdapter<ListItem> {

    public CustomListViewAdapter(@NonNull Context context, @NonNull List<ListItem> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent){
        ListItem item = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
            TextView title = convertView.findViewById(R.id.listTitle);
            title.setText(item.getTitle());
            TextView description = convertView.findViewById(R.id.listDescription);
            description.setText(item.getDescription());
        }
        return convertView;
    }
}