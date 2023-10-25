package com.example.traveling_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Batdau_Activity extends AppCompatActivity {
    Button btn1;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batdau);
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