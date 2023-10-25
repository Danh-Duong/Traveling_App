package com.example.traveling_app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.traveling_app.entity.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class DetailActivity extends AppCompatActivity{

    private TextView txtReview;
    private TextView txtDetail;

//    FrameLayout frameLayout;
//
//    FragmentManager fragmentManager;

    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);

        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

//        fragmentManager=getSupportFragmentManager();
//
//        DetailFragment detailFragment=new DetailFragment();
//        fragmentManager.beginTransaction().replace(R.id.frContent, detailFragment).commit();
//
//        txtReview=findViewById(R.id.txtReview);
//        txtDetail=findViewById(R.id.txtDetail);
//        txtReview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ReviewFragment reviewFragment=new ReviewFragment();
//                fragmentManager.beginTransaction().replace(R.id.frContent, reviewFragment).commit();
//            }
//        });
//
//        txtDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DetailFragment detailFragment=new DetailFragment();
//                fragmentManager.beginTransaction().replace(R.id.frContent, detailFragment).commit();
//            }
//        });

        // nút Back
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);
        actionBar.setTitle("");
    }

    // tạo menu cho action bar
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menu_item_call){
            Uri uri = Uri.parse("tel:01234679");
            if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL, uri);
                startActivity(intent);
            } else {
                requestPermissions(new String[] {Manifest.permission.CALL_PHONE}, 1);
            }
        }
        else if (item.getItemId()==android.R.id.home){
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == 1)
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                checkRight();
//    }
}