package com.example.donatenowapp.Model;

public class HallFood {
    public String hfName;
    public String hfQuantity;
    public String hfDuration;
    public String hfPhone;
    public String hfImage;
    public String hID;
    public String hfID;
    public String isBooked;

    public HallFood() {
    }

    public HallFood(String hfName, String hfQuantity, String hfDuration, String hfPhone, String hfImage, String hID, String hfID, String isBooked) {
        this.hfName = hfName;
        this.hfQuantity = hfQuantity;
        this.hfDuration = hfDuration;
        this.hfPhone = hfPhone;
        this.hfImage = hfImage;
        this.hID = hID;
        this.hfID = hfID;
        this.isBooked = isBooked;
    }

    public String getHfName() {
        return hfName;
    }

    public void setHfName(String hfName) {
        this.hfName = hfName;
    }

    public String getHfQuantity() {
        return hfQuantity;
    }

    public void setHfQuantity(String hfQuantity) {
        this.hfQuantity = hfQuantity;
    }

    public String getHfDuration() {
        return hfDuration;
    }

    public void setHfDuration(String hfDuration) {
        this.hfDuration = hfDuration;
    }

    public String getHfPhone() {
        return hfPhone;
    }

    public void setHfPhone(String hfPhone) {
        this.hfPhone = hfPhone;
    }

    public String getHfImage() {
        return hfImage;
    }

    public void setHfImage(String hfImage) {
        this.hfImage = hfImage;
    }

    public String gethID() {
        return hID;
    }

    public void sethID(String hID) {
        this.hID = hID;
    }

    public String getHfID() {
        return hfID;
    }

    public void setHfID(String hfID) {
        this.hfID = hfID;
    }

    public String getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(String isBooked) {
        this.isBooked = isBooked;
    }
}
