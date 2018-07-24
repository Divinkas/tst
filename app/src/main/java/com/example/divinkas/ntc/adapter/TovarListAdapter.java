package com.example.divinkas.ntc.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.divinkas.ntc.R;
import com.example.divinkas.ntc.dataTypes.ItemTovar;
import java.util.List;

public class TovarListAdapter extends RecyclerView.Adapter<TovarListAdapter.ViewHolder> {
    private final static int TYPE_VIEW_ONE = 0;
    private final static int TYPE_VIEW_TWO = 1;
    private LayoutInflater inflater;
    private List<ItemTovar> itemTovarList;
    private Context context;

    public TovarListAdapter(Context context, List <ItemTovar>  list){
        this.context = context;
        itemTovarList = list;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case TYPE_VIEW_TWO:
                view = inflater.inflate(R.layout.fragment_refresh, parent, false);
                break;
            default:
                view = inflater.inflate(R.layout.fragment_tovar_item, parent, false);
                break;
        }
        return new ViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(position != 1) {
            ItemTovar item = itemTovarList.get(position);
            holder.tovName.setText(item.getName());
            holder.tovPrice.setText(item.getPrice());

            Glide.with(context).load(item.getUrlTovar()).into(holder.tovImage);

            if (item.isSaleOrange()) {
                holder.saleOrange.setText(item.getTextSale());
                holder.saleOrange.setVisibility(View.VISIBLE);
            }
            if (item.isSaleRed()) {
                holder.saleRed.setText(item.getTextSale());
                holder.saleRed.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 1:
                return TYPE_VIEW_TWO;
        }
        return TYPE_VIEW_ONE;
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

        public ViewHolder(View itemView, int viewType) {
            super(itemView);
            if(viewType ==TYPE_VIEW_ONE) {
                tovName = itemView.findViewById(R.id.tovarItemName);
                tovPrice = itemView.findViewById(R.id.tovarItemPrice);
                tovImage = itemView.findViewById(R.id.tovarItemImage);
                saleOrange = itemView.findViewById(R.id.tvSaleOrange);
                saleRed = itemView.findViewById(R.id.tvSaleRed);
            }
        }
    }

}
