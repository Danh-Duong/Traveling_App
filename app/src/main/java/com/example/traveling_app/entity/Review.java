package com.example.traveling_app.entity;

import java.util.Date;
import java.util.List;

public class Review {
    private String name;
    private int mainImg;
    private int rate;
    private Date time;
    private String content;
    private List<Integer> images;

    public Review(String name, int mainImg, int rate, Date time, String content) {
        this.name = name;
        this.mainImg = mainImg;
        this.rate = rate;
        this.time = time;
        this.content = content;
    }

    public int getMainImg() {
        return mainImg;
    }

    public void setMainImg(int mainImg) {
        this.mainImg = mainImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Integer> getImages() {
        return images;
    }

    public void setImages(List<Integer> images) {
        this.images = images;
    }
}
