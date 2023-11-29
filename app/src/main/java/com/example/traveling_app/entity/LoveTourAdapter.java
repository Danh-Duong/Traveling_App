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

public class LoveTourAdapter extends RecyclerView.Adapter<LoveTourAdapter.LoveTourAdapterViewHolder>{
    private List<Tour> tours;
    private Context context;

    public LoveTourAdapter( Context context,List<Tour> tours) {
        this.tours = tours;
        this.context = context;
    }

    @NonNull
    @Override
    public LoveTourAdapter.LoveTourAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.love_tour_item,parent,false);
        return new LoveTourAdapter.LoveTourAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoveTourAdapter.LoveTourAdapterViewHolder holder, int position) {
        Tour tour=tours.get(position);
        if (tour==null)
            return;

        holder.txtTitle.setText(tour.getName());
        holder.txtAddress.setText(tour.getAddress());
        holder.txtComment.setText(tour.getNumComment()+"");
        holder.txtBook.setText(tour.getNumBooking()+"k đã đặt");
        holder.txtComment.setText("(" +tour.getNumComment()+" đánh giá)");
    }

    @Override
    public int getItemCount() {
        return tours==null?0:tours.size();
    }

    public static class LoveTourAdapterViewHolder extends RecyclerView.ViewHolder{

        private TextView txtTitle, txtAddress,txtBook,txtComment,  txtRate;
        private ImageView img;
        public LoveTourAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.title_love_tour);
            txtAddress=itemView.findViewById(R.id.address_love_tour);
            txtBook=itemView.findViewById(R.id.book_love_tour);
            txtComment=itemView.findViewById(R.id.comment_love_tour);
            txtRate=itemView.findViewById(R.id.rate_love_tour);
            img=itemView.findViewById(R.id.img_love_tour);
        }
    }
}