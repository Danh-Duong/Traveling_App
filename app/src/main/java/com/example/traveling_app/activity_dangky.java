package com.example.traveling_app;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.traveling_app.entity.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class activity_dangky extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://traveling-app-7d1f0-default-rtdb.asia-southeast1.firebasedatabase.app/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        final EditText fullname = findViewById(R.id.dangky_user);
        final EditText email = findViewById(R.id.dangky_email);
        final EditText matkhau = findViewById(R.id.dangky_matkhau);
        final EditText xnmatkhau = findViewById(R.id.dangky_xnmatkhau);
        final Button btndangky = findViewById(R.id.at2_btn1);

        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fullnametxt= fullname.getText().toString();
                final String emailtxt= email.getText().toString();
                final String matkhautxt= matkhau.getText().toString();
                final String xnmatkhautxt= xnmatkhau.getText().toString();

                if(fullnametxt.isEmpty() || emailtxt.isEmpty() || matkhautxt.isEmpty()){
                    Toast.makeText(activity_dangky.this,"vui lòng nhập",Toast.LENGTH_SHORT).show();
                } else if (!matkhautxt.equals(xnmatkhautxt)) {
                    Toast.makeText(activity_dangky.this,"password are not matching", Toast.LENGTH_SHORT).show();

                }
                else {
                    User user = new User(emailtxt, fullnametxt, matkhautxt);
                    databaseReference.child("users/" + fullnametxt).setValue(user);
                    Toast.makeText(activity_dangky.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(activity_dangky.this, activity_dangnhap.class));
//                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//
//                            if(snapshot.hasChild(emailtxt)){
//                                Toast.makeText(activity_dangky.this, "email is already regisred", Toast.LENGTH_SHORT).show();
//
//                            }
//                            else {
//                                databaseReference.child("users").child(emailtxt).child("fullname").setValue(fullnametxt);
//                                databaseReference.child("users").child(emailtxt).child("fullname").setValue(emailtxt);
//                                databaseReference.child("users").child(emailtxt).child("fullname").setValue(matkhautxt);
//
//                                Toast.makeText(activity_dangky.this,"user succesfully",Toast.LENGTH_SHORT).show();
//                                finish();
//                            }
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError error) {
//
//                        }
//                    });


                }
            }
        });



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.baseline_keyboard_backspace_24); // Thay thế ic_arrow_back bằng ID của hình ảnh của bạn


        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        // Hiển thị nút quay lại trên Action Bar
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Hoặc thực hiện hành động cụ thể khi nút quay lại được nhấn
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}