package com.example.traveling_app.model.savedtours;

import androidx.annotation.NonNull;

import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;

public class SavedTourSnapshotParser {
    public static Parser INSTANCE = new Parser();
    private static class Parser implements SnapshotParser<SavedTour> {

        @NonNull
        @Override
        public SavedTour parseSnapshot(@NonNull DataSnapshot snapshot) {
            SavedTour savedTour = new SavedTour(snapshot.getKey());
            savedTour.setUsername(snapshot.getRef().getParent().getKey());
            return savedTour;
        }
    }
}
