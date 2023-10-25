package com.example.traveling_app.entity;

public class luu_discount_obj {
    private int resourceId;
    private String text1;
    private String text2;
    private String text3;

    public luu_discount_obj(int resourceId, String text1, String text2, String text3) {
        this.resourceId = resourceId;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }
}
