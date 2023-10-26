package com.example.traveling_app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traveling_app.DetailActivity;
import com.example.traveling_app.R;
import com.example.traveling_app.entity.Service;
import com.example.traveling_app.entity.ServiceTourAdapter;
import com.example.traveling_app.luu_book_tour;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment {
    private List<Service> services=new ArrayList<>();
    private RecyclerView service_rcv;
    private View view;
    private DetailActivity detailActivity;

    private TextView book_tour;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        detailActivity=(DetailActivity)getActivity();
        view= inflater.inflate(R.layout.fragment_detail, container, false);

        book_tour=view.findViewById(R.id.book_tour);
        book_tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(detailActivity, luu_book_tour.class);
                startActivity(intent);
            }
        });

        service_rcv=view.findViewById(R.id.service_rcv);
        services.add(new Service(R.drawable.face_smile,"Vé vào cổng điểm tham quan: công viên nước WaterCin Phú Quốc"));
        services.add(new Service(R.drawable.face_smile,"Thưởng thức bữa sáng miễn phí"));
        services.add(new Service(R.drawable.face_smile,"Ngủ 1 đêm tại khách sạn tại phòng Standard"));
        services.add(new Service(R.drawable.face_smile,"Nhận voucher 500.000 đ"));

        ServiceTourAdapter serviceTourAdapter=new ServiceTourAdapter(detailActivity,services);
        LinearLayoutManager ln=new LinearLayoutManager(detailActivity, RecyclerView.VERTICAL, false);
        service_rcv.setLayoutManager(ln);
        service_rcv.setAdapter(serviceTourAdapter);
        return view;
    }
}