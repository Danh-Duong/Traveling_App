package com.example.traveling_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.traveling_app.entity.LoadVoucher;
import com.example.traveling_app.entity.VoucherHelper;
import com.example.traveling_app.entity.luu_discount_adapter;
import com.example.traveling_app.entity.luu_discount_obj;
import com.example.traveling_app.entity.luu_history_adapter;
import com.example.traveling_app.entity.luu_history_obj;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class luu_lichsutour extends AppCompatActivity {

    private RecyclerView recyclerView;
    private luu_history_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luu_lichsutour);
        LoadVoucher.load();
        recyclerView = (RecyclerView) findViewById(R.id.rcv_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<luu_history_obj> options =
                new FirebaseRecyclerOptions.Builder<luu_history_obj>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("booking"), luu_history_obj.class)
                        .build();

        adapter = new luu_history_adapter(options);
        recyclerView.setAdapter(adapter);


        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);
        actionBar.setTitle("Lịch sử tour");
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
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