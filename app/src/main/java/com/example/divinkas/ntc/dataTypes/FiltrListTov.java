package com.example.divinkas.ntc.dataTypes;

import java.util.HashSet;
import java.util.Set;

public class FiltrListTov {
    private static Set<String> filtrList;

    public FiltrListTov(){
        filtrList = new HashSet<>();
        filtrList.add("250гр.");
        filtrList.add("500гр.");
        filtrList.add("1кг");
    }

    public static Set<String> getFiltrList() {
        return filtrList;
    }

    public void addItem(String s){
        filtrList.add(s);
    }

}
