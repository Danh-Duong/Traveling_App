package com.example.traveling_app.model;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.traveling_app.R;
import com.example.traveling_app.fragment.UserPostFragment;
import com.example.traveling_app.fragment.UserInfomationFragment;

public class ProfileViewPageAdapter extends FragmentStateAdapter {

    private final String profileId;

    public ProfileViewPageAdapter(@NonNull Fragment fragment) {
        super(fragment);
        this.profileId = null;
    }

    public ProfileViewPageAdapter(@NonNull Fragment fragment, String profileId) {
        super(fragment);
        this.profileId = profileId;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return UserPostFragment.newInstance(profileId);
            case 1:
                return UserInfomationFragment.newInstance(profileId);
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public int getTabName(int position) {
        switch (position) {
            case 0:
                return R.string.post;
            case 1:
                return R.string.user_infomation;
            default:
                return -1;
        }
    }
}
