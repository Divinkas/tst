package com.example.divinkas.ntc.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.divinkas.ntc.R;
import com.example.divinkas.ntc.dataTypes.ItemTovar;

import java.util.List;

public class TovarListAdapter extends RecyclerView.Adapter<TovarListAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<ItemTovar> itemTovarList;

    public TovarListAdapter(Context context, List <ItemTovar>  list){
        itemTovarList = list;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.fragment_tovar_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemTovar item = itemTovarList.get(position);
        holder.tovName.setText(item.getName());
        holder.tovPrice.setText(item.getPrice());
        holder.tovImage.setImageResource(item.getImageTovar());

        if(item.isSaleOrange()){
            holder.saleOrange.setText(item.getTextSale());
            holder.saleOrange.setVisibility(View.VISIBLE);
        }
        if(item.isSaleRed()){
            holder.saleRed.setText(item.getTextSale());
            holder.saleRed.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return itemTovarList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tovName;
        TextView tovPrice;
        ImageView tovImage;
        TextView saleOrange;
        TextView saleRed;

        public ViewHolder(View itemView) {
            super(itemView);
            tovName = itemView.findViewById(R.id.tovarItemName);
            tovPrice = itemView.findViewById(R.id.tovarItemPrice);
            tovImage = itemView.findViewById(R.id.tovarItemImage);
            saleOrange = itemView.findViewById(R.id.tvSaleOrange);
            saleRed = itemView.findViewById(R.id.tvSaleRed);
        }
    }
}
