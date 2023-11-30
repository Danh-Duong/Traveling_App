package com.example.traveling_app.model;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.traveling_app.R;
import com.example.traveling_app.common.DatabaseReferences;
import com.example.traveling_app.entity.Tour;
import com.example.traveling_app.model.filter.FilterItemGroup;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class TourSearchResultAdapter extends RecyclerView.Adapter<TourSearchResultAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Tour> tourList = new ArrayList<>();
    private String keyword;
    private FilterItemGroup[] filters;

    private Query query = DatabaseReferences.TOURS_DATABASE_REF.orderByKey();

    private ChildEventListener listener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            if (snapshot.child("name").getValue(String.class).toLowerCase().contains(keyword) && Arrays.stream(filters).allMatch(f -> f.isSatisfied(snapshot.child(f.getKey()).getValue()))) {
                Tour tour = snapshot.getValue(Tour.class);
                tourList.add(tour);
                Log.d("image", tour.getMainImageUrl());
                notifyDataSetChanged();
            }
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot snapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };



    public TourSearchResultAdapter(Context context, String keyword, Stream<FilterItemGroup> filterGroups) {
        this.context = context;
        this.keyword = keyword;
        this.filters = filterGroups.toArray(FilterItemGroup[]::new);
        query.addChildEventListener(listener);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(context);
        ViewGroup tourInfoContainer = (ViewGroup) inflate.inflate(R.layout.tour_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(tourInfoContainer);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tour tour = tourList.get(position);
        holder.title.setText(tour.getName());
        holder.place.setText(tour.getAddress());
        holder.oldPrice.setText(Html.fromHtml("<strike>" + tour.getPrice() + " VND</strike>", HtmlCompat.FROM_HTML_MODE_LEGACY));
        holder.newPrice.setText(tour.getSalePrice() + " VND");
        holder.bookCount.setText(context.getString(R.string.book_count, tour.getNumBooking()));
        holder.rateCount.setText(context.getString(R.string.rate_count, tour.getNumComment()));
        Glide.with(context).load(tour.getMainImageUrl()).into(holder.thumbnail);
    }

    public void stopListening() {
        query.removeEventListener(listener);
    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title, place, oldPrice, newPrice, bookCount, rateCount;
        private ImageView thumbnail, star;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            place = itemView.findViewById(R.id.place);
            oldPrice = itemView.findViewById(R.id.oldPrice);
            newPrice = itemView.findViewById(R.id.newPrice);
            bookCount = itemView.findViewById(R.id.bookCount);
            rateCount = itemView.findViewById(R.id.rateCount);
            star = itemView.findViewById(R.id.star);
            thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }
}
