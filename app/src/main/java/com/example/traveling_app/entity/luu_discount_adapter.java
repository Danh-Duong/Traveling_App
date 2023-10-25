package com.example.traveling_app.entity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.traveling_app.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class luu_discount_adapter extends RecyclerView.Adapter<luu_discount_adapter.discountViewHolder>{

    private Context mContext;
    private List<luu_discount_obj> mListDiscount;

    public luu_discount_adapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<luu_discount_obj> list) {
        this.mListDiscount = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public discountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_luu_magiamgia,parent,false);
        return new discountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull discountViewHolder holder, int position) {
        luu_discount_obj discount = mListDiscount.get(position);
        if (discount==null) {
            return;
        }
        holder.discount_img.setImageResource(discount.getResourceId());
        holder.text1.setText(discount.getText1());
        holder.text2.setText(discount.getText2());
        holder.text3.setText(discount.getText3());
    }

    @Override
    public int getItemCount() {
        if (mListDiscount!=null) {
            return mListDiscount.size();
        }
        return 0;
    }

    public class discountViewHolder extends RecyclerView.ViewHolder{

        private ImageView discount_img;
        private TextView text1,text2,text3;
        public discountViewHolder(@NonNull View itemView) {
            super(itemView);
            discount_img = itemView.findViewById(R.id.discount_img);
            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
            text3 = itemView.findViewById(R.id.text3);

        }
    }
}
