package com.example.traveling_app.model.post;

import java.text.DateFormat;
import java.util.Date;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.traveling_app.R;
import com.example.traveling_app.common.DatabaseReferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

class PostViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleTextView, usernameTextView, timeTextView;
    private final ImageView userAvatarImageView, thumbnailImageView;
    private final DateFormat dateFormat, timeFormat;
    private final CardView cardView;
    private final RequestManager imageLoader;

    public PostViewHolder(@NonNull View itemView, DateFormat dateFormat, DateFormat timeFormat, RequestManager imageLoader) {
        super(itemView);
        this.dateFormat = dateFormat;
        this.timeFormat = timeFormat;
        this.imageLoader = imageLoader;
        titleTextView = itemView.findViewById(R.id.title);
        usernameTextView = itemView.findViewById(R.id.username);
        timeTextView = itemView.findViewById(R.id.timeTextView);
        cardView = itemView.findViewById(R.id.thumbnailCardView);
        userAvatarImageView = itemView.findViewById(R.id.userAvatar);
        thumbnailImageView = itemView.findViewById(R.id.thumbnail);
    }

    public void bindPostToView(Post post) {
        titleTextView.setText(post.getTitle());
        post.getFullNameAsync(usernameTextView::setText);

        Date time = new Date(post.getTime());
        timeTextView.setText(dateFormat.format(time) + " " + timeFormat.format(time));

        post.getProfileImageUrlAsync(url -> {
            if (url == null)
                imageLoader.load(R.drawable.user_profile_icon).into(userAvatarImageView);
            else
                imageLoader.load(url).circleCrop().into(userAvatarImageView);
        });

        if (post.getPostImgUrl() != null) {
            cardView.setVisibility(View.VISIBLE);
            imageLoader.load(post.getPostImgUrl()).into(thumbnailImageView);
        }

        else {
            cardView.setVisibility(View.GONE);
            thumbnailImageView.setImageResource(0);
        }
    }
}
