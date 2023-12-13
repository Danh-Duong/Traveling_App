package com.example.traveling_app.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
//
//import com.example.traveling_app.Blog_activity;
//import com.example.traveling_app.CreateBlog_activity;
import com.example.traveling_app.activity.CreateBlogActivity;
import com.example.traveling_app.R;
import com.example.traveling_app.common.DatabaseReferences;
import com.example.traveling_app.model.post.Post;
import com.example.traveling_app.model.post.PostGridRecyclerViewAdapter;
import com.example.traveling_app.model.post.PostSnapshotParser;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.Query;


public class Menu_Blog extends Fragment {

    private ImageView back, create;
    private PostGridRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Query query = DatabaseReferences.POST_DATABASE_REF.orderByKey();
        FirebaseRecyclerOptions<Post> options = new FirebaseRecyclerOptions.Builder<Post>().setQuery(query, PostSnapshotParser.INSTANCE).build();
        View view = inflater.inflate(R.layout.fragment_menu__blog, container, false);
        adapter = new PostGridRecyclerViewAdapter(options, getContext());
        back = view.findViewById(R.id.back_blog_main);
        create = view.findViewById(R.id.create_blog_main);
        create.setOnClickListener(v -> startActivity(new Intent(getContext(), CreateBlogActivity.class)));
        RecyclerView postRecyclerView = view.findViewById(R.id.postRecyclerView);
        postRecyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.startListening();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adapter.stopListening();
    }

}