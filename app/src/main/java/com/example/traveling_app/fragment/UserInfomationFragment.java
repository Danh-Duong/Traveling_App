package com.example.traveling_app.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.traveling_app.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class UserInfomationFragment extends Fragment {

    public static final String ARG_PARAM1 = "profileId";
    private String profileId;

    public static UserInfomationFragment newInstance(String profileId) {
        UserInfomationFragment fragment = new UserInfomationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, profileId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            profileId = getArguments().getString(ARG_PARAM1);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_infomation, container, false);
    }

    public String getTabName() {
        return getString(R.string.post);
    }
}