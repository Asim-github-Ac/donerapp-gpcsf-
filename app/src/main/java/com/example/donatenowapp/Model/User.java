package com.example.donatenowapp.Model;

public class User {
    public String uName;
    public String uEmail;
    public String uPhone;
    public String uAddress;
    public String uPassword;
    public String uImg;
    public String uID;
    public String uType;
    public String uCardID;

    public User(String uName, String uEmail, String uPhone, String uAddress, String uPassword, String uImg, String uID, String uType, String uCardID) {
        this.uName = uName;
        this.uEmail = uEmail;
        this.uPhone = uPhone;
        this.uAddress = uAddress;
        this.uPassword = uPassword;
        this.uImg = uImg;
        this.uID = uID;
        this.uType = uType;
        this.uCardID = uCardID;
    }

    public User() {
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuAddress() {
        return uAddress;
    }

    public void setuAddress(String uAddress) {
        this.uAddress = uAddress;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuImg() {
        return uImg;
    }

    public void setuImg(String uImg) {
        this.uImg = uImg;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getuType() {
        return uType;
    }

    public void setuType(String uType) {
        this.uType = uType;
    }

    public String getuCardID() {
        return uCardID;
    }

    public void setuCardID(String uCardID) {
        this.uCardID = uCardID;
    }
}
