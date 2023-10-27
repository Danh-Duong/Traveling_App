package com.example.traveling_app.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.traveling_app.MainActivity;
import com.example.traveling_app.MenuActivity;
import com.example.traveling_app.R;
import com.example.traveling_app.model.Common;
import com.example.traveling_app.model.MenuListAdapter;
import com.example.traveling_app.model.MenuSectionItem;

public class Menu_Menu extends Fragment {

    MainActivity mainActivity;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainActivity = (MainActivity) getActivity();
        view = inflater.inflate(R.layout.fragment_menu__menu, container, false);
        MenuListAdapter menuListAdapter = new MenuListAdapter(Common.MENU_SECTION_ITEMS);
        ListView listView = view.findViewById(R.id.menuListItem);
        listView.setAdapter(menuListAdapter);
        listView.setOnItemClickListener(
                (AdapterView<?> parent, View view, int position, long id) -> {
                    MenuSectionItem menuSectionItem = (MenuSectionItem) parent.getItemAtPosition(position);
                    if (menuSectionItem.getActivityClass() == null)
                        Toast.makeText(getContext(), getString(R.string.not_implemented), Toast.LENGTH_SHORT).show();
                    else
                        startActivity(new Intent(getContext(), menuSectionItem.getActivityClass()));
                }
        );
        return view;
    }
}