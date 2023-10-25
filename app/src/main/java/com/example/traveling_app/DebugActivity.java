package com.example.traveling_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.content.pm.ActivityInfo;
import com.example.traveling_app.model.ActivityListAdapter;
import android.widget.Toast;

public class DebugActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        final String ACTIVITY_NAME = getClass().getName();
        ListView listView = findViewById(R.id.activityListView);
        ActivityListAdapter activityListAdapter = ActivityListAdapter.createInstance(getApplicationContext(), getApplicationContext().getPackageName());
        listView.setAdapter(activityListAdapter);
        activityListAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Object selectedItem = parent.getItemAtPosition(position);
            if (selectedItem instanceof ActivityInfo) {
                if (((ActivityInfo)selectedItem).name.equals(ACTIVITY_NAME))
                    Toast.makeText(getApplicationContext(), "Current activity is opening", Toast.LENGTH_SHORT).show();
                else {
                    try {
                        Intent intent = new Intent(getApplicationContext(), Class.forName(((ActivityInfo)selectedItem).name));
                        startActivity(intent);
                    } catch (ClassNotFoundException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

}