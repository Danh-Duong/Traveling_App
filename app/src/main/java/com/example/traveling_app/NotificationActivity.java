package com.example.traveling_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveling_app.entity.Notification;
import com.example.traveling_app.entity.NotificationAdapter;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView noti_rcv;
    private ImageButton btnHome;
    private List<Notification> newList=new ArrayList<>(), recentList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        noti_rcv=findViewById(R.id.noti_rcv);

        recentList.add(new Notification(1,R.drawable.main_avatar,"Nguyễn Văn A đã thích bài viết của bạn","2 giờ trước", true));
        recentList.add(new Notification(2,R.drawable.main_avatar,"Nguyễn Văn B đã thích bài viết của bạn","3 giờ trước", false));
        recentList.add(new Notification(3,R.drawable.main_avatar,"Nguyễn Văn C đã thích bài viết của bạn","4 giờ trước",false));
        recentList.add(new Notification(4,R.drawable.main_avatar,"Nguyễn Văn A đã thích bài viết của bạn","2 giờ trước",false));

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        noti_rcv.setLayoutManager(linearLayoutManager);
        NotificationAdapter recentAdapter=new NotificationAdapter(recentList);
        noti_rcv.setAdapter(recentAdapter);

        RecyclerView.ItemDecoration itemDecoration=new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        noti_rcv.addItemDecoration(itemDecoration);
        btnHome=findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(NotificationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);
        actionBar.setTitle("Thông báo");
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notification, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}