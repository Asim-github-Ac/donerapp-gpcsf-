package com.example.donatenowapp.Model;

public class Order {
    public String oName;
    public String oPhone;
    public String oAddress;
    public String cardID;
    public String uID;
    public String oKey;
    public String rID;


    public Order(String oName, String oPhone, String oAddress, String cardID, String uID, String oKey, String rID) {
        this.oName = oName;
        this.oPhone = oPhone;
        this.oAddress = oAddress;
        this.cardID = cardID;
        this.uID = uID;
        this.oKey = oKey;
        this.rID = rID;
    }

    public Order() {
    }

    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName;
    }

    public String getoPhone() {
        return oPhone;
    }

    public void setoPhone(String oPhone) {
        this.oPhone = oPhone;
    }

    public String getoAddress() {
        return oAddress;
    }

    public void setoAddress(String oAddress) {
        this.oAddress = oAddress;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getoKey() {
        return oKey;
    }

    public void setoKey(String oKey) {
        this.oKey = oKey;
    }

    public String getrID() {
        return rID;
    }

    public void setrID(String rID) {
        this.rID = rID;
    }
}
