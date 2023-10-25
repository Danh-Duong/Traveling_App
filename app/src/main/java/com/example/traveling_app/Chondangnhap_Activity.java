package com.example.traveling_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Chondangnhap_Activity extends AppCompatActivity {

    Button chondangnhap_btn1,chondangnhap_btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chondangnhap);
        chondangnhap_btn1=(Button) findViewById(R.id.chondangnhap_btn1);
        chondangnhap_btn2=(Button) findViewById(R.id.chondangnhap_btn2);
        chondangnhap_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent1 = new Intent(getApplicationContext(), activity_dangnhap.class);
                startActivity(myintent1);
            }
        });
        chondangnhap_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent2 = new Intent(getApplicationContext(), activity_dangky.class);
                startActivity(myintent2);
            }
        });
    }
} 