package com.example.traveling_app.entity;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ImageLoader {

    // Số lượng luồng tối đa trong pool
    private static final int MAX_POOL_SIZE = 5;

    // Thời gian giữa các công việc trong queue
    private static final int KEEP_ALIVE_TIME = 10;

    // Đơn vị thời gian của KEEP_ALIVE_TIME
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    // Executor để quản lý thread pool
    private static final Executor executor = new ThreadPoolExecutor(
            MAX_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            KEEP_ALIVE_TIME_UNIT,
            new LinkedBlockingQueue<>()
    );

    // Phương thức để load ảnh sử dụng Glide trong thread pool
    public static void loadImage(String imageUrl, ImageView imageView) {
        // Sử dụng Glide trong executor
        executor.execute(() -> {
            try {
                Bitmap bitmap = Glide.with(imageView.getContext())
                        .asBitmap()
                        .load(imageUrl)
                        .submit()
                        .get();

                // Hiển thị ảnh trong UI thread
                imageView.post(() -> imageView.setImageBitmap(bitmap));
                // Hiển thị ảnh trong UI thread
//                imageView.post(() -> imageView.setImageBitmap(bitmap));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
