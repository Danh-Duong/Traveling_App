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

    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    public CurrentUser(Activity activity, User user) {
        if (preferences == null) {
            preferences = PreferenceManager.getDefaultSharedPreferences(activity);
            editor = preferences.edit();
        }

        // Tiếp tục với phần còn lại của hàm khởi tạo
        editor.putString("username", user.getUsername());
        editor.putString("imageUrl", user.getProfileImage());
        editor.apply();
    }

    public static User getCurrentUser() {
        // Kiểm tra nếu preferences chưa được khởi tạo thì trả về giá trị mặc định
        if (preferences == null) {
            return new User("", ""); // hoặc trả về giá trị mặc định phù hợp
        }

        String email = preferences.getString("username", "");
        String imageUrl = preferences.getString("imageUrl", "");
        return new User(email, imageUrl);

    }

    public static void remove() {
        // Kiểm tra nếu preferences chưa được khởi tạo thì không làm gì cả
        if (preferences != null) {
            editor.clear();
            editor.apply();
        }
    }
}