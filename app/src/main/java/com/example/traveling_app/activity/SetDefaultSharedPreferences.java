package com.example.traveling_app.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.traveling_app.R;

public class SetDefaultSharedPreferences extends Activity {
    @Override
    @SuppressWarnings("deprecation")
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_set_default_shared_preferences);
        final SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        final EditText usernameEditText = findViewById(R.id.usernameEditText);
        final Button sumbitButton = findViewById(R.id.submitButton);
        sumbitButton.setOnClickListener(nothing -> editor.putString("username", usernameEditText.getText().toString()).commit());
    }
}
