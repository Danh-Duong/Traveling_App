package com.example.traveling_app.model;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;

import com.example.traveling_app.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public class Common {

    static {
        ArrayList<MenuSectionItem> menuSectionItems = new ArrayList<>();
        menuSectionItems.add(new MenuSectionItem(R.drawable.baseline_person_outline_24, R.string.profile_page, com.example.traveling_app.ProfileActivity.class));
        menuSectionItems.add(new MenuSectionItem(R.drawable.baseline_bookmark_border_24, R.string.saved, null));
        menuSectionItems.add(new MenuSectionItem(R.drawable.baseline_directions_car_24, R.string.place_went, com.example.traveling_app.luu_lichsutour.class));
        menuSectionItems.add(new MenuSectionItem(R.drawable.baseline_share_24_1, R.string.share_app, com.example.traveling_app.GioiThieuBanBe.class));
        menuSectionItems.add(new MenuSectionItem(R.drawable.baseline_star_border_24, R.string.rate_app, null));
        menuSectionItems.add(new MenuSectionItem(R.drawable.baseline_support_agent_24, R.string.support_center, null));
        menuSectionItems.add(new MenuSectionItem(R.drawable.baseline_settings_24, R.string.settings, null));
        menuSectionItems.add(new MenuSectionItem(R.drawable.baseline_info_24_1, R.string.about_us, com.example.traveling_app.AboutActivity.class));
        menuSectionItems.add(new MenuSectionItem(R.drawable.baseline_logout_24, R.string.log_out, com.example.traveling_app.activity_dangnhap.class));
        MENU_SECTION_ITEMS = Collections.unmodifiableList(menuSectionItems);
    }

    public static final List<MenuSectionItem> MENU_SECTION_ITEMS;

    public static final void getCurrentAddress(Activity activity, Runnable onFailedListener, Consumer<Address> onSuccessListener) {
        if (activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
            fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_LOW_POWER, null).addOnSuccessListener(activity, location -> {
                if (location != null) {
                    Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
                    Address address;
                    try {
                        address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1).get(0);
                        onSuccessListener.accept(address);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    onFailedListener.run();
                }

            });

        } else {
            activity.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
    }
}
