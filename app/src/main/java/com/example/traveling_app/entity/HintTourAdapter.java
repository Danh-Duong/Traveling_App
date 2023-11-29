package com.example.traveling_app.entity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.traveling_app.DetailActivity;
import com.example.traveling_app.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HintTourAdapter extends RecyclerView.Adapter<HintTourAdapter.TourHintViewHolder>{

    private Bitmap[] images;
    private boolean isLoadedBitmaps[];
    private List<Tour> tours;
    private Context context;
    public HintTourAdapter(Context context, List<Tour> tours) {
        this.tours = tours;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TourHintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hint_tour_item,parent,false);
        return new TourHintViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TourHintViewHolder holder, int position) {
        Tour tour=tours.get(position);
        if (tour==null)
            return;
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        DecimalFormat rateFormatter = new DecimalFormat("#.##");
        holder.txtName.setText(tour.getName());
        holder.txtRate.setText(rateFormatter.format(tour.getNumStar()).replace(',','.'));
        holder.txtPrice.setText(formatter.format(tour.getPrice())+" đ");
        ImageLoader.loadImage(tours.get(position).getMainImageUrl(), holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("id", tour.getId());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return tours==null?0:tours.size();
    }

    public static class TourHintViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName, txtRate, txtPrice;
        private ImageView img;
        public TourHintViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.title_hint);
            txtRate=itemView.findViewById(R.id.rate_hint);
            txtPrice=itemView.findViewById(R.id.price_hint);
            img=itemView.findViewById(R.id.image_hint);
        }
    }
}
