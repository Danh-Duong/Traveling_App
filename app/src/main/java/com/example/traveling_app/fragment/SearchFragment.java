package com.example.traveling_app.fragment;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.traveling_app.model.FilterItemGroup;
import com.example.traveling_app.model.TourInformation;
import com.google.android.flexbox.FlexboxLayout;
import com.example.traveling_app.R;
import com.example.traveling_app.model.FilterItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public class SearchFragment extends Fragment {

    public interface OnFilterChangeListener {
        Stream<FilterItem> getStreamOfSelectedFilterItem();
        Stream<String> getStreamOfRecentSearch();
        Stream<FilterItemGroup> getStreamOfFilterItemGroups();
        ArrayList<TourInformation> getSearchResult(String keyword, FilterItem[] filterItems);
        void switchToFilterFragment();
        void switchToSearchResultFragment();
    }
    private OnFilterChangeListener listener;
    private FlexboxLayout selectedFilterItemContainer;
    private RelativeLayout selectedFilterItemHeader;
    private LinearLayout recentSearchItemsContainer;
    private String[] recentSearch;

    private EditText searchBoxEditText;


    @Override
    public void onAttach(Context context) {
        if (context instanceof OnFilterChangeListener)
            this.listener = (OnFilterChangeListener)context;
        else
            throw new RuntimeException(context.getClass().getName() + " must implement " + OnFilterChangeListener.class.getName());

        super.onAttach(context);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("SearchFragment", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ImageButton imageButton = view.findViewById(R.id.filterButton);
        imageButton.setOnClickListener(v -> listener.switchToFilterFragment());
        selectedFilterItemContainer = view.findViewById(R.id.selectedFilterItems);
        selectedFilterItemHeader = view.findViewById(R.id.selectedFilterItemsHeader);
        recentSearchItemsContainer = view.findViewById(R.id.recentSearchItemsContainer);
        searchBoxEditText = view.findViewById(R.id.searchBoxEditText);
        searchBoxEditText.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == 66) {
                listener.switchToSearchResultFragment();
                return true;
            }
            return false;
        });
        listener.getStreamOfSelectedFilterItem().forEach(filterItem -> selectedFilterItemContainer.addView(inflateTextViewFromFilterItem(filterItem, selectedFilterItemContainer, selectedFilterItemHeader)));
        listener.getStreamOfRecentSearch().forEach(str -> {
            ViewGroup recentSearchViewItem = (ViewGroup) inflater.inflate(R.layout.list_view_item_with_label, recentSearchItemsContainer, false);
            TextView label = recentSearchViewItem.findViewById(R.id.label);
            ImageView icon = recentSearchViewItem.findViewById(R.id.icon);
            label.setText(str);
            icon.setImageDrawable(getActivity().getDrawable(R.drawable.baseline_history_24));
            recentSearchItemsContainer.addView(recentSearchViewItem);
        });
        autoHideSectionIfEmpty(selectedFilterItemHeader, selectedFilterItemContainer);
        selectedFilterItemContainer.getChildCount();
        return view;
    }


    @Override
    public void onResume() {
        if (listener instanceof AppCompatActivity)
        if (((AppCompatActivity)listener).getSupportActionBar() != null) {
            ActionBar actionBar = ((AppCompatActivity)listener).getSupportActionBar();
            actionBar.setTitle(getString(R.string.search));
            actionBar.setSubtitle(null);
        }
        super.onResume();
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    private void autoHideSectionIfEmpty(View titleSection, ViewGroup contentSection) {
        int mode = contentSection.getChildCount() == 0 ? View.GONE : View.VISIBLE;
        contentSection.setVisibility(mode);
        titleSection.setVisibility(mode);
    }

    private TextView inflateTextViewFromFilterItem(FilterItem filterItem, ViewGroup container, View titleSection) {
        LayoutInflater inflater = getLayoutInflater();
        TextView filterItemTextView = (TextView) inflater.inflate(R.layout.small_clickable_textview_primary_color, container, false);
        filterItemTextView.setText(filterItem.getName());
        filterItemTextView.setTag(filterItem);
        filterItemTextView.setOnClickListener(v -> {
            ((FilterItem)v.getTag()).unselectSelf();
            container.removeView(v);
            autoHideSectionIfEmpty(titleSection, container);
        });
        return filterItemTextView;
    }


}