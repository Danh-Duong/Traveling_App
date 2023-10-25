package com.example.traveling_app.entity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.traveling_app.R;

import java.util.List;

public class AdminTourAdapter extends BaseAdapter {
    private List<Tour> tours;
    private Activity activity;

    public AdminTourAdapter(List<Tour> tours, Activity activity) {
        this.tours = tours;
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return tours==null?0:tours.size();
    }

    @Override
    public Object getItem(int position) {
        return tours.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=activity.getLayoutInflater();
        convertView =inflater.inflate(R.layout.admin_tour_item,null);

        //Bind sữ liệu phần tử vào View
        Tour product = (Tour) getItem(position);
        ((TextView) convertView.findViewById(R.id.title_tour_admin)).setText(tours.get(position).getName());
        ((TextView) convertView.findViewById(R.id.address_tour_admin)).setText(tours.get(position).getAddress());
        ((TextView) convertView.findViewById(R.id.time_tour_admin)).setText("Thời gian: 20/11/2023 - 22/11/2023");
        ((ImageView) convertView.findViewById(R.id.avatar_tour_admin)).setImageResource(((Tour) getItem(position)).getMainImage());
        ImageView menu_tour=convertView.findViewById(R.id.menu_tour_admin);

        PopupMenu popupMenu=new PopupMenu(activity, menu_tour);
        popupMenu.inflate(R.menu.menu_tour_admin);
        menu_tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });

        return convertView;
    }
}
