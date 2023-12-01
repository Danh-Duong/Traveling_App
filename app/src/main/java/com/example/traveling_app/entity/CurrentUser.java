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
    public CurrentUser(Activity activity,User user) {
        preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        editor = preferences.edit();
        editor.putString("username", user.getUsername());
        editor.putString("imageUrl", user.getProfileImage());
        editor.apply();
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
