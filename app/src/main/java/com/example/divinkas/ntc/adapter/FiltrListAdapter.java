package com.example.divinkas.ntc.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.divinkas.ntc.R;

import java.util.List;

public class FiltrListAdapter extends RecyclerView.Adapter<FiltrListAdapter.ViewHolder>{
    LayoutInflater inflater;
    Context context;
    List<String> listFiltrItems;

    public static boolean[] show;

    public FiltrListAdapter(Context context, List<String> list){
        this.context = context;
        listFiltrItems = list;
        inflater = LayoutInflater.from(context);
        show = new boolean[listFiltrItems.size()];

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_filtr_layout, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.checkBox.setText(listFiltrItems.get(position));
        holder.checkBox.setChecked(true);
        show[position] = holder.checkBox.isChecked();
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                show[position] = isChecked;

                /*
                for (int i = 0; i < show.length; i++){
                    if(show[i]){ Log.println(Log.INFO, "cheked_list", i + " - true"); }
                    else{ Log.println(Log.INFO, "cheked_list", i + " - false"); }
                    }
                Log.println(Log.INFO, "cheked_list", " - end ________________");
                */
            }
        });
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
