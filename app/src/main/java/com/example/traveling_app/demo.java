package com.example.traveling_app;

public class demo {
    private int image;
    private String name;

    public demo(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public demo() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
