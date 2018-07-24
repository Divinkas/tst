package com.example.divinkas.ntc.dto;

import android.os.AsyncTask;

import com.example.divinkas.ntc.dataTypes.FiltrListTov;
import com.example.divinkas.ntc.dataTypes.ItemTovar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConnecterDTO extends AsyncTask<Void, Void, List<ItemTovar>> {

    private List<ItemTovar> itemTovarList;
    private FiltrListTov filtrListTov;
    public ConnecterDTO(){
        itemTovarList = new ArrayList<>();
        filtrListTov = new FiltrListTov();
    }

    @Override
    protected List<ItemTovar> doInBackground(Void... voids) {
        try {
            itemTovarList = new ArrayList<>();

            Document document = Jsoup.connect("https://papakava.4k.com.ua/api/products")
                    .ignoreContentType(true)
                    .get();

            String result = document.text();

            JSONObject jsonObject = new JSONObject(result);
            JSONArray arrayTovars = jsonObject.getJSONArray("products");

            for (int i = 0; i < arrayTovars.length(); i++){

                if(i == 1){
                    itemTovarList.add(null);
                }

                ItemTovar itemTovar = new ItemTovar();
                JSONObject item = arrayTovars.getJSONObject(i);

                filtrListTov.addItem(item.getString("brand_name"));

                itemTovar.setName(item.getString("name"));
                itemTovar.setUrlTovar(item.getString("image_url"));
                itemTovar.setPrice(item.getString("price"));
                itemTovar.setBrand_name(item.getString("brand_name"));
                itemTovar.setType_weight(item.getInt("weight_type"));

                if(item.getString("first_marker").isEmpty()){
                    itemTovar.setSaleOrange(false);
                } else {
                    itemTovar.setSaleOrange(true);
                    itemTovar.setTextSale(item.getString("first_marker"));
                }
                if (item.getString("second_marker").isEmpty()){
                    itemTovar.setSaleRed(false);
                } else{
                    itemTovar.setSaleRed(true);
                    itemTovar.setTextSale(item.getString("second_marker"));
                }

                itemTovarList.add(itemTovar);
            }

            return itemTovarList;
        }
        catch (IOException e) { e.printStackTrace(); }
        catch (JSONException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    protected void onPostExecute(List<ItemTovar> list) {
        super.onPostExecute(list);
    }
}
