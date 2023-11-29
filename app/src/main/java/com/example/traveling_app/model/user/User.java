package com.example.traveling_app.model.user;
import com.google.firebase.database.Exclude;

public class User {
    @Exclude
    private String username;
    private String fullName;
    private String password;
    private int birthday;
    private String description;
    private boolean gender;
    private boolean hasProfileImage;

    public User() {

    }

    public User(String username, String fullName, String password, String description, int birthday, boolean gender, boolean hasProfileImage) {
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.description = description;
        this.fullName = fullName;
        this.gender = gender;
        this.hasProfileImage = hasProfileImage;
    }
    @Exclude
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isHasProfileImage() {
        return hasProfileImage;
    }

    public void setHasProfileImage(boolean hasProfileImage) {
        this.hasProfileImage = hasProfileImage;
    }

    @Override
    public String toString() {
        return "username = " + username + ", fullName = " + fullName + ", birthday = " + birthday + ", description = " + description + ", gender = " + gender;
    }

}

