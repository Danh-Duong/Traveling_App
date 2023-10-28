package com.example.traveling_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class luu_xacnhanthe extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button xacnhanthe_btn;
    private Spinner spin;

    String[] country = {"Việt Nam","Hoa Kỳ", "Nhật Bản","Trung Quốc", "Nga", "Hàn Quốc", "Trung Quốc", "Thụy Điển"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luu_xacnhanthe);
        xacnhanthe_btn = (Button) findViewById(R.id.xacnhanthe_btn);

        spin = (Spinner) findViewById(R.id.spinner);

        spin.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,country);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        xacnhanthe_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(luu_xacnhanthe.this, luu_notifysuccess.class);
                startActivity(intent);
            }
        });
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);
        actionBar.setTitle("Thanh toán");
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),country[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}