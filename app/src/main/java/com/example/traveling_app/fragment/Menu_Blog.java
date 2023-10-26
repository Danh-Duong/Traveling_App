package com.example.traveling_app.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.traveling_app.Blog_activity;
import com.example.traveling_app.CreateBlog_activity;
import com.example.traveling_app.MainActivity;
import com.example.traveling_app.R;
import com.example.traveling_app.Status_activity;


public class Menu_Blog extends Fragment {

    private ImageView back, create;

    private View view;
    private MainActivity mainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_menu__blog, container, false);

        mainActivity= (MainActivity) getActivity();
        back=view.findViewById(R.id.back_blog_main);
        create=view.findViewById(R.id.create_blog_main);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainActivity, CreateBlog_activity.class));
            }
        });
        view.findViewById(R.id.Baiviet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mainActivity, Status_activity.class));
            }
        });

        return view;
    }
}