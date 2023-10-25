package com.example.traveling_app.entity;

public class luu_history_obj {
    private String name;
    private String price;
    private String date;
    private String state;

    public luu_history_obj(String name, String price, String date, String state) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}