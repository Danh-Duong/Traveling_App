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

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    public void goToAdmin(View v) {
        startActivity(new Intent(getApplicationContext(), AdminActivity.class));
    }

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
    }
}