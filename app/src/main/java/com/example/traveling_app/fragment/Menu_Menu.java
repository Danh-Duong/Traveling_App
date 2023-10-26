package com.example.traveling_app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupMenu;

import com.example.traveling_app.MainActivity;
import com.example.traveling_app.MenuActivity;
import com.example.traveling_app.R;
import com.example.traveling_app.model.MenuListAdapter;

public class Menu_Menu extends Fragment {

    MainActivity mainActivity;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainActivity= (MainActivity) getActivity();
        view= inflater.inflate(R.layout.fragment_menu__menu, container, false);
        Menu menu = new PopupMenu(mainActivity.getApplicationContext(), null).getMenu();
        mainActivity.getMenuInflater().inflate(R.menu.menu_list_item, menu);
        MenuListAdapter menuListAdapter = new MenuListAdapter(menu);
        ListView listView = view.findViewById(R.id.menuListItem);
        listView.setAdapter(menuListAdapter);

        return view;
    }
}