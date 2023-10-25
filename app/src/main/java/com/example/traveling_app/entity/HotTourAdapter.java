package com.example.traveling_app.entity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveling_app.R;

import java.text.DecimalFormat;
import java.util.List;

public class HotTourAdapter extends RecyclerView.Adapter<HotTourAdapter.HotTourViewHolder>{
    private List<Tour> tours;
    private Context context;

    public HotTourAdapter( Context context,List<Tour> tours) {
        this.tours = tours;
        this.context = context;
    }

    @NonNull
    @Override
    public HotTourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hot_tour_item,parent,false);
        return new HotTourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotTourViewHolder holder, int position) {
        Tour tour=tours.get(position);
        if (tour==null)
            return;

        DecimalFormat formatter = new DecimalFormat("###,###,###");
        holder.txtTitle.setText(tour.getName());
        holder.txtRate.setText(tour.getNumStar()+"");
        holder.txtReview.setText("(" +tour.getNumComment()+" đánh giá)");
        holder.txtPrice.setText(formatter.format(tour.getPrice())+" đ");
        holder.txtSale.setText(formatter.format(tour.getSalePrice())+" đ");
        holder.txtPercent.setText("("+(tour.getSalePrice()/tour.getPrice())*100+"%)");
        holder.img.setImageResource(tour.getMainImage());
    }

    @Override
    public int getItemCount() {
        return tours==null?0:tours.size();
    }

    public static class HotTourViewHolder extends RecyclerView.ViewHolder{

        private TextView txtTitle, txtPercent, txtRate, txtReview,txtPrice,txtSale;
        private ImageView img;
        public HotTourViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.title_hot);
            txtRate=itemView.findViewById(R.id.rate_hot);
            txtReview=itemView.findViewById(R.id.review_hot);
            txtPercent=itemView.findViewById(R.id.percent_hot);
            txtPrice=itemView.findViewById(R.id.price_hot);
            txtSale=itemView.findViewById(R.id.sale_hot);
            img=itemView.findViewById(R.id.image_hot);
        }
    }
}
