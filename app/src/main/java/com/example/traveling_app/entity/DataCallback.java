package com.example.traveling_app.entity;

import java.util.List;

public interface DataCallback {
    void onDataLoaded(List<Tour> tours);

    void onTourLoaded(Tour Tour);

}
