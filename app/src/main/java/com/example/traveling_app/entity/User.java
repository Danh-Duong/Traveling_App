package com.example.traveling_app.entity;

import java.io.Serializable;

@Deprecated
public class User implements Serializable {
    private String email;
    private String username;
    private String fullName;
    private String password;
    private String token;
    private String profileImage;
    private String description;
    private int birthday;
    private boolean gender;

    private int role;

    public User() {
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String username, String profileImage, int role) {
        this.username = username;
        this.profileImage = profileImage;
        this.role=role;
    }

    public User(String username, String fullName, String password, String description, int birthday, boolean gender, String profileImage) {
        this.password = password;
        this.birthday = birthday;
        this.description = description;
        this.fullName = fullName;
        this.gender = gender;
        this.profileImage = profileImage;
    }

    public User(String email, String username, String password, String profileImage) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.profileImage = profileImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBirthday() {
        return birthday;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public boolean isGender() {
        return gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
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
                ", profileImage='" + profileImage + '\'' +
                '}';
    }
}