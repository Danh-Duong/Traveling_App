package com.example.traveling_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class activity_succesful extends AppCompatActivity {

    private Button at2_btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succesful);

        at2_btn1=findViewById(R.id.at2_btn1);
        at2_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity_succesful.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}