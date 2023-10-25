package com.example.traveling_app.entity;

public class Notification {
    private int id;
    private int image;
    private String content;
    private String time;
    private boolean isNew;

    public Notification(int id,int image, String content, String time, boolean isNew) {
        this.id=id;
        this.image = image;
        this.content = content;
        this.time = time;
        this.isNew=isNew;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        isNew = isNew;
    }
}
