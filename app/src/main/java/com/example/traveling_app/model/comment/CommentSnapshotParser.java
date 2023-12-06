package com.example.traveling_app.model.comment;
import androidx.annotation.NonNull;

import com.example.traveling_app.common.DatabaseReferences;
import com.example.traveling_app.model.user.User;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class CommentSnapshotParser implements SnapshotParser<Comment> {
    public static CommentSnapshotParser INSTANCE = new CommentSnapshotParser();

    private CommentSnapshotParser() {

    }

    @NonNull
    @Override
    public Comment parseSnapshot(@NonNull DataSnapshot snapshot) {
        Comment comment = snapshot.getValue(Comment.class);
        comment.setCommentId(snapshot.getKey());
        DatabaseReferences.USER_DATABASE_REF.child(comment.getUsername()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                comment.setFullName(user.getFullName());
                comment.setProfileImageUrl(user.getProfileImage());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return comment;
    }
}
