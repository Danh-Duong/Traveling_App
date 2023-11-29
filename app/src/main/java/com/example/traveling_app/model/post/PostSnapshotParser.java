package com.example.traveling_app.model.post;

import androidx.annotation.NonNull;

import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;

public class PostSnapshotParser {
    public static Parser INSTANCE = new Parser();

    private static class Parser implements SnapshotParser<Post> {
        @NonNull
        @Override
        public Post parseSnapshot(@NonNull DataSnapshot snapshot) {
            Post post = snapshot.getValue(Post.class);
            post.setIdPost(snapshot.getKey());
            return post;
        }
    }
}


