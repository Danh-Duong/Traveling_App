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


import com.example.traveling_app.entity.CurrentUser;
import com.example.traveling_app.entity.LoadVoucher;
import com.example.traveling_app.entity.VoucherHelper;
import com.example.traveling_app.entity.luu_discount_adapter;
import com.example.traveling_app.entity.luu_discount_obj;
import com.example.traveling_app.entity.luu_history_adapter;
import com.example.traveling_app.entity.luu_history_obj;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class luu_lichsutour extends AppCompatActivity {

    private RecyclerView recyclerView;
    private luu_history_adapter adapter;
    private DatabaseReference ref;
    private ArrayList<luu_history_obj> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luu_lichsutour);
        LoadVoucher.load();
        recyclerView = (RecyclerView) findViewById(R.id.rcv_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ref = FirebaseDatabase.getInstance().getReference().child("booking");
        list = new ArrayList<>();
        adapter = new luu_history_adapter(this,list);
        recyclerView.setAdapter(adapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    luu_history_obj luuHistoryObj = dataSnapshot.getValue(luu_history_obj.class);
                    if (luuHistoryObj.getUsername().equals(CurrentUser.getCurrentUser().getUsername()))
                        list.add(luuHistoryObj);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);
        actionBar.setTitle("Lịch sử tour");
    }



    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notification, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            startActivity(new Intent(luu_lichsutour.this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}