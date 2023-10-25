package com.example.traveling_app.model;

public class TourInformation {
    private final long id;
    private final String title;
    private final String place;
    private final Integer oldPrice;
    private final int newPrices;
    private final int bookCount;
    private final int rateCount;
    private final int rateStar;
    private final String thumbnailUrl;

    public TourInformation(long id, String title, String place, Integer oldPrice, int newPrices, int bookCount, int rateCount, int rateStar, String thumbnailUrl) {
        this.id = id;
        this.title = title;
        this.place = place;
        this.oldPrice = oldPrice;
        this.newPrices = newPrices;
        this.bookCount = bookCount;
        this.rateCount = rateCount;
        this.rateStar = rateStar;
        this.thumbnailUrl = thumbnailUrl;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPlace() {
        return place;
    }

    public Integer getOldPrice() {
        return oldPrice;
    }

    public int getNewPrices() {
        return newPrices;
    }

    public int getBookCount() {
        return bookCount;
    }

    public int getRateCount() {
        return rateCount;
    }

    public int getRateStar() {
        return rateStar;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
