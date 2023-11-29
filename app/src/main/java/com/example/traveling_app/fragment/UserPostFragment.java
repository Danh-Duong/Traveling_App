package com.example.traveling_app.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.traveling_app.R;
import com.example.traveling_app.UpdateUserInformationActivity;
import com.example.traveling_app.common.DatabaseReferences;
import com.example.traveling_app.common.StorageReferences;
import com.example.traveling_app.model.post.Post;
import com.example.traveling_app.model.post.PostRecycleViewAdapter;
import com.example.traveling_app.model.post.PostSnapshotParser;
import com.example.traveling_app.model.user.User;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UserPostFragment extends Fragment {

    private PostRecycleViewAdapter adapter;
    private String profileId;
    private DatabaseReference userProfileRef;
    private ValueEventListener valueEventListener;

    @Override
    @SuppressWarnings("deprecation")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileId = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("username", "defaultuser0");
        userProfileRef = DatabaseReferences.USER_DATABASE_REF.child(profileId);
        Query userPostQuery = DatabaseReferences.POST_DATABASE_REF.orderByChild("username").equalTo(profileId);
        FirebaseRecyclerOptions<Post> options = new FirebaseRecyclerOptions.Builder<Post>().setQuery(userPostQuery, PostSnapshotParser.INSTANCE).build();
        adapter = new PostRecycleViewAdapter(options);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_post, container, false);
        TextView usernameTextView = rootView.findViewById(R.id.username),
                descriptionTextView = rootView.findViewById(R.id.description);
        ImageView avatarPicture = rootView.findViewById(R.id.avatarImage);
        Button editProfileButton = rootView.findViewById(R.id.editProfileButton);

        if (valueEventListener != null)
            userProfileRef.removeEventListener(valueEventListener);
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                user.setUsername(snapshot.getKey());
                String description = user.getDescription();
                usernameTextView.setText(user.getFullName());
                descriptionTextView.setText(description == null ? getContext().getText(R.string.online) : description);
                if (user.isHasProfileImage())
                    StorageReferences.USER_AVATAR_PICTURES.child(user.getUsername() + ".jpeg").getDownloadUrl().addOnSuccessListener(uri -> Glide.with(getContext()).load(uri).circleCrop().into(avatarPicture));
                else
                    avatarPicture.setImageResource(R.drawable.user_profile_icon);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        editProfileButton.setOnClickListener(v -> startActivity(new Intent(getContext(), UpdateUserInformationActivity.class)));
        RecyclerView postRecyclerView = rootView.findViewById(R.id.postRecyclerView);
        postRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        postRecyclerView.setAdapter(adapter);
        adapter.startListening();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        userProfileRef.addValueEventListener(valueEventListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        userProfileRef.removeEventListener(valueEventListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adapter.stopListening();
    }


}