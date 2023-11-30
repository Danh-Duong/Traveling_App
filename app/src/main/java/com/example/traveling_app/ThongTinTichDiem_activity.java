package com.example.traveling_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class ThongTinTichDiem_activity extends AppCompatActivity {
    private ImageView back;
    private TextView point;
    private AppCompatButton use_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtintichdiem_main);
        point= findViewById(R.id.point);
        use_btn = findViewById(R.id.use_btn);
        back=findViewById(R.id.back_Point);
        use_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongTinTichDiem_activity.this, luu_book_tour.class);
                intent.putExtra("key_point",point.getText());
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}