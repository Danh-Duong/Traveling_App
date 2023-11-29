package com.example.traveling_app.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.traveling_app.R;
import com.example.traveling_app.SearchAndFilterActivity;
import com.example.traveling_app.model.TourInformationAdapter;

public class SearchResultFragment extends Fragment {

    private SearchAndFilterActivity listener;

    @Override
    public void onAttach(Context context) {
        if (context instanceof SearchAndFilterActivity)
            this.listener = (SearchAndFilterActivity)context;
        else
            throw new RuntimeException(context.getClass().getName() + " must implement " + SearchAndFilterActivity.class.getName());
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_search_result, container, false);
        RecyclerView recyclerView = viewGroup.findViewById(R.id.resultContainer);
        TourInformationAdapter adapter = new TourInformationAdapter(getContext(), listener.getSearchResult(null, null));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (getActivity() instanceof AppCompatActivity && ((AppCompatActivity)getActivity()).getSupportActionBar() != null) {
            ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
            actionBar.setTitle(getString(R.string.filter));
            int selectedFilterCount = (int)listener.getStreamOfSelectedFilterItem().count();
            actionBar.setSubtitle(selectedFilterCount == 0 ? "" : getString(R.string.item_count, selectedFilterCount));
        }
        return viewGroup;
    }
}