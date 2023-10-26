package com.example.traveling_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.traveling_app.entity.BannerTourAdapter;
import com.example.traveling_app.entity.BottomNaviAdapter;
import com.example.traveling_app.entity.HintTourAdapter;
import com.example.traveling_app.entity.HotTourAdapter;
import com.example.traveling_app.entity.NearTourAdapter;
import com.example.traveling_app.entity.RecentTourAdapter;
import com.example.traveling_app.entity.Tour;
import com.example.traveling_app.entity.ViewPagerAdapter;
import com.example.traveling_app.entity.Voucher;
import com.example.traveling_app.entity.VoucherTourAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private RecyclerView tour_hint_rcv, recent_rcv, voucher_rcv, hot_rcv, near_rcv;
//
//    private SliderView sliderView;
//    private ImageButton btnNotification;
//    private HintTourAdapter hintTourAdapter;
//    private RecentTourAdapter recentTourAdapter;
//    private VoucherTourAdapter voucherTourAdapter;
//    private HotTourAdapter hotTourAdapter;
//    private NearTourAdapter nearTourAdapter;
//    private List<Tour> tours=new ArrayList<>();
//    private List<Voucher> vouchers=new ArrayList<>();

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager=findViewById(R.id.view_pager_main);
        bottomNavigationView=findViewById(R.id.bottom_navigation);

        BottomNaviAdapter bottomNaviAdapter=new BottomNaviAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(bottomNaviAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                       bottomNavigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menu_blog).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.menu_love).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.menu_noti).setChecked(true);
                        break;
                    case 4:
                        bottomNavigationView.getMenu().findItem(R.id.menu_menu).setChecked(true);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.menu_home)
                    viewPager.setCurrentItem(0);
                else if (item.getItemId()==R.id.menu_blog)
                    viewPager.setCurrentItem(1);
                else if (item.getItemId()==R.id.menu_love)
                    viewPager.setCurrentItem(2);
                else if (item.getItemId()==R.id.menu_noti)
                    viewPager.setCurrentItem(3);
                else if (item.getItemId()==R.id.menu_menu)
                    viewPager.setCurrentItem(4);

                return true;
            }
        });

//        tours.add(new Tour("Vịnh Hạ Long","Quảng Ninh","0121267",R.drawable.main_anh1,"Đầm được hình thành do tuyến đê tả ngạn sông Đáy nhằm ngăn lũ lụt từ đó biển Vân Long trở thành 1 vùng trù phú",4,1000000,500000, 100,100));
//        tours.add(new Tour("Vịnh Hạ Long","Quảng Ninh","0121267",R.drawable.main_anh5,"Đầm được hình thành do tuyến đê tả ngạn sông Đáy nhằm ngăn lũ lụt từ đó biển Vân Long trở thành 1 vùng trù phú",4,1000000,500000, 100,100));
//        tours.add(new Tour("Vịnh Hạ Long","Quảng Ninh","0121267",R.drawable.main_anh2,"Đầm được hình thành do tuyến đê tả ngạn sông Đáy nhằm ngăn lũ lụt từ đó biển Vân Long trở thành 1 vùng trù phú",4,1000000,500000, 100,100));
//        tours.add(new Tour("Vịnh Hạ Long","Quảng Ninh","0121267",R.drawable.main_anh4,"Đầm được hình thành do tuyến đê tả ngạn sông Đáy nhằm ngăn lũ lụt từ đó biển Vân Long trở thành 1 vùng trù phú",4,1000000,500000, 100,100));

        /*tour_hint_rcv=findViewById(R.id.tour_hint_rcv);
        hintTourAdapter =new HintTourAdapter(this,tours);
        LinearLayoutManager ln1=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);;
        tour_hint_rcv.setLayoutManager(ln1);
        tour_hint_rcv.setAdapter(hintTourAdapter);

        LinearLayoutManager ln2=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recent_rcv=findViewById(R.id.recent_rcv);
        recentTourAdapter =new RecentTourAdapter(this,tours);
        recent_rcv.setLayoutManager(ln2);
        recent_rcv.setAdapter(recentTourAdapter);

        vouchers.add(new Voucher("Giảm giá",R.drawable.main_voucher1));
        vouchers.add(new Voucher("Giảm giá",R.drawable.main_voucher1));
        vouchers.add(new Voucher("Giảm giá",R.drawable.main_voucher1));
        voucher_rcv=findViewById(R.id.voucher_rcv);
        LinearLayoutManager ln3=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        voucherTourAdapter =new VoucherTourAdapter(this,vouchers);
        voucher_rcv.setLayoutManager(ln3);
        voucher_rcv.setAdapter(voucherTourAdapter);

        hot_rcv=findViewById(R.id.hot_rcv);
        hotTourAdapter =new HotTourAdapter(this,tours);
        LinearLayoutManager ln4=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);;
        hot_rcv.setLayoutManager(ln4);
        hot_rcv.setAdapter(hotTourAdapter);

        near_rcv=findViewById(R.id.near_rcv);
        nearTourAdapter=new NearTourAdapter(this,tours);
        LinearLayoutManager ln5=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);;
        near_rcv.setLayoutManager(ln5);
        near_rcv.setAdapter(nearTourAdapter);

        btnNotification=findViewById(R.id.btnNotification);
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        sliderView = findViewById(R.id.imageSlider);
        BannerTourAdapter adapter = new BannerTourAdapter(this,tours);
        sliderView.setSliderAdapter(adapter);

        // kiểu của các dot trong navigator
        sliderView.setIndicatorAnimation(IndicatorAnimationType.SWAP); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
//        sliderView.setIndicatorSelectedColor(R.color.primaryColor);
//        sliderView.setIndicatorUnselectedColor(R.color.black);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();*/
    }
}