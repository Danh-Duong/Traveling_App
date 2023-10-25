package com.example.traveling_app.entity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.traveling_app.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.text.DecimalFormat;
import java.util.List;
public class BannerTourAdapter extends SliderViewAdapter<BannerTourAdapter.BannerTourViewHolder>{

    private List<Tour> tours;
    private Context context;

    public BannerTourAdapter(Context context,List<Tour> tours) {
        this.tours = tours;
        this.context = context;
        notifyDataSetChanged();
    }

    @Override
    public BannerTourViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_tour_item, null);
        return new BannerTourViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(BannerTourViewHolder viewHolder, int position) {
        Tour tour = tours.get(position);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        viewHolder.txtContent.setText(tour.getContent().substring(0,32) +"...");
        viewHolder.txtTitle.setText(tour.getName());
        viewHolder.txtPrice.setText(formatter.format(tour.getPrice())+" đ");
        viewHolder.img.setImageResource(tour.getMainImage());
    }

    @Override
    public int getCount() {
        return tours==null?0:tours.size();
    }

    public static class BannerTourViewHolder extends SliderViewAdapter.ViewHolder {
        ImageView img;
        TextView txtTitle, txtContent, txtPrice;
        public BannerTourViewHolder(View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.title_banner);
            txtContent=itemView.findViewById(R.id.content_banner);
            txtPrice=itemView.findViewById(R.id.price_banner);
            img=itemView.findViewById(R.id.img_banner);
        }
    }

}
