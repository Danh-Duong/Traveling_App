package com.example.traveling_app.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BillGenerator {
    public static String generateBillId() {
        // Lấy thời gian hiện tại dưới dạng timestamp
        long timestamp = System.currentTimeMillis();

        // Định dạng thời gian thành chuỗi để sử dụng làm ID
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault());
        String billId = sdf.format(new Date(timestamp));

        return billId;
    }
}
