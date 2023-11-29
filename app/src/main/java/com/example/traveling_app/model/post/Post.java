package com.example.traveling_app.model.post;

import com.google.firebase.database.Exclude;

public class Post {
    @Exclude
    private String idPost;
    private String username;
    private String title;
    private long time;
    private boolean hasImage = false;
    public Post() {

    }

    public Post(String username, String title, long time, boolean hasImage) {
        this.username = username;
        this.title = title;
        this.time = time;
        this.hasImage = hasImage;
    }
    @Exclude
    public String getIdPost() {
        return idPost;
    }
    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }
    public String getTitle() {
        return title;
    }
    public String getUsername() {
        return username;
    }
    public long getTime() {
        return time;
    }

    public boolean isHasImage() {
        return hasImage;
    }
}
