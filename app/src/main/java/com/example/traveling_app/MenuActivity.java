package com.example.traveling_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.PopupMenu;
import com.example.traveling_app.model.MenuListAdapter;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Menu menu = new PopupMenu(getApplicationContext(), null).getMenu();
        getMenuInflater().inflate(R.menu.menu_list_item, menu);
        MenuListAdapter menuListAdapter = new MenuListAdapter(menu);
        ListView listView = findViewById(R.id.menuListItem);
        listView.setAdapter(menuListAdapter);
    }
}