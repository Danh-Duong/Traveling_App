package com.example.traveling_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.traveling_app.entity.CurrentUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ThongTinTichDiem_activity extends AppCompatActivity {
    private ImageView back;
    private TextView point;
    private AppCompatButton use_btn;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtintichdiem_main);
        point = findViewById(R.id.point);
        use_btn = findViewById(R.id.use_btn);
        back = findViewById(R.id.back_Point);
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(CurrentUser.getCurrentUser().getUsername());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                point.setText(snapshot.child("point").getValue(Integer.class).toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        use_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongTinTichDiem_activity.this, luu_book_tour.class);
                intent.putExtra("key_point", point.getText());
                databaseReference.child("point").setValue(0);
                startActivity(intent);
                finish();
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