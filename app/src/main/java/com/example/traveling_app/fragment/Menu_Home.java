package com.example.traveling_app.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.traveling_app.AdminActivity;
import com.example.traveling_app.MainActivity;
import com.example.traveling_app.R;
import com.example.traveling_app.SearchAndFilterActivity;
import com.example.traveling_app.entity.BannerTourAdapter;
import com.example.traveling_app.entity.HintTourAdapter;
import com.example.traveling_app.entity.HotTourAdapter;
import com.example.traveling_app.entity.NearTourAdapter;
import com.example.traveling_app.entity.RecentTourAdapter;
import com.example.traveling_app.entity.Tour;
import com.example.traveling_app.entity.Voucher;
import com.example.traveling_app.entity.VoucherTourAdapter;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class Menu_Home extends Fragment {

    private RecyclerView tour_hint_rcv, recent_rcv, voucher_rcv, hot_rcv, near_rcv;
    private SliderView sliderView;
    private ImageButton btnNotification;
    private HintTourAdapter hintTourAdapter;
    private RecentTourAdapter recentTourAdapter;
    private VoucherTourAdapter voucherTourAdapter;
    private HotTourAdapter hotTourAdapter;
    private NearTourAdapter nearTourAdapter;
    private List<Tour> tours=new ArrayList<>();
    private List<Voucher> vouchers=new ArrayList<>();
    private MainActivity mainActivity;
    private View view;

    private EditText searchInput;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_menu__home, container, false);

        searchInput=view.findViewById(R.id.searchInput);
        searchInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mainActivity, SearchAndFilterActivity.class);
                startActivity(intent);
            }
        });

        tours.add(new Tour("Vịnh Hạ Long","Quảng Ninh","0121267",R.drawable.main_anh1,"Đầm được hình thành do tuyến đê tả ngạn sông Đáy nhằm ngăn lũ lụt từ đó biển Vân Long trở thành 1 vùng trù phú",4,1000000,500000, 100,100));
        tours.add(new Tour("Vịnh Hạ Long","Quảng Ninh","0121267",R.drawable.main_anh5,"Đầm được hình thành do tuyến đê tả ngạn sông Đáy nhằm ngăn lũ lụt từ đó biển Vân Long trở thành 1 vùng trù phú",4,1000000,500000, 100,100));
        tours.add(new Tour("Vịnh Hạ Long","Quảng Ninh","0121267",R.drawable.main_anh2,"Đầm được hình thành do tuyến đê tả ngạn sông Đáy nhằm ngăn lũ lụt từ đó biển Vân Long trở thành 1 vùng trù phú",4,1000000,500000, 100,100));
        tours.add(new Tour("Vịnh Hạ Long","Quảng Ninh","0121267",R.drawable.main_anh4,"Đầm được hình thành do tuyến đê tả ngạn sông Đáy nhằm ngăn lũ lụt từ đó biển Vân Long trở thành 1 vùng trù phú",4,1000000,500000, 100,100));

        mainActivity= (MainActivity) getActivity();

        tour_hint_rcv=view.findViewById(R.id.tour_hint_rcv);
        hintTourAdapter =new HintTourAdapter(mainActivity,tours);
        LinearLayoutManager ln1=new LinearLayoutManager(mainActivity,RecyclerView.HORIZONTAL,false);;
        tour_hint_rcv.setLayoutManager(ln1);
        tour_hint_rcv.setAdapter(hintTourAdapter);

        LinearLayoutManager ln2=new LinearLayoutManager(mainActivity,RecyclerView.HORIZONTAL,false);
        recent_rcv=view.findViewById(R.id.recent_rcv);
        recentTourAdapter =new RecentTourAdapter(mainActivity,tours);
        recent_rcv.setLayoutManager(ln2);
        recent_rcv.setAdapter(recentTourAdapter);

        vouchers.add(new Voucher("Giảm giá",R.drawable.main_voucher1));
        vouchers.add(new Voucher("Giảm giá",R.drawable.main_voucher1));
        vouchers.add(new Voucher("Giảm giá",R.drawable.main_voucher1));
        voucher_rcv=view.findViewById(R.id.voucher_rcv);
        LinearLayoutManager ln3=new LinearLayoutManager(mainActivity,RecyclerView.HORIZONTAL,false);
        voucherTourAdapter =new VoucherTourAdapter(mainActivity,vouchers);
        voucher_rcv.setLayoutManager(ln3);
        voucher_rcv.setAdapter(voucherTourAdapter);

        hot_rcv=view.findViewById(R.id.hot_rcv);
        hotTourAdapter =new HotTourAdapter(mainActivity,tours);
        LinearLayoutManager ln4=new LinearLayoutManager(mainActivity,RecyclerView.HORIZONTAL,false);;
        hot_rcv.setLayoutManager(ln4);
        hot_rcv.setAdapter(hotTourAdapter);

        near_rcv=view.findViewById(R.id.near_rcv);
        nearTourAdapter=new NearTourAdapter(mainActivity,tours);
        LinearLayoutManager ln5=new LinearLayoutManager(mainActivity,RecyclerView.VERTICAL,false);;
        near_rcv.setLayoutManager(ln5);
        near_rcv.setAdapter(nearTourAdapter);


        sliderView = view.findViewById(R.id.imageSlider);
        BannerTourAdapter adapter = new BannerTourAdapter(mainActivity,tours);
        sliderView.setSliderAdapter(adapter);

        // kiểu của các dot trong navigator
        sliderView.setIndicatorAnimation(IndicatorAnimationType.SWAP); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
//        sliderView.setIndicatorSelectedColor(R.color.primaryColor);
//        sliderView.setIndicatorUnselectedColor(R.color.black);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

        return view;
    }
}