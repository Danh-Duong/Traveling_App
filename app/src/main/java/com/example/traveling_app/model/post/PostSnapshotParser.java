package com.example.traveling_app.model.post;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.traveling_app.common.DatabaseReferences;
import com.example.traveling_app.model.user.User;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Optional;

public class PostSnapshotParser implements SnapshotParser<Post> {
    public static PostSnapshotParser INSTANCE = new PostSnapshotParser();

    private PostSnapshotParser() {

    }

    @NonNull
    @Override
    public Post parseSnapshot(@NonNull DataSnapshot snapshot) {
        Post post = snapshot.getValue(Post.class);
        post.setIdPost(snapshot.getKey());
        DatabaseReferences.USER_DATABASE_REF.child(post.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                post.setFullName(user.getFullName());
                post.setProfileImageUrl(user.getProfileImage());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return post;
    }
}


