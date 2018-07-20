package com.example.divinkas.ntc.dto;

import android.os.AsyncTask;
import android.util.Log;

import com.example.divinkas.ntc.R;
import com.example.divinkas.ntc.dataTypes.ItemTovar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ConnecterDTO extends AsyncTask<Void, Void, List<ItemTovar>> {

    private List<ItemTovar> itemTovarList;
    public ConnecterDTO(){itemTovarList = new ArrayList<>(); }

    @Override
    protected List<ItemTovar> doInBackground(Void... voids) {
        try {
            itemTovarList = new ArrayList<>();

            Document document = Jsoup.connect("https://papakava.4k.com.ua/api/products")
                    .ignoreContentType(true)
                    .get();

            Log.println(Log.INFO, "restAPI", document.toString());
            //Element obj = document.text();

            String result = document.text();
            Log.println(Log.INFO, "restAPI", result);

            JSONObject jsonObject = new JSONObject(result);
            JSONArray arrayTovars = jsonObject.getJSONArray("products");

            for (int i = 0; i < arrayTovars.length(); i++){

                ItemTovar itemTovar = new ItemTovar();
                JSONObject item = arrayTovars.getJSONObject(i);

                Log.println(Log.INFO, "restAPI", item.toString());

                itemTovar.setName(item.getString("name"));
                itemTovar.setUrlTovar(item.getString("image_url"));
                itemTovar.setPrice(item.getString("price"));

                Log.println(Log.INFO, "restAPI", "first_marker: " + item.getString("first_marker"));
                Log.println(Log.INFO, "restAPI", "second_marker: " + item.getString("second_marker"));

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
            Log.println(Log.INFO, "restAPI", "list count - " + itemTovarList.size());

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

    // old render data
    /*
    public List<ItemTovar> getItemTovars(){
        for(int i = 1; i < 11; i++){

            String text = "test test test test test test test ";
            ItemTovar item = new ItemTovar(text, "228$", R.drawable.kava_item);

            if(i == 4){
                item.setSaleRed(true);
                item.setTextSale("Арабика");
            }
            if(i == 3){
                item.setSaleOrange(true);
                item.setTextSale("робуста");
            }
            itemTovarList.add(item);
        }
        return itemTovarList;
    }
    */
}
