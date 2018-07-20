package com.example.divinkas.ntc.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.divinkas.ntc.R;
import com.example.divinkas.ntc.dataTypes.ItemTovar;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TovarListAdapter extends RecyclerView.Adapter<TovarListAdapter.ViewHolder> {

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
        View view = inflater.inflate(R.layout.fragment_tovar_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemTovar item = itemTovarList.get(position);
        holder.tovName.setText(item.getName());
        holder.tovPrice.setText(item.getPrice());


        //holder.tovImage.setImageBitmap(BitmapFactory.decodeStream(url.openConnection().getInputStream()));

        //Picasso.with(context).invalidate(item.getUrlTovar());
        //Picasso.with(context).load(item.getUrlTovar()).into(holder.tovImage);
        Glide.with(context).load(item.getUrlTovar()).into(holder.tovImage);
        /*
        try {
            LoaderImg loaderImg = new LoaderImg();
            loaderImg.execute(item.getUrlTovar());
            Bitmap img = loaderImg.get();
            holder.tovImage.setImageBitmap(img);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        */



        Log.println(Log.INFO, "restAPI", item.getUrlTovar());

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
    // don't work
    /*
    public class LoaderImg extends AsyncTask<String, Void, Bitmap>{

        public LoaderImg(){}
        @Override
        protected Bitmap doInBackground(String... strings) {
            URL url = null;
            try {
                url = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setDoInput(true);
                conn.connect();
                InputStream is = conn.getInputStream();
                Bitmap bmImg = BitmapFactory.decodeStream(is);
                return bmImg;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
        }
    }
    */
}
