package com.example.traveling_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.traveling_app.common.Constants;
import com.example.traveling_app.model.MenuListAdapter;
import com.example.traveling_app.model.MenuSectionItem;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        MenuListAdapter menuListAdapter = new MenuListAdapter(Constants.MENU_SECTION_ITEMS);
        ListView listView = findViewById(R.id.menuListItem);
        listView.setAdapter(menuListAdapter);
        listView.setOnItemClickListener(
            (AdapterView<?> parent, View view, int position, long id) -> {
                MenuSectionItem menuSectionItem = (MenuSectionItem) Constants.MENU_SECTION_ITEMS.get(position);
                if (menuSectionItem.getActivityClass() == null)
                    Toast.makeText(getApplicationContext(), getString(R.string.not_implemented), Toast.LENGTH_SHORT).show();
                else
                    startActivity(new Intent(getApplicationContext(), menuSectionItem.getActivityClass()));
            }
        );
    }
}