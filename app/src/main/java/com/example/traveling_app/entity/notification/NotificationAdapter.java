package com.example.traveling_app.entity.notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.traveling_app.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class NotificationAdapter extends FirebaseRecyclerAdapter<Notification, NotificationViewHolder> {

    private final DateFormat dateFormat;
    private final RequestManager imageLoader;

    public NotificationAdapter(@NonNull FirebaseRecyclerOptions<Notification> options, Context context) {
        super(options);
        dateFormat = android.text.format.DateFormat.getLongDateFormat(context);
        imageLoader = Glide.with(context);
    }

    @Override
    protected void onBindViewHolder(@NonNull NotificationViewHolder holder, int position, @NonNull Notification model) {
        holder.bindToView(model, dateFormat);
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return new NotificationViewHolder(view, imageLoader);
    }
}
