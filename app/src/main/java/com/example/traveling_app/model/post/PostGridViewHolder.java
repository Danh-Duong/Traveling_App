package com.example.traveling_app.model.post;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.traveling_app.R;
import com.example.traveling_app.Status_activity;

class PostGridViewHolder extends RecyclerView.ViewHolder {
    private final TextView usernameTextView;
    private final ImageView postImageView;
    private final RequestManager imageLoader;
    private Post bindedPost;

    public PostGridViewHolder(@NonNull View itemView, RequestManager imageLoader) {
        super(itemView);
        itemView.setOnClickListener(this::onPostItemClick);
        this.imageLoader = imageLoader;
        usernameTextView = itemView.findViewById(R.id.name_user_blog);
        postImageView = itemView.findViewById(R.id.img_blog);
    }

    public void bindDataToView(Post post) {
        this.bindedPost = post;
        post.getFullNameAsync(usernameTextView::setText);
        if (post.getPostImgUrl() != null)
            imageLoader.load(post.getPostImgUrl()).into(postImageView);
    }

    private void onPostItemClick(View v) {
        Context context = v.getContext();
        Intent intent = new Intent(context, Status_activity.class);
        intent.putExtra(Status_activity.POST_PARAM, bindedPost);
        context.startActivity(intent);
    }
}
