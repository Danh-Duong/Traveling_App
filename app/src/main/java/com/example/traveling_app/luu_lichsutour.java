package com.example.traveling_app;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.traveling_app.entity.luu_history_adapter;
import com.example.traveling_app.entity.luu_history_obj;

import java.util.ArrayList;
import java.util.List;

public class luu_lichsutour extends AppCompatActivity {

    private RecyclerView rcvHistory;
    private luu_history_adapter historyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luu_lichsutour);
        rcvHistory = findViewById(R.id.rcv_history);
        historyAdapter = new luu_history_adapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvHistory.setLayoutManager(linearLayoutManager);
        historyAdapter.setData(getListHistory());
        rcvHistory.setAdapter(historyAdapter);


        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);
        actionBar.setTitle("Lịch sử tour");
    }

    private List<luu_history_obj> getListHistory() {
        List<luu_history_obj> list = new ArrayList<>();
        list.add(new luu_history_obj("Tour VinOasis Phú Quốc", "VND 1.500.000", "27 June 2023 - 28 June 2019","Active"));
        list.add(new luu_history_obj("Tour VinOasis Phú Quốc", "VND 1.500.000", "27 June 2023 - 28 June 2019","Active"));
        list.add(new luu_history_obj("Tour VinOasis Phú Quốc", "VND 1.500.000", "27 June 2023 - 28 June 2019","Active"));
        list.add(new luu_history_obj("Tour VinOasis Phú Quốc", "VND 1.500.000", "27 June 2023 - 28 June 2019","Active"));
        list.add(new luu_history_obj("Tour VinOasis Phú Quốc", "VND 1.500.000", "27 June 2023 - 28 June 2019","Active"));
        list.add(new luu_history_obj("Tour VinOasis Phú Quốc", "VND 1.500.000", "27 June 2023 - 28 June 2019","Active"));


        list.add(new luu_history_obj("Tour VinOasis Phú Quốc", "VND 1.500.000", "27 June 2023 - 28 June 2019","Active"));
        list.add(new luu_history_obj("Tour VinOasis Phú Quốc", "VND 1.500.000", "27 June 2023 - 28 June 2019","Active"));
        list.add(new luu_history_obj("Tour VinOasis Phú Quốc", "VND 1.500.000", "27 June 2023 - 28 June 2019","Active"));
        list.add(new luu_history_obj("Tour VinOasis Phú Quốc", "VND 1.500.000", "27 June 2023 - 28 June 2019","Active"));
        list.add(new luu_history_obj("Tour VinOasis Phú Quốc", "VND 1.500.000", "27 June 2023 - 28 June 2019","Active"));
        list.add(new luu_history_obj("Tour VinOasis Phú Quốc", "VND 1.500.000", "27 June 2023 - 28 June 2019","Active"));
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