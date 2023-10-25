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

import java.text.SimpleDateFormat;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private Context context;
    private List<Review> reviews;

    public ReviewAdapter(Context context, List<Review> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review=reviews.get(position);
        if (review==null)
            return ;

        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        holder.txtName.setText(review.getName());
        holder.txtContent.setText(review.getContent());
        holder.txtTime.setText(format.format(review.getTime()));
        holder.img.setImageResource(review.getMainImg());
    }

    @Override
    public int getItemCount() {
        return reviews==null?0:reviews.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder{
        private TextView txtName, txtTime, txtContent;
        private ImageView img;
        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName=itemView.findViewById(R.id.name_review);
            txtTime=itemView.findViewById(R.id.time_review);
            txtContent=itemView.findViewById(R.id.content_review);
            img=itemView.findViewById(R.id.img_review);
        }
    }
}
