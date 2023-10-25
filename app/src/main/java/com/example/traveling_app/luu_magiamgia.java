package com.example.traveling_app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.traveling_app.entity.luu_discount_adapter;
import com.example.traveling_app.entity.luu_discount_obj;

import java.util.ArrayList;
import java.util.List;

public class luu_magiamgia extends AppCompatActivity {
    private RecyclerView rcvDiscount;
    private luu_discount_adapter disAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luu_magiamgia);
        rcvDiscount = findViewById(R.id.rcv_discount);
        disAdapter = new luu_discount_adapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvDiscount.setLayoutManager(linearLayoutManager);

        disAdapter.setData(getListDiscount());
        rcvDiscount.setAdapter(disAdapter);


        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);
        actionBar.setTitle("Thanh toán");
    }

    private List<luu_discount_obj> getListDiscount() {
        List<luu_discount_obj> list = new ArrayList<>();
        list.add(new luu_discount_obj(R.drawable.luu_magiamgia_pic1, "Giảm 555k","608 đánh giá 5 sao","Có hiệu lực từ 10.9.2023-00:00"));
        list.add(new luu_discount_obj(R.drawable.luu_magiamgia_pic2, "Giảm 500k","48 đánh giá 5 sao","Có hiệu lực từ 10.9.2023-00:00"));
        list.add(new luu_discount_obj(R.drawable.luu_magiamgia_pic3, "Giảm 500k","48 đánh giá 5 sao","Có hiệu lực từ 10.9.2023-00:00"));
        list.add(new luu_discount_obj(R.drawable.luu_magiamgia_pic4, "Giảm 500k","48 đánh giá 5 sao","Có hiệu lực từ 10.9.2023-00:00"));
        list.add(new luu_discount_obj(R.drawable.luu_magiamgia_pic5, "Giảm 500k","48 đánh giá 5 sao","Có hiệu lực từ 10.9.2023-00:00"));

        list.add(new luu_discount_obj(R.drawable.luu_magiamgia_pic1, "Giảm 555k","608 đánh giá 5 sao","Có hiệu lực từ 10.9.2023-00:00"));
        list.add(new luu_discount_obj(R.drawable.luu_magiamgia_pic2, "Giảm 500k","48 đánh giá 5 sao","Có hiệu lực từ 10.9.2023-00:00"));
        list.add(new luu_discount_obj(R.drawable.luu_magiamgia_pic3, "Giảm 500k","48 đánh giá 5 sao","Có hiệu lực từ 10.9.2023-00:00"));
        list.add(new luu_discount_obj(R.drawable.luu_magiamgia_pic4, "Giảm 500k","48 đánh giá 5 sao","Có hiệu lực từ 10.9.2023-00:00"));
        list.add(new luu_discount_obj(R.drawable.luu_magiamgia_pic5, "Giảm 500k","48 đánh giá 5 sao","Có hiệu lực từ 10.9.2023-00:00"));

        list.add(new luu_discount_obj(R.drawable.luu_magiamgia_pic1, "Giảm 555k","608 đánh giá 5 sao","Có hiệu lực từ 10.9.2023-00:00"));
        list.add(new luu_discount_obj(R.drawable.luu_magiamgia_pic2, "Giảm 500k","48 đánh giá 5 sao","Có hiệu lực từ 10.9.2023-00:00"));
        list.add(new luu_discount_obj(R.drawable.luu_magiamgia_pic3, "Giảm 500k","48 đánh giá 5 sao","Có hiệu lực từ 10.9.2023-00:00"));
        list.add(new luu_discount_obj(R.drawable.luu_magiamgia_pic4, "Giảm 500k","48 đánh giá 5 sao","Có hiệu lực từ 10.9.2023-00:00"));
        list.add(new luu_discount_obj(R.drawable.luu_magiamgia_pic5, "Giảm 500k","48 đánh giá 5 sao","Có hiệu lực từ 10.9.2023-00:00"));
        return list;
    }



    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notification, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}