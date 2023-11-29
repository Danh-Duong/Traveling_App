package com.example.traveling_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Batdau_Activity extends AppCompatActivity {
    Button btn1;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batdau);

        ImageView imageView1= findViewById(R.id.img1);
        ImageView imageView2= findViewById(R.id.img);
        TextView textView1=findViewById(R.id.tv1);
        TextView textview2=findViewById(R.id.tv2);
        Button button= findViewById(R.id.btn1);

        Animation slideUp = new TranslateAnimation(0, 0, 300, 0);
        slideUp.setDuration(1800);

        Animation slideDown1 = new TranslateAnimation(0, 0, -300, 0);
        slideDown1.setDuration(1800);

        Animation slideDown2 = new TranslateAnimation(0, 0, -300, 0);
        slideDown2.setDuration(1000);
        // Áp dụng animation cho chữ và hình ảnh.
        textView1.startAnimation(slideUp);
        button.startAnimation(slideUp);
        textview2.startAnimation(slideUp);
        imageView1.startAnimation(slideDown1);
        imageView2.startAnimation(slideDown2);
        
        spinner=(Spinner)findViewById(R.id.spinner);
        btn1= (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(getApplicationContext(),Chondangnhap_Activity.class);
                startActivity(myintent);
            }
        });
        ArrayList<demo> list= new ArrayList<>();
        list.add(new demo(R.drawable.lacovn,"VIE"));
        list.add(new demo(R.drawable.lacoanh,"ENG"));

        demospinnerAdapter adapter= new demospinnerAdapter(this,list);
        spinner.setAdapter(adapter);
    }
}