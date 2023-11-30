package com.example.traveling_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.traveling_app.entity.AdminTourAdapter;
import com.example.traveling_app.entity.Tour;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private ListView tour_admin_lv;
    private List<Tour> tours=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);



        AdminTourAdapter adapter=new AdminTourAdapter(tours, this);
        tour_admin_lv.setAdapter(adapter);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);
        actionBar.setTitle("Admin");

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            this.finish();
            return true;
        }
        else if (item.getItemId()==R.id.create_tour){
            Intent intent=new Intent(this, AdminCreateActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}