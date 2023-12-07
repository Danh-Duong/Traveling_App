package com.example.traveling_app;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.traveling_app.common.DatabaseReferences;
import com.example.traveling_app.model.user.User;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Arrays;

public class activity_dangky extends AppCompatActivity {

    CallbackManager callbackManager;
    ImageView fbBtn;
    ImageView ggBtn;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://traveling-app-7d1f0-default-rtdb.asia-southeast1.firebasedatabase.app/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        fbBtn = findViewById(R.id.icon_fb);
        ggBtn = findViewById(R.id.icon_gg);

        callbackManager = CallbackManager.Factory.create();

        final LoginManager facebookLoginMgr = LoginManager.getInstance();
        facebookLoginMgr.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Intent intent = new Intent(activity_dangky.this, MainActivity.class);

                        // Thực hiện lấy thông tin người dùng từ Graph API
                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        try {
                                            // Lấy tên người dùng từ JSON object
                                            String userName = object.getString("name");

                                            // Tạo đối tượng User với thông tin người dùng
                                            User user = new User();
                                            user.setUsername(userName);
                                            // Kiểm tra xem user đã tồn tại trong Firebase chưa
                                            DatabaseReferences.USER_DATABASE_REF.child(userName)
                                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            if (!snapshot.exists()) {
                                                                // User chưa tồn tại, đưa thông tin người dùng lên Firebase
                                                                snapshot.getRef().setValue(user);
                                                            }

                                                            // Chuyển đến MainActivity với thông tin người dùng
                                                            com.example.traveling_app.entity.User currentUser = new com.example.traveling_app.entity.User();
                                                            currentUser.setUsername(userName);
                                                            intent.putExtra("user", (Serializable) currentUser);
                                                            startActivity(intent);
                                                            finish();
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {
                                                            // Xử lý lỗi nếu cần
                                                        }
                                                    });

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "name,birthday,gender"); // Yêu cầu các trường thông tin cần thiết
                        request.setParameters(parameters);
                        request.executeAsync();

                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        fbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookLoginMgr.logInWithReadPermissions(activity_dangky.this, Arrays.asList("public_profile"));
            }
        });
        ggBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

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
                    User user = new User(fullnametxt, null, matkhautxt, null, User.DEFAULT_BIRTHDAY, false, null);
                    databaseReference.child("users/" + fullnametxt).setValue(user);
                    Toast.makeText(activity_dangky.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(activity_dangky.this, activity_dangnhap.class));


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


    void  signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(),"something wrong",Toast.LENGTH_SHORT).show();
            }
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    void  navigateToSecondActivity(){
        finish();
        Intent intent = new Intent(activity_dangky.this, Login_google.class);
        startActivity(intent);
    }
}