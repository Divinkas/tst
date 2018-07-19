package com.example.divinkas.ntc.dto;

import com.example.divinkas.ntc.R;
import com.example.divinkas.ntc.dataTypes.ItemTovar;

import java.util.ArrayList;
import java.util.List;

public class ConnecterDTO {

    private List<ItemTovar> itemTovarList;
    public ConnecterDTO() {
        itemTovarList = new ArrayList<>();
    }

    // :D
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
}
