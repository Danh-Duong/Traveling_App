package com.example.traveling_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class Chondangnhap_Activity extends AppCompatActivity {

    CallbackManager callbackManager;
    ImageView fbBtn;
    ImageView ggBtn;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    Button chondangnhap_btn1,chondangnhap_btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chondangnhap);

        callbackManager = CallbackManager.Factory.create();

        final LoginManager facebookLoginMgr = LoginManager.getInstance();
        facebookLoginMgr.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        startActivity(new Intent(Chondangnhap_Activity.this,MainActivity.class));
                        finish();
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

        fbBtn = findViewById(R.id.icon_fb);
        ggBtn = findViewById(R.id.icon_gg);
        fbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookLoginMgr.logInWithReadPermissions(Chondangnhap_Activity.this, Arrays.asList("public_profile"));
            }
        });

        ggBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });




        chondangnhap_btn1=(Button) findViewById(R.id.chondangnhap_btn1);
        chondangnhap_btn2=(Button) findViewById(R.id.chondangnhap_btn2);
        chondangnhap_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent1 = new Intent(getApplicationContext(), activity_dangnhap.class);
                startActivity(myintent1);
            }
        });
        chondangnhap_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent2 = new Intent(getApplicationContext(), activity_dangky.class);
                startActivity(myintent2);
            }
        });


    }



    void  signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }

    @Override
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

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//        super.onActivityResult(requestCode, resultCode, data);
//
//    }

    void  navigateToSecondActivity(){
        finish();
        Intent intent = new Intent(Chondangnhap_Activity.this, Login_google.class);
        startActivity(intent);
    }


} 