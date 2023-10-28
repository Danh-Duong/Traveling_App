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

        tour_admin_lv=findViewById(R.id.tour_admin_lv);
        tours.add(new Tour("Vịnh Hạ Long","Quảng Ninh","0121267",R.drawable.main_anh1,"Đầm được hình thành do tuyến đê tả ngạn sông Đáy nhằm ngăn lũ lụt từ đó biển Vân Long trở thành 1 vùng trù phú",4,1000000,500000, 100,100));
        tours.add(new Tour("Vịnh Hạ Long","Quảng Ninh","0121267",R.drawable.main_anh5,"Đầm được hình thành do tuyến đê tả ngạn sông Đáy nhằm ngăn lũ lụt từ đó biển Vân Long trở thành 1 vùng trù phú",4,1000000,500000, 100,100));
        tours.add(new Tour("Vịnh Hạ Long","Quảng Ninh","0121267",R.drawable.main_anh2,"Đầm được hình thành do tuyến đê tả ngạn sông Đáy nhằm ngăn lũ lụt từ đó biển Vân Long trở thành 1 vùng trù phú",4,1000000,500000, 100,100));
        tours.add(new Tour("Vịnh Hạ Long","Quảng Ninh","0121267",R.drawable.main_anh4,"Đầm được hình thành do tuyến đê tả ngạn sông Đáy nhằm ngăn lũ lụt từ đó biển Vân Long trở thành 1 vùng trù phú",4,1000000,500000, 100,100));

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