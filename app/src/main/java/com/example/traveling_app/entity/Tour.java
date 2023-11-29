package com.example.traveling_app.entity;

public class Tour {
    private String name;
    private String address;
    private String phone;
    private int mainImage;
    private String content;
    private int numStar;
    private double price;
    private double salePrice;
    private int numComment;
    private int numBooking;

    public Tour(String name, String address, String phone, int mainImage, String content, int numStar, double price, double salePrice, int numComment, int numBooking) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.mainImage = mainImage;
        this.content = content;
        this.numStar = numStar;
        this.price = price;
        this.salePrice = salePrice;
        this.numComment = numComment;
        this.numBooking = numBooking;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMainImage() {
        return mainImage;
    }

    public void setMainImage(int mainImage) {
        this.mainImage = mainImage;
    }

    public int getNumStar() {
        return numStar;
    }

    public void setNumStar(int numStar) {
        this.numStar = numStar;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNumComment() {
        return numComment;
    }

    public void setNumComment(int numComment) {
        this.numComment = numComment;
    }

    public int getNumBooking() {
        return numBooking;
    }

    public void setNumBooking(int numBooking) {
        this.numBooking = numBooking;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }
}
