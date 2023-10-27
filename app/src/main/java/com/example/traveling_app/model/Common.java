package com.example.traveling_app.model;

import android.view.Menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.example.traveling_app.R;
public class Common {

    static {
        ArrayList<MenuSectionItem> menuSectionItems = new ArrayList<>();
        menuSectionItems.add(new MenuSectionItem(R.drawable.baseline_person_outline_24, R.string.profile_page, com.example.traveling_app.ProfileActivity.class));
        menuSectionItems.add(new MenuSectionItem(R.drawable.baseline_bookmark_border_24, R.string.saved, com.example.traveling_app.luu_lichsutour.class));
        menuSectionItems.add(new MenuSectionItem(R.drawable.baseline_directions_car_24, R.string.place_went, null));
        menuSectionItems.add(new MenuSectionItem(R.drawable.baseline_share_24_1, R.string.share_app, com.example.traveling_app.GioiThieuBanBe.class));
        menuSectionItems.add(new MenuSectionItem(R.drawable.baseline_star_border_24, R.string.rate_app, null));
        menuSectionItems.add(new MenuSectionItem(R.drawable.baseline_support_agent_24, R.string.support_center, null));
        menuSectionItems.add(new MenuSectionItem(R.drawable.baseline_settings_24, R.string.settings, null));
        menuSectionItems.add(new MenuSectionItem(R.drawable.baseline_info_24, R.string.about_us, com.example.traveling_app.AboutActivity.class));
        menuSectionItems.add(new MenuSectionItem(R.drawable.baseline_logout_24, R.string.log_out, com.example.traveling_app.activity_dangnhap.class));
        MENU_SECTION_ITEMS = Collections.unmodifiableList(menuSectionItems);
    }
    public static final List<MenuSectionItem> MENU_SECTION_ITEMS;
}
