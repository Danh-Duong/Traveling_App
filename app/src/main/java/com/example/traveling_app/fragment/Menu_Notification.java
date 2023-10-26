package com.example.traveling_app.fragment;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.traveling_app.MainActivity;
import com.example.traveling_app.R;
import com.example.traveling_app.entity.Notification;
import com.example.traveling_app.entity.NotificationAdapter;

import java.util.ArrayList;
import java.util.List;

public class Menu_Notification extends Fragment {

    private RecyclerView noti_rcv;
    private ImageButton btnHome;
    private List<Notification> newList=new ArrayList<>(), recentList=new ArrayList<>();

    private MainActivity mainActivity;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainActivity= (MainActivity) getActivity();
        view= inflater.inflate(R.layout.fragment_menu__notification, container, false);
        noti_rcv=view.findViewById(R.id.noti_rcv);

        recentList.add(new Notification(1,R.drawable.main_avatar,"Nguyễn Văn A đã thích bài viết của bạn","2 giờ trước", true));
        recentList.add(new Notification(2,R.drawable.main_avatar,"Nguyễn Văn B đã thích bài viết của bạn","3 giờ trước", false));
        recentList.add(new Notification(3,R.drawable.main_avatar,"Nguyễn Văn C đã thích bài viết của bạn","4 giờ trước",false));
        recentList.add(new Notification(4,R.drawable.main_avatar,"Nguyễn Văn A đã thích bài viết của bạn","2 giờ trước",false));

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mainActivity);
        noti_rcv.setLayoutManager(linearLayoutManager);
        NotificationAdapter recentAdapter=new NotificationAdapter(recentList);
        noti_rcv.setAdapter(recentAdapter);

        RecyclerView.ItemDecoration itemDecoration=new DividerItemDecoration(mainActivity, DividerItemDecoration.VERTICAL);
        noti_rcv.addItemDecoration(itemDecoration);
        return view;
    }
}