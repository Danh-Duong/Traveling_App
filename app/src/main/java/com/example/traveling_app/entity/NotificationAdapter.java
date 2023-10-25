package com.example.traveling_app.entity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.traveling_app.R;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>{

    private List<Notification> notifications;
    private ViewBinderHelper viewBinderHelper=new ViewBinderHelper();

    public NotificationAdapter(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notification notification=notifications.get(position);
        if (notification==null)
            return ;

        if (notification.getIsNew()==false)
            holder.main.setBackgroundResource(R.color.white);

        viewBinderHelper.bind(holder.swipeRevealLayout,notification.getId()+"");
        holder.txtContent.setText(notification.getContent());
        holder.txtTime.setText(notification.getTime());
        holder.img.setImageResource(notification.getImage());

        holder.layoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifications.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return notifications==null?0:notifications.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder{

       private SwipeRevealLayout swipeRevealLayout;
       private LinearLayout layoutDelete,main;
       private TextView txtContent, txtTime;
       private ImageView img;
       public NotificationViewHolder(@NonNull View itemView) {
           super(itemView);
           swipeRevealLayout=itemView.findViewById(R.id.swipeRevealLayout);
           layoutDelete=itemView.findViewById(R.id.layout_delete);
           main=itemView.findViewById(R.id.main);
           txtContent=itemView.findViewById(R.id.content_noti);
           txtTime=itemView.findViewById(R.id.time_noti);
           img=itemView.findViewById(R.id.image_noti);
       }
   }
}
