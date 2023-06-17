package com.example.donatenowapp.Model;

import com.google.firebase.database.Exclude;

public class AddToCart {
    public String pName;
    public String pPrice;
    public String tPrice;
    public String pQuantity;
    public String mURL;
    public String mKey;


    public AddToCart() {
    }

    public AddToCart(String pName, String pPrice, String tPrice, String pQuantity, String mURL, String mKey) {
        this.pName = pName;
        this.pPrice = pPrice;
        this.tPrice = tPrice;
        this.pQuantity = pQuantity;
        this.mURL = mURL;
        this.mKey = mKey;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpPrice() {
        return pPrice;
    }

    public void setpPrice(String pPrice) {
        this.pPrice = pPrice;
    }

    public String gettPrice() {
        return tPrice;
    }

    public void settPrice(String tPrice) {
        this.tPrice = tPrice;
    }

    public String getpQuantity() {
        return pQuantity;
    }

    public void setpQuantity(String pQuantity) {
        this.pQuantity = pQuantity;
    }

    public String getmURL() {
        return mURL;
    }

    public void setmURL(String mURL) {
        this.mURL = mURL;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        mKey = key;
    }

    public String getmKey() {
        return mKey;
    }

    public void setmKey(String mKey) {
        this.mKey = mKey;
    }

}
