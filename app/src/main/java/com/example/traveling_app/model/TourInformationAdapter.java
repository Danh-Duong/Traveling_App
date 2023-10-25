package com.example.traveling_app.model;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.traveling_app.R;
import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Optional;

public class TourInformationAdapter extends RecyclerView.Adapter<TourInformationAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TourInformation> tourList;

    public TourInformationAdapter(Context context, ArrayList<TourInformation> tourList) {
        this.context = context;
        this.tourList = tourList;
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
        TourInformation tour = tourList.get(position);
        holder.title.setText(tour.getTitle());
        holder.place.setText(tour.getPlace());
        if (tour.getOldPrice() != null)
            holder.oldPrice.setText(Html.fromHtml("<strike>" + tour.getOldPrice() + " VND</strike>", HtmlCompat.FROM_HTML_MODE_LEGACY));
        else
            holder.oldPrice.setText("");
        holder.newPrice.setText(tour.getNewPrices() + " VND");
        holder.bookCount.setText(context.getString(R.string.book_count, tour.getBookCount()));
        holder.rateCount.setText(context.getString(R.string.rate_count, tour.getRateCount()));
    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
        }

    }
}
