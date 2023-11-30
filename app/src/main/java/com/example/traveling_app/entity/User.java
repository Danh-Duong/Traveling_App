package com.example.traveling_app.entity;

public class User{
    private String email;
    private String username;
    private String password;
    private String token;
    private String imageUrl;
    public User() {
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String username, String imageUrl) {
        this.username = username;
        this.imageUrl = imageUrl;
    }

    public User(String email, String username, String password, String imageUrl) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.imageUrl = imageUrl;
    }



    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}