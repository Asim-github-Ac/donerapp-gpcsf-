package com.example.donatenowapp.Model;

public class FoodDonate {
    public String fName;
    public String fDes;
    public String fImg;
    public String rID;
    public String uID;
    public String fID;

    public FoodDonate(String fName, String fDes, String fImg, String rID, String uID, String fID) {
        this.fName = fName;
        this.fDes = fDes;
        this.fImg = fImg;
        this.rID = rID;
        this.uID = uID;
        this.fID = fID;
    }

    public FoodDonate() {
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfDes() {
        return fDes;
    }

    public void setfDes(String fDes) {
        this.fDes = fDes;
    }

    public String getfImg() {
        return fImg;
    }

    public void setfImg(String fImg) {
        this.fImg = fImg;
    }

    public String getrID() {
        return rID;
    }

    public void setrID(String rID) {
        this.rID = rID;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getfID() {
        return fID;
    }

    public void setfID(String fID) {
        this.fID = fID;
    }
}
