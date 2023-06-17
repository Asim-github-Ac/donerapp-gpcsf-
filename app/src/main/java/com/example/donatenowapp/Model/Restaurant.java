package com.example.donatenowapp.Model;

public class Restaurant {
    public String rName;
    public String rLocation;
    public String rDes;
    public String rWebUrl;
    public String rPhone;
    public String rImageUrl;
    public String rID;
    public String rStatus;
    public String rLongitude;
    public String rLatitude;

    public String getrLongitude() {
        return rLongitude;
    }

    public void setrLongitude(String rLongitude) {
        this.rLongitude = rLongitude;
    }

    public String getrLatitude() {
        return rLatitude;
    }

    public void setrLatitude(String rLatitude) {
        this.rLatitude = rLatitude;
    }

    public Restaurant() {
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrLocation() {
        return rLocation;
    }

    public void setrLocation(String rLocation) {
        this.rLocation = rLocation;
    }

    public String getrDes() {
        return rDes;
    }

    public void setrDes(String rDes) {
        this.rDes = rDes;
    }

    public String getrWebUrl() {
        return rWebUrl;
    }

    public void setrWebUrl(String rWebUrl) {
        this.rWebUrl = rWebUrl;
    }

    public String getrPhone() {
        return rPhone;
    }

    public void setrPhone(String rPhone) {
        this.rPhone = rPhone;
    }

    public String getrImageUrl() {
        return rImageUrl;
    }

    public void setrImageUrl(String rImageUrl) {
        this.rImageUrl = rImageUrl;
    }

    public String getrID() {
        return rID;
    }

    public void setrID(String rID) {
        this.rID = rID;
    }

    public String getrStatus() {
        return rStatus;
    }

    public void setrStatus(String rStatus) {
        this.rStatus = rStatus;
    }

    public Restaurant(String rName, String rLocation, String rDes, String rWebUrl, String rPhone, String rImageUrl, String rID, String rStatus, String rLongitude, String rLatitude) {
        this.rName = rName;
        this.rLocation = rLocation;
        this.rDes = rDes;
        this.rWebUrl = rWebUrl;
        this.rPhone = rPhone;
        this.rImageUrl = rImageUrl;
        this.rID = rID;
        this.rStatus = rStatus;
        this.rLongitude = rLongitude;
        this.rLatitude = rLatitude;
    }
}
