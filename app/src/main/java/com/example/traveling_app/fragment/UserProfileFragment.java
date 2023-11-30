package com.example.traveling_app.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.traveling_app.R;
import com.example.traveling_app.common.DatabaseReferences;
import com.example.traveling_app.common.StorageReferences;
import com.example.traveling_app.model.ProfileViewPageAdapter;
import com.example.traveling_app.model.user.User;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;

public class UserProfileFragment extends Fragment {
    private ProfileViewPageAdapter profileViewPageAdapter;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    @SuppressWarnings("deprecation")
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        return view;
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstance) {
        profileViewPageAdapter = new ProfileViewPageAdapter(this);
        viewPager.setAdapter(profileViewPageAdapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
           tab.setText(profileViewPageAdapter.getTabName(position));
        }).attach();
    }
}