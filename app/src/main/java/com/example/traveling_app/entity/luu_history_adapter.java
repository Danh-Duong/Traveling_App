package com.example.traveling_app.entity;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.traveling_app.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class luu_history_adapter extends FirebaseRecyclerAdapter<luu_history_obj,luu_history_adapter.myviewholder> {
    public luu_history_adapter(@NonNull FirebaseRecyclerOptions<luu_history_obj> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull luu_history_obj model) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("tours");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child(model.getIdtour()).child("name").getValue(String.class);
                holder.name.setText(name);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        holder.price.setText(model.getPrice()+"");
        holder.dateStart.setText(model.getStartDate());
        holder.dateEnd.setText(model.getEndDate());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_luu_lichsu,parent,false);
        return new luu_history_adapter.myviewholder(view);
    }
    public class myviewholder extends RecyclerView.ViewHolder{
        private TextView name, price, dateStart, dateEnd;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            dateStart = itemView.findViewById(R.id.dateStart);
            dateEnd = itemView.findViewById(R.id.dateEnd);
        }
    }
    private void showPopupMenu(View view) {

    }
}

