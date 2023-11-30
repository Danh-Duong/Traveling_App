package com.example.traveling_app.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.traveling_app.AdminActivity;
import com.example.traveling_app.MainActivity;
import com.example.traveling_app.R;
import com.example.traveling_app.SearchAndFilterActivity;
import com.example.traveling_app.entity.AdminTourAdapter;
import com.example.traveling_app.entity.BannerTourAdapter;
import com.example.traveling_app.entity.CurrentUser;
import com.example.traveling_app.entity.DataCallback;
import com.example.traveling_app.entity.HintTourAdapter;
import com.example.traveling_app.entity.HotTourAdapter;
import com.example.traveling_app.entity.ImageLoader;
import com.example.traveling_app.entity.NearTourAdapter;
import com.example.traveling_app.entity.RecentTourAdapter;
import com.example.traveling_app.entity.Review;
import com.example.traveling_app.entity.Tour;
import com.example.traveling_app.entity.User;
import com.example.traveling_app.entity.Voucher;
import com.example.traveling_app.entity.VoucherTourAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Menu_Home extends Fragment{
    private RecyclerView tour_hint_rcv, recent_rcv, voucher_rcv, hot_rcv, near_rcv;
    private SliderView sliderView;
    private HintTourAdapter hintTourAdapter;
    private RecentTourAdapter recentTourAdapter;
    private VoucherTourAdapter voucherTourAdapter;
    private HotTourAdapter hotTourAdapter;
    private NearTourAdapter nearTourAdapter;
    HashMap<String, Tour> tours=new HashMap<>();
    private List<Voucher> vouchers=new ArrayList<>();
    private MainActivity mainActivity;
    private View view;
    private EditText searchInput;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref=database.getReference();
    TextView username1;
    ImageView imgAvaMain;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_menu__home, container, false);

        searchInput=view.findViewById(R.id.searchInput);
        username1=view.findViewById(R.id.username1);
        imgAvaMain=view.findViewById(R.id.imgAvaMain);
        searchInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mainActivity, SearchAndFilterActivity.class);
                startActivity(intent);
            }
        });

        mainActivity= (MainActivity) getActivity();

        CurrentUser currentUser=null;
        if (mainActivity.getIntent().getSerializableExtra("user")!=null){
            User user= (User) mainActivity.getIntent().getSerializableExtra("user");
            currentUser=new CurrentUser(mainActivity,user);
        }

        username1.setText(currentUser.getCurrentUser().getUsername());
        if (currentUser.getCurrentUser().getImageUrl()!=null)
            ImageLoader.loadImage(currentUser.getCurrentUser().getImageUrl(),imgAvaMain);

        // chặn sự kiện Back của trang chủ
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_UP)
                    return true;
                return false;
            }
        });

        tour_hint_rcv=view.findViewById(R.id.tour_hint_rcv);
        recent_rcv=view.findViewById(R.id.recent_rcv);
        vouchers.add(new Voucher("Giảm giá",R.drawable.main_voucher1));
        vouchers.add(new Voucher("Giảm giá",R.drawable.main_voucher1));
        vouchers.add(new Voucher("Giảm giá",R.drawable.main_voucher1));
        voucher_rcv=view.findViewById(R.id.voucher_rcv);
        LinearLayoutManager ln3=new LinearLayoutManager(mainActivity,RecyclerView.HORIZONTAL,false);
        voucherTourAdapter =new VoucherTourAdapter(mainActivity,vouchers);
        voucher_rcv.setLayoutManager(ln3);
        voucher_rcv.setAdapter(voucherTourAdapter);
        hot_rcv=view.findViewById(R.id.hot_rcv);
        near_rcv=view.findViewById(R.id.near_rcv);
        sliderView = view.findViewById(R.id.imageSlider);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.SWAP); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setScrollTimeInSec(4);
        sliderView.startAutoCycle();

        getData(new DataCallback() {
            @Override
            public void onDataLoaded(List<Tour> tours) {
                LinearLayoutManager ln1=new LinearLayoutManager(mainActivity,RecyclerView.HORIZONTAL,false);;
                tour_hint_rcv.setLayoutManager(ln1);
                hintTourAdapter =new HintTourAdapter(mainActivity,tours);
                tour_hint_rcv.setAdapter(hintTourAdapter);

                LinearLayoutManager ln2=new LinearLayoutManager(mainActivity,RecyclerView.HORIZONTAL,false);
                recent_rcv.setLayoutManager(ln2);
                recentTourAdapter =new RecentTourAdapter(mainActivity,tours);
                recent_rcv.setAdapter(recentTourAdapter);

                LinearLayoutManager ln4=new LinearLayoutManager(mainActivity,RecyclerView.HORIZONTAL,false);;
                hot_rcv.setLayoutManager(ln4);
                hotTourAdapter =new HotTourAdapter(mainActivity,tours);
                hot_rcv.setAdapter(hotTourAdapter);

                LinearLayoutManager ln5=new LinearLayoutManager(mainActivity,RecyclerView.VERTICAL,false);;
                near_rcv.setLayoutManager(ln5);
                nearTourAdapter=new NearTourAdapter(mainActivity,tours);
                near_rcv.setAdapter(nearTourAdapter);

                BannerTourAdapter adapter = new BannerTourAdapter(mainActivity, tours);
                sliderView.setSliderAdapter(adapter);
            }

            @Override
            public void onTourLoaded(Tour Tour) {

            }

        });


        return view;
    }

    public void getData(final DataCallback callback){
        ref.child("tours").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Tour tour=snapshot.getValue(Tour.class);
                String id=snapshot.getKey();
                tour.setId(id);
                tours.put(id, tour);
                callback.onDataLoaded(new ArrayList<>(tours.values()));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String idTourModified=snapshot.getKey();
                Tour tourModified=snapshot.getValue(Tour.class);
                tours.put(idTourModified, tourModified);
                callback.onDataLoaded(new ArrayList<>(tours.values()));
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                String idTourDeleted=snapshot.getKey();
                tours.remove(idTourDeleted);
                callback.onDataLoaded(new ArrayList<>(tours.values()));
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}