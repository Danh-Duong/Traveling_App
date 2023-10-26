package com.example.traveling_app;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import com.example.traveling_app.fragment.FilterFragment;
import com.example.traveling_app.fragment.SearchFragment;
import com.example.traveling_app.fragment.SearchResultFragment;
import com.example.traveling_app.model.FilterItem;
import com.example.traveling_app.model.FilterItemGroup;
import com.example.traveling_app.model.TourInformation;

import android.os.Bundle;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class SearchAndFilterActivity extends AppCompatActivity implements SearchFragment.OnFilterChangeListener {
    // Một mớ hổ lốn đang tồn tại và cần được dọn dẹp sạch sẽ, để dungleanh297 lo phần này.
    private ArrayList<FilterItemGroup> filterGroups = new ArrayList<>();;
    private SearchFragment searchFragment = new SearchFragment();
    private FilterFragment filterFragment = new FilterFragment();
    private String[] recentSearch = {"Mục tìm kiếm thứ nhất", "Mục tìm kiếm thứ hai", "..."};
    private ArrayList<TourInformation> resultList = new ArrayList<>();

    public SearchAndFilterActivity() {
        FilterItemGroup prices = new FilterItemGroup("prices", "Giá cả"),
                purpose = new FilterItemGroup("purpose", "Mục đích chuyến đi"),
                geographical = new FilterItemGroup("geographical", "Đặc điểm địa hình"),
                province = new FilterItemGroup("province", "Tỉnh thành");
        TourInformation tourInformation = new TourInformation(1, "Nghỉ dưỡng 3 ngày 3 đêm", "Đà Nẵng", 1000000, 900000, 123, 34, 4, null);
        prices.add("500-1000", "500K - 1TR");
        prices.add("1000-2000", "1TR - 2TR");
        prices.add("2000-3000", "2TR - 3TR");
        purpose.add("nghiduong", "Nghỉ dưỡng");
        purpose.add("sinhthai", "Sinh thái");
        purpose.add("khampha", "Khám phá");
        geographical.add("bien", "Biển");
        geographical.add("nui", "Núi");
        geographical.add("dangoai", "Dã ngoại");
        province.add("1", "Đà Nẵng");
        province.add("2", "Hà Nội");
        province.add("3", "Thành phố Hồ Chí Minh");
        filterGroups.add(prices);
        filterGroups.add(purpose);
        filterGroups.add(geographical);
        filterGroups.add(province);
        resultList.add(tourInformation);
        resultList.add(tourInformation);
        resultList.add(tourInformation);
        resultList.add(tourInformation);
        resultList.add(tourInformation);
        resultList.add(tourInformation);
        resultList.add(tourInformation);
        resultList.add(tourInformation);
        resultList.add(tourInformation);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ActionBar actionBar = getSupportActionBar();
        FragmentManager fragmentManager = getSupportFragmentManager();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        fragmentManager.beginTransaction().replace(R.id.content, searchFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            getOnBackPressedDispatcher().onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public Stream<FilterItem> getStreamOfSelectedFilterItem() {
        return filterGroups.stream().map(group -> group.getSelectedItem()).filter(item -> item != null);
    }

    @Override
    public Stream<String> getStreamOfRecentSearch() {
        return Arrays.stream(recentSearch);
    }

    @Override
    public void switchToFilterFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.content, filterFragment).addToBackStack(null).commit();
    }

    @Override
    public void switchToSearchResultFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.content, SearchResultFragment.class, null).addToBackStack(null).commit();
    }
    @Override
    public Stream<FilterItemGroup> getStreamOfFilterItemGroups() {
        return filterGroups.stream();
    }


    @Override
    public ArrayList<TourInformation> getSearchResult(String keyword, FilterItem[] filterItems) {
        return this.resultList;
    }

}