package com.example.traveling_app.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.traveling_app.R;
import com.example.traveling_app.SearchAndFilterActivity;
import com.example.traveling_app.entity.BannerTourAdapter;
import com.example.traveling_app.entity.CurrentUser;
import com.example.traveling_app.entity.DataCallback;
import com.example.traveling_app.entity.HintTourAdapter;
import com.example.traveling_app.entity.HotTourAdapter;
import com.example.traveling_app.entity.ImageLoader;
import com.example.traveling_app.entity.NearTourAdapter;
import com.example.traveling_app.entity.RecentTourAdapter;
import com.example.traveling_app.entity.Tour;
import com.example.traveling_app.entity.Voucher;
import com.example.traveling_app.entity.VoucherTourAdapter;
import com.example.traveling_app.model.user.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import java.util.ArrayList;
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
    private View view;
    private EditText searchInput;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref=database.getReference();
    TextView username1;
    ImageView imgAvaMain;
    CurrentUser currentUser=null;

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
                Intent intent=new Intent(getContext(), SearchAndFilterActivity.class);
                startActivity(intent);
            }
        });

        Activity mainActivity = getActivity();

        if (mainActivity.getIntent().getSerializableExtra("user")!=null){
            User user= (User) mainActivity.getIntent().getSerializableExtra("user");
            Log.d("danh123",user.toString());
            currentUser=new CurrentUser(mainActivity,user);
        }

        username1.setText(currentUser.getCurrentUser().getUsername());
        if (currentUser.getCurrentUser().getProfileImage()!=null)
            ImageLoader.loadImage(currentUser.getCurrentUser().getProfileImage(),imgAvaMain);

        imgAvaMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentUser.getCurrentUser().getRole()==1)
                    startActivity(new Intent(mainActivity, AdminActivity.class));
            }
        });

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
//        vouchers.add(new Voucher("Giảm giá",R.drawable.main_voucher1));
//        vouchers.add(new Voucher("Giảm giá",R.drawable.main_voucher1));
//        vouchers.add(new Voucher("Giảm giá",R.drawable.main_voucher1));
        voucher_rcv=view.findViewById(R.id.voucher_rcv);
        LinearLayoutManager ln3=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        voucherTourAdapter =new VoucherTourAdapter(getContext(),vouchers);
        voucher_rcv.setLayoutManager(ln3);

        hot_rcv=view.findViewById(R.id.hot_rcv);
        near_rcv=view.findViewById(R.id.near_rcv);
        sliderView = view.findViewById(R.id.imageSlider);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.SWAP); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setScrollTimeInSec(4);
        sliderView.startAutoCycle();

        LinearLayoutManager ln1=new LinearLayoutManager(mainActivity,RecyclerView.HORIZONTAL,false);;
        tour_hint_rcv.setLayoutManager(ln1);
        LinearLayoutManager ln2=new LinearLayoutManager(mainActivity,RecyclerView.HORIZONTAL,false);
        recent_rcv.setLayoutManager(ln2);

        LinearLayoutManager ln4=new LinearLayoutManager(mainActivity,RecyclerView.HORIZONTAL,false);;
        hot_rcv.setLayoutManager(ln4);
        LinearLayoutManager ln5=new LinearLayoutManager(mainActivity,RecyclerView.VERTICAL,false);;
        near_rcv.setLayoutManager(ln5);

        getDataVoucher();
        getData(new DataCallback() {
            @Override
            public void onDataLoaded(List<Tour> tours) {
                LinearLayoutManager ln1=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                tour_hint_rcv.setLayoutManager(ln1);
                hintTourAdapter =new HintTourAdapter(getContext(),tours);
                tour_hint_rcv.setAdapter(hintTourAdapter);

                LinearLayoutManager ln2=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                recent_rcv.setLayoutManager(ln2);
                recentTourAdapter =new RecentTourAdapter(getContext(),tours);
                recent_rcv.setAdapter(recentTourAdapter);

                LinearLayoutManager ln4=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
                hot_rcv.setLayoutManager(ln4);
                hotTourAdapter =new HotTourAdapter(getContext(),tours);
                hot_rcv.setAdapter(hotTourAdapter);

                LinearLayoutManager ln5=new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);;
                near_rcv.setLayoutManager(ln5);
                nearTourAdapter=new NearTourAdapter(getContext(),tours);
                near_rcv.setAdapter(nearTourAdapter);

                BannerTourAdapter adapter = new BannerTourAdapter(getContext(), tours);
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
                tour.setId(snapshot.getKey().toString());
                String id=snapshot.getKey();
                tour.setId(id);
//                updateInfo(id);
                tours.put(id, tour);
                callback.onDataLoaded(new ArrayList<>(tours.values()));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String idTourModified=snapshot.getKey();
                Tour tourModified=snapshot.getValue(Tour.class);
                tourModified.setId(snapshot.getKey().toString());
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

//    public void updateInfo(String tourName) {
//        DatabaseReference tourRef = ref.child("tours").child(tourName);
//
//        tourRef.child("reviews").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                int numComment = 0;
//                double numRate = 0;
//
//                for (DataSnapshot reviewSnapshot : snapshot.getChildren()) {
//                    for (DataSnapshot ds : reviewSnapshot.getChildren()) {
//                        Review review = ds.getValue(Review.class);
//                        numComment++;
//                        numRate += review.getRate();
//                    }
//                }
//
//                double averageRate = numComment > 0 ? numRate / numComment : 0;
//                tourRef.child("numComment").setValue(numComment);
//                tourRef.child("numStar").setValue(averageRate);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // Xử lý lỗi nếu cần
//            }
//        });
//    }


    public void getDataVoucher(){
        ref.child("vouchers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds: snapshot.getChildren()){
                    Voucher v=ds.getValue(Voucher.class);
                    vouchers.add(v);
                }
                voucherTourAdapter =new VoucherTourAdapter(mainActivity,vouchers);
                voucher_rcv.setAdapter(voucherTourAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}