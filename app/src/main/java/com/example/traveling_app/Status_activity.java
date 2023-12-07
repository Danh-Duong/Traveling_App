package com.example.traveling_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.traveling_app.model.post.Post;

public class Status_activity extends AppCompatActivity {
    private Post post;
    public static final String POST_PARAM = "post";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        post = getIntent().getExtras().getParcelable(POST_PARAM);
        RequestManager imageLoader = Glide.with(this);
        if (post == null)
            finish();
        setContentView(R.layout.status_main);
        ImageView commentImageView = findViewById(R.id.imageView4),
                back = findViewById(R.id.back_detail_blog),
                avatarImageView = findViewById(R.id.avatarPictureImageView),
                postImage = findViewById(R.id.postImageView);

        TextView usernameTextView = findViewById(R.id.usernameTextView),
                postContentTextView = findViewById(R.id.postContentTextView);

        commentImageView.setOnClickListener(this::openComment);
        back.setOnClickListener(v -> finish());
        post.getFullNameAsync(usernameTextView::setText);
        post.getProfileImageUrlAsync(url -> {
            if (url == null)
                imageLoader.load(R.drawable.user_profile_icon).into(avatarImageView);
            else
                imageLoader.load(url).into(avatarImageView);
        });
        postContentTextView.setText(post.getTitle());
        if (post.getPostImgUrl() == null) {
            
        }
    }

    private void openComment(View v) {
        Intent intent = new Intent(this, CommentActivity.class);
        intent.putExtra(CommentActivity.POST_PARAM, post);
        startActivity(intent);
    }



}
