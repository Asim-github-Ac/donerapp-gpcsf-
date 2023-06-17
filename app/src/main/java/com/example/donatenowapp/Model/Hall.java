package com.example.donatenowapp.Model;

public class Hall {
    public String hName;
    public String hAddress;
    public String hUrl;
    public String hPhone;
    public String hEmail;
    public String hImageUrl;
    public String hID;
    public String hStatus;
    public String hLongitude;
    public String hLatitude;

    public Hall(String hName, String hAddress, String hUrl, String hPhone, String hEmail, String hImageUrl, String hID, String hStatus, String hLongitude, String hLatitude) {
        this.hName = hName;
        this.hAddress = hAddress;
        this.hUrl = hUrl;
        this.hPhone = hPhone;
        this.hEmail = hEmail;
        this.hImageUrl = hImageUrl;
        this.hID = hID;
        this.hStatus = hStatus;
        this.hLongitude = hLongitude;
        this.hLatitude = hLatitude;
    }

    public Hall() {
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String gethAddress() {
        return hAddress;
    }

    public void sethAddress(String hAddress) {
        this.hAddress = hAddress;
    }

    public String gethUrl() {
        return hUrl;
    }

    public void sethUrl(String hUrl) {
        this.hUrl = hUrl;
    }

    public String gethPhone() {
        return hPhone;
    }

    public void sethPhone(String hPhone) {
        this.hPhone = hPhone;
    }

    public String gethEmail() {
        return hEmail;
    }

    public void sethEmail(String hEmail) {
        this.hEmail = hEmail;
    }

    public String gethImageUrl() {
        return hImageUrl;
    }

    public void sethImageUrl(String hImageUrl) {
        this.hImageUrl = hImageUrl;
    }

    public String gethID() {
        return hID;
    }

    public void sethID(String hID) {
        this.hID = hID;
    }

    public String gethStatus() {
        return hStatus;
    }

    public void sethStatus(String hStatus) {
        this.hStatus = hStatus;
    }

    public String gethLongitude() {
        return hLongitude;
    }

    public void sethLongitude(String hLongitude) {
        this.hLongitude = hLongitude;
    }

    public String gethLatitude() {
        return hLatitude;
    }

    public void sethLatitude(String hLatitude) {
        this.hLatitude = hLatitude;
    }
}
