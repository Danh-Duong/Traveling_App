package com.example.traveling_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.traveling_app.common.DatabaseReferences;
import com.example.traveling_app.model.post.Post;
import com.example.traveling_app.model.post.PostSnapshotParser;

public class CommentDebugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_debug);
        EditText postIdEditText = findViewById(R.id.postIdEditText);
        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(nothing -> {
            DatabaseReferences.POST_DATABASE_REF.child(postIdEditText.getText().toString()).get().addOnSuccessListener(dataSnapshot -> {
                Post post = PostSnapshotParser.INSTANCE.parseSnapshot(dataSnapshot);
                Intent intent = new Intent(this, CommentActivity.class);
                intent.putExtra(CommentActivity.POST_PARAM, post);
                startActivity(intent);
//                CommentDialog dialog = new CommentDialog(this, PreferenceManager.getDefaultSharedPreferences(this).getString("username", Constants.DEFAULT_USERNAME), post);
//                dialog.show();
            });
        });

    }
}