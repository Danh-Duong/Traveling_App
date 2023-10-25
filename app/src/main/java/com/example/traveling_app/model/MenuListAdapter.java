package com.example.traveling_app.model;

import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.traveling_app.R;
import androidx.annotation.NonNull;

public class MenuListAdapter extends BaseAdapter {

    private Menu menu;

    public MenuListAdapter(@NonNull Menu menu) {
        this.menu = menu;
    }
    @Override
    public int getCount() {
        return menu.size();
    }

    @Override
    public Object getItem(int position) {
        return menu.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return menu.getItem(position).getItemId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = View.inflate(parent.getContext(), R.layout.list_view_item_with_label, null);
        ((ImageView) convertView.findViewById(R.id.icon)).setImageDrawable(menu.getItem(position).getIcon());
        ((TextView) convertView.findViewById(R.id.label)).setText(menu.getItem(position).getTitle());
        return convertView;
    }
}
