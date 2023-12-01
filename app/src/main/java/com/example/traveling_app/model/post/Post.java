package com.example.traveling_app.model.post;

import com.google.firebase.database.Exclude;

public class Post {
    @Exclude
    private String idPost;
    private String postImgUrl;
    private String username;
    private String title;
    private long time;

    public Post() {
    }

    public Post(String idPost, String postImgUrl, String username, String title, long time) {
        this.idPost = idPost;
        this.postImgUrl = postImgUrl;
        this.username = username;
        this.title = title;
        this.time = time;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    public String getPostImgUrl() {
        return postImgUrl;
    }

    public void setPostImgUrl(String postImgUrl) {
        this.postImgUrl = postImgUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
