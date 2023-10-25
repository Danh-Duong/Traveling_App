package com.example.traveling_app.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveling_app.DetailActivity;
import com.example.traveling_app.R;
import com.example.traveling_app.entity.Review;
import com.example.traveling_app.entity.ReviewAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReviewFragment extends Fragment {

    private List<Review> reviews=new ArrayList<>();
    private RecyclerView review_rcv;
    private DetailActivity detailActivity;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        detailActivity= (DetailActivity) getActivity();
        view= inflater.inflate(R.layout.fragment_review, container, false);

        review_rcv=view.findViewById(R.id.review_rcv);
        reviews.add(new Review("Danh",R.drawable.main_avatar,5, new Date(),"Mọi thứ đều rất tuyệt vời, tui rất là thích."));
        reviews.add(new Review("Dũng",R.drawable.main_avatar,5, new Date(),"Mọi thứ đều rất tuyệt vời, tui rất là thích."));
        reviews.add(new Review("Lưu",R.drawable.main_avatar,5, new Date(),"Mọi thứ đều rất tuyệt vời, tui rất là thích."));
        reviews.add(new Review("Vương",R.drawable.main_avatar,5, new Date(),"Mọi thứ đều rất tuyệt vời, tui rất là thích."));
        reviews.add(new Review("Thắng",R.drawable.main_avatar,5, new Date(),"Mọi thứ đều rất tuyệt vời, tui rất là thích."));

        ReviewAdapter reviewAdapter=new ReviewAdapter(detailActivity, reviews);
        LinearLayoutManager ln=new LinearLayoutManager(detailActivity, LinearLayoutManager.VERTICAL,false);
        review_rcv.setLayoutManager(ln);
        review_rcv.setAdapter(reviewAdapter);
        return view;
    }
}