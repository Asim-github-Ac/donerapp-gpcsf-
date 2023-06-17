package com.example.donatenowapp.Model;

public class Products {
    public String pName;
    public String pPrice;
    public String pDes;
    public String pImage;
    public String pID;
    public String rID;

    public Products() {
    }

    public Products(String pName, String pPrice, String pDes, String pImage, String pID, String rID) {
        this.pName = pName;
        this.pPrice = pPrice;
        this.pDes = pDes;
        this.pImage = pImage;
        this.pID = pID;
        this.rID = rID;
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

    public String getpDes() {
        return pDes;
    }

    public void setpDes(String pDes) {
        this.pDes = pDes;
    }

    public String getpImage() {
        return pImage;
    }

    public void setpImage(String pImage) {
        this.pImage = pImage;
    }

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public String getrID() {
        return rID;
    }

    public void setrID(String rID) {
        this.rID = rID;
    }
}
