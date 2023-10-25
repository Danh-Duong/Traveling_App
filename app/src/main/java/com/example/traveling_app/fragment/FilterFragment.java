package com.example.traveling_app.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.traveling_app.R;
import com.example.traveling_app.model.FilterItem;
import com.example.traveling_app.model.FilterItemGroup;
import com.google.android.flexbox.FlexboxLayout;

public class FilterFragment extends Fragment {

    SearchFragment.OnFilterChangeListener listener;

    @Override
    public void onAttach(Context context) {
        if (context instanceof SearchFragment.OnFilterChangeListener)
            this.listener = (SearchFragment.OnFilterChangeListener)context;
        else
            throw new RuntimeException(context.getClass().getName() + " must implement " + SearchFragment.OnFilterChangeListener.class.getName());
        super.onAttach(context);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_filter, container, false);
        listener.getStreamOfFilterItemGroups().forEach( filterItemGroup -> {
            LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.filter_selector_layout, rootView, false);
            FlexboxLayout filterItemContainer = linearLayout.findViewById(R.id.filterItemContainer);
            TextView title = linearLayout.findViewById(R.id.title);
            title.setText(filterItemGroup.getTitle());
            initFilterItemGroupContainer(filterItemGroup, filterItemContainer);
            rootView.addView(linearLayout);
        });
        return rootView;
    }

    @Override
    public void onResume() {
        updateSelectedFilterCountToActionBar();
        super.onResume();
    }

    @Override
    public void onDetach() {
        listener.getStreamOfFilterItemGroups().forEach(filterItemGroup ->  {
            filterItemGroup.setOnItemUnselected(null);
            filterItemGroup.setOnItemUnselected(null);
        });
        listener = null;
        super.onDetach();

    }

    private void initFilterItemGroupContainer(@NonNull FilterItemGroup filterItemGroup, ViewGroup viewGroup) {
        LayoutInflater inflater = getLayoutInflater();
        for (int i = 0; i < filterItemGroup.size(); ++i) {
            FilterItem filterItem = filterItemGroup.get(i);
            TextView textView = (TextView) inflater.inflate(R.layout.small_clickable_textview, viewGroup, false);
            textView.setText(filterItem.getName());
            textView.setTag(filterItem);
            changeFilterItemViewState(textView, filterItem.isSelected());
            textView.setOnClickListener(v -> {
                boolean selected = filterItem.isSelected();
                if (selected)
                    filterItem.unselectSelf();
                else
                    filterItem.selectSelf();
            });
            viewGroup.addView(textView);
        }
        filterItemGroup.setOnSelectedItemChanged(index -> {
            changeFilterItemViewState((TextView)viewGroup.getChildAt(index), true);
            updateSelectedFilterCountToActionBar();
        });
        filterItemGroup.setOnItemUnselected(index -> {
            changeFilterItemViewState((TextView)viewGroup.getChildAt(index), false);
            updateSelectedFilterCountToActionBar();
        });
    }

    private void changeFilterItemViewState(TextView textView, boolean state) {
        textView.setTextAppearance(state ? R.style.SmallClickableTextView_PrimaryColor : R.style.SmallClickableTextView);
        textView.setBackground(getContext().getDrawable(state ? R.drawable.small_clickable_textview_primary_color_background : R.drawable.small_clickable_textview_background));
    }

    public void updateSelectedFilterCountToActionBar() {
        if (getActivity() instanceof AppCompatActivity && ((AppCompatActivity)getActivity()).getSupportActionBar() != null) {
            ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
            actionBar.setTitle(getString(R.string.search_result));
            int selectedFilterCount = (int)listener.getStreamOfSelectedFilterItem().count();
            actionBar.setSubtitle(selectedFilterCount == 0 ? "" : getString(R.string.item_count, selectedFilterCount));
        }
    }

}