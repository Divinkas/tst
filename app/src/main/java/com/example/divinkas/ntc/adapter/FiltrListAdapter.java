package com.example.divinkas.ntc.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.divinkas.ntc.R;

import java.util.List;

public class FiltrListAdapter extends RecyclerView.Adapter<FiltrListAdapter.ViewHolder>{
    LayoutInflater inflater;
    Context context;
    List<String> listFiltrItems;

    public FiltrListAdapter(Context context, List<String> list){
        this.context = context;
        listFiltrItems = list;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_filtr_layout, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.checkBox.setText(listFiltrItems.get(position));
        holder.checkBox.setChecked(true);
    }

    @Override
    public int getItemCount() {
        return listFiltrItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        public ViewHolder(View itemView){
            super(itemView);
            checkBox = itemView.findViewById(R.id.cbxFiltrItem);
        }
    }
}
