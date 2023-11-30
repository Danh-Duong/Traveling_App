package com.example.traveling_app.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
//
//import com.example.traveling_app.Blog_activity;
//import com.example.traveling_app.CreateBlog_activity;
import com.example.traveling_app.CreateBlog_activity;
import com.example.traveling_app.MainActivity;
import com.example.traveling_app.R;
import com.example.traveling_app.Status_activity;
import com.example.traveling_app.entity.PostCustomAdapter;
import com.example.traveling_app.model.Post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Menu_Blog extends Fragment {

    private ImageView back, create;
    private View view;
    private MainActivity mainActivity;
    private List<Post> posts=new ArrayList<>();
    PostCustomAdapter postCustomAdapter;

    GridView grv_post;

    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("posts");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_menu__blog, container, false);

        mainActivity= (MainActivity) getActivity();
        back=view.findViewById(R.id.back_blog_main);
        create=view.findViewById(R.id.create_blog_main);
        grv_post=view.findViewById(R.id.grv_post);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainActivity, CreateBlog_activity.class));
            }
        });

        bindingData();

        return view;
    }

    public void bindingData(){
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data: snapshot.getChildren()){
                    Post post= data.getValue(Post.class);
                    posts.add(post);
                }
                postCustomAdapter=new PostCustomAdapter(mainActivity, posts);
                grv_post.setAdapter(postCustomAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}