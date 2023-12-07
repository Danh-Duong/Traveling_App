package com.example.traveling_app.entity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.traveling_app.R;

import java.util.List;

public class VoucherTourAdapter extends RecyclerView.Adapter<VoucherTourAdapter.VoucherTourViewHolder>{
    private List<Voucher> vouchers;
    private Context context;

    public VoucherTourAdapter(Context context, List<Voucher> vouchers) {
        this.vouchers = vouchers;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VoucherTourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.voucher_tour_item,parent,false);
        return new VoucherTourViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherTourViewHolder holder, int position) {
        Voucher voucher=vouchers.get(position);
        if (voucher==null)
            return;
        Glide.with(context).load(voucher.getpUrl()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return vouchers==null?0:vouchers.size();
    }

    public static class VoucherTourViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        public VoucherTourViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img_voucher);
        }
    }
}
