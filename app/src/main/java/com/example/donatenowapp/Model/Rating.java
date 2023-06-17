package com.example.donatenowapp.Model;

public class Rating {
    public String rMsg;
    public String rStart;
    public String rDate;
    public String uID;
    public String uName;
    public String pID;
    public String uKey;

    public Rating(String rMsg, String rStart, String rDate, String uID, String uName, String pID, String uKey) {
        this.rMsg = rMsg;
        this.rStart = rStart;
        this.rDate = rDate;
        this.uID = uID;
        this.uName = uName;
        this.pID = pID;
        this.uKey = uKey;
    }

    public Rating() {
    }

    public String getrMsg() {
        return rMsg;
    }

    public void setrMsg(String rMsg) {
        this.rMsg = rMsg;
    }

    public String getrStart() {
        return rStart;
    }

    public void setrStart(String rStart) {
        this.rStart = rStart;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public String getuKey() {
        return uKey;
    }

    public void setuKey(String uKey) {
        this.uKey = uKey;
    }
}
