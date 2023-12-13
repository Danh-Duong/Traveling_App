package com.example.traveling_app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.traveling_app.R;
import com.example.traveling_app.common.Constants;
import com.example.traveling_app.model.notification.Notification;
import com.example.traveling_app.model.notification.NotificationAdapter;
import com.example.traveling_app.model.notification.NotificationParser;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Menu_Notification extends Fragment {

    private RecyclerView noti_rcv;
    private NotificationAdapter recentAdapter;
    private View view;
    @Override
    @SuppressWarnings("deprecation")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Query query = FirebaseDatabase.getInstance().getReference("/notifications").orderByChild("sendTo").equalTo(PreferenceManager.getDefaultSharedPreferences(getContext()).getString("username", Constants.DEFAULT_USERNAME));
        FirebaseRecyclerOptions<Notification> options = new FirebaseRecyclerOptions.Builder<Notification>().setQuery(query, new NotificationParser()).build();
        view = inflater.inflate(R.layout.fragment_menu__notification, container, false);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        noti_rcv = view.findViewById(R.id.noti_rcv);
        recentAdapter = new NotificationAdapter(options, getContext());
        noti_rcv.addItemDecoration(itemDecoration);
        noti_rcv.setAdapter(recentAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recentAdapter.startListening();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recentAdapter.stopListening();
    }
}