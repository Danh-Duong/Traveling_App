package com.example.traveling_app.entity;

public class Voucher {
    private String id;
    private int discount;
    private int numStar;
    private String efDatetime;
    private String pUrl;

    public Voucher() {
    }

    public Voucher(String id, int discount, int numStar, String efDatetime, String pUrl) {
        this.id = id;
        this.discount = discount;
        this.numStar = numStar;
        this.efDatetime = efDatetime;
        this.pUrl = pUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getNumStar() {
        return numStar;
    }

    public void setNumStar(int numStar) {
        this.numStar = numStar;
    }

    public String getEfDatetime() {
        return efDatetime;
    }

    public void setEfDatetime(String efDatetime) {
        this.efDatetime = efDatetime;
    }

    public String getpUrl() {
        return pUrl;
    }

    public void setpUrl(String pUrl) {
        this.pUrl = pUrl;
    }
}

