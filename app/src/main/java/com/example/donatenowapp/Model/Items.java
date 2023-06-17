package com.example.donatenowapp.Model;

public class Items {
    public String iName;
    public String iDes;
    public String iPhone;
    public String iAddress;
    public String iImage;
    public String isBooked;
    public String rID;
    public String iID;
    public String iLongitude;
    public String iLatitude;

    public Items(String iName, String iDes, String iPhone, String iAddress, String iImage, String isBooked, String rID, String iID, String iLongitude, String iLatitude) {
        this.iName = iName;
        this.iDes = iDes;
        this.iPhone = iPhone;
        this.iAddress = iAddress;
        this.iImage = iImage;
        this.isBooked = isBooked;
        this.rID = rID;
        this.iID = iID;
        this.iLongitude = iLongitude;
        this.iLatitude = iLatitude;
    }

    public Items() {
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public String getiDes() {
        return iDes;
    }

    public void setiDes(String iDes) {
        this.iDes = iDes;
    }

    public String getiPhone() {
        return iPhone;
    }

    public void setiPhone(String iPhone) {
        this.iPhone = iPhone;
    }

    public String getiAddress() {
        return iAddress;
    }

    public void setiAddress(String iAddress) {
        this.iAddress = iAddress;
    }

    public String getiImage() {
        return iImage;
    }

    public void setiImage(String iImage) {
        this.iImage = iImage;
    }

    public String getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(String isBooked) {
        this.isBooked = isBooked;
    }

    public String getrID() {
        return rID;
    }

    public void setrID(String rID) {
        this.rID = rID;
    }

    public String getiID() {
        return iID;
    }

    public void setiID(String iID) {
        this.iID = iID;
    }

    public String getiLongitude() {
        return iLongitude;
    }

    public void setiLongitude(String iLongitude) {
        this.iLongitude = iLongitude;
    }

    public String getiLatitude() {
        return iLatitude;
    }

    public void setiLatitude(String iLatitude) {
        this.iLatitude = iLatitude;
    }
}
