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
import com.example.traveling_app.R;
import com.example.traveling_app.common.DatabaseReferences;
import com.example.traveling_app.common.StorageReferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

public class PostViewHolder extends RecyclerView.ViewHolder {
    private TextView titleTextView, usernameTextView, timeTextView;
    private ImageView userAvatarImageView, thumbnailImageView;
    private DateFormat dateFormat, timeFormat;
    private CardView cardView;
    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        dateFormat = android.text.format.DateFormat.getLongDateFormat(itemView.getContext());
        timeFormat = android.text.format.DateFormat.getTimeFormat(itemView.getContext());
        titleTextView = itemView.findViewById(R.id.title);
        usernameTextView = itemView.findViewById(R.id.username);
        timeTextView = itemView.findViewById(R.id.timeTextView);
        cardView = itemView.findViewById(R.id.thumbnailCardView);
        userAvatarImageView = itemView.findViewById(R.id.userAvatar);
        thumbnailImageView = itemView.findViewById(R.id.thumbnail);
    }

    public void bindPostToView(Post post) {
        titleTextView.setText(post.getTitle());
        usernameTextView.setText(post.getUsername());
        Date time = new Date(post.getTime());
        timeTextView.setText(dateFormat.format(time) + " " + timeFormat.format(time));
        userAvatarImageView.setImageResource(0);
        if (post.isHasImage()) {
            StorageReference ref = StorageReferences.POST_IMAGE_STORAGE_REF.child(post.getIdPost() + ".jpeg");
            ref.getDownloadUrl().addOnSuccessListener(url -> {
                cardView.setVisibility(View.VISIBLE);
                Glide.with(thumbnailImageView).load(url).into(thumbnailImageView);

            });
        }
        else {
            cardView.setVisibility(View.GONE);
            thumbnailImageView.setImageResource(0);
        }

        DatabaseReferences.USER_DATABASE_REF.child(post.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String profileImageUrl = snapshot.child("profileImage").getValue(String.class);
                if (profileImageUrl != null)
                    Glide.with(userAvatarImageView.getContext()).load(profileImageUrl).circleCrop().into(userAvatarImageView);
                else
                    userAvatarImageView.setImageResource(R.drawable.user_profile_icon);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
