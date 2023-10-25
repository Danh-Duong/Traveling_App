package com.example.traveling_app.entity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.traveling_app.R;

import java.util.List;

public class luu_history_adapter extends RecyclerView.Adapter<luu_history_adapter.historyViewHolder> {

    private Context mContext;
    private List<luu_history_obj> mListhistory;

    public luu_history_adapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<luu_history_obj> list) {
        this.mListhistory = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public historyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_luu_lichsu,parent,false);
        return new historyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull historyViewHolder holder, int position) {
        luu_history_obj historyObj = mListhistory.get(position);
        if (historyObj==null)
            return;
        holder.name.setText(historyObj.getName());
        holder.price.setText(historyObj.getPrice());
        holder.date.setText(historyObj.getDate());
        holder.state.setText(historyObj.getState());

    }

    @Override
    public int getItemCount() {
        if (mListhistory!=null)
            return mListhistory.size();
        return 0;
    }

    public class historyViewHolder extends RecyclerView.ViewHolder{

        private TextView name, price, date, state;
        public historyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name_histour);
            price = itemView.findViewById(R.id.tv_price_histour);
            date = itemView.findViewById(R.id.tv_date_histour);
            state = itemView.findViewById(R.id.tv_state_histour);
        }
    }
}

