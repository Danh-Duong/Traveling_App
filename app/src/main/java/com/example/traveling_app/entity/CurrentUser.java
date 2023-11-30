package com.example.traveling_app.entity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CurrentUser {

    private DatabaseReference ref= FirebaseDatabase.getInstance().getReference("users");
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    public CurrentUser(Activity activity,String username) {
        preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        editor = preferences.edit();
        ref.child(username).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    User u = snapshot.getValue(User.class);
                    editor.putString("username", u.getUsername());
                    editor.putString("imageUrl", u.getImageUrl());
                    editor.apply();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static User getCurrentUser(){
        String email = preferences.getString("username", "");
        String imageUrl = preferences.getString("imageUrl", "");
        return new User(email,imageUrl);
    }
    public static void remove(){
        editor.clear();
        editor.apply();
    }
}
