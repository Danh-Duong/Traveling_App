package com.example.traveling_app.model.savedtours;
import androidx.annotation.NonNull;
import com.example.traveling_app.common.DatabaseReferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.ValueEventListener;

import java.util.function.Consumer;

public class SavedTour {

    @Exclude
    private Tour tour;
    @Exclude
    private String username;

    private String tourId;
    private Consumer<Tour> onTourReady;

    @SuppressWarnings("unused")
    public SavedTour() {

    }

    public SavedTour(String tourId) {
        this.tourId = tourId;
        DatabaseReferences.TOURS_DATABASE_REF.child(tourId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    tour = snapshot.getValue(Tour.class);
                    tour.setIdTour(snapshot.getKey());
                    if (onTourReady != null)
                        onTourReady.accept(tour);
                }
                else
                    DatabaseReferences.USER_SAVED_TOURS_DATABASE_REF.child(getUsername()).child(snapshot.getKey()).setValue(null);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Exclude
    @SuppressWarnings("unused")
    public String getTourId() {
        return tourId;
    }

    @Exclude
    public Tour getTour() {
        return tour;
    }
    public void setOnTourInformationReady(Consumer<Tour> onTourReady) {
        this.onTourReady = onTourReady;
        if (tour != null && onTourReady != null)
            onTourReady.accept(tour);
    }
}

@SuppressWarnings("unused")
class Tour {
    @Exclude
    private String idTour;
    private String name;
    private int numBooking;
    private int numComment;
    private int numStar;
    private String address;
    private String mainImageUrl;

    public Tour() {

    }

    public Tour(String name, int numBooking, int numComment, int numStar, String address, String mainImageUrl) {
        this.name = name;
        this.numBooking = numBooking;
        this.numComment = numComment;
        this.numStar = numStar;
        this.address = address;
        this.mainImageUrl = mainImageUrl;
    }

    @Exclude
    public String getIdTour() {
        return idTour;
    }

    public void setIdTour(String idTour) {
        this.idTour = idTour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumBooking() {
        return numBooking;
    }


    public int getNumComment() {
        return numComment;
    }


    public int getNumStar() {
        return numStar;
    }


    public String getAddress() {
        return address;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }
}
