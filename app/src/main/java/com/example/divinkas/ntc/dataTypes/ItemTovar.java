package com.example.divinkas.ntc.dataTypes;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class ItemTovar {

    private String name;
    private String price;
    private Integer imageTovar;
    private String urlTovar;

    private boolean isSaleOrange;
    private boolean isSaleRed;
    private String textSale;

    public ItemTovar(){}
    public ItemTovar(String name, String price, Integer imageTovar){
        this.name = name;
        this.price = price;
        this.imageTovar = imageTovar;

        isSaleOrange = false;
        isSaleRed = false;

        textSale = "default text";
    }

    public Integer getImageTovar() {
        return imageTovar;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public boolean isSaleOrange() {
        return isSaleOrange;
    }

    public boolean isSaleRed() {
        return isSaleRed;
    }

    public String getTextSale() {
        return textSale;
    }

    public void setImageTovar(Integer imageTovar) {
        this.imageTovar = imageTovar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSaleOrange(boolean saleOrange) {
        isSaleOrange = saleOrange;
    }

    public void setSaleRed(boolean saleRed) {
        isSaleRed = saleRed;
    }

    public void setTextSale(String textSale) {
        this.textSale = textSale;
    }

    public String getUrlTovar() {
        return urlTovar;
    }

    public void setUrlTovar(String urlTovar){
        this.urlTovar = urlTovar;
    }
}
