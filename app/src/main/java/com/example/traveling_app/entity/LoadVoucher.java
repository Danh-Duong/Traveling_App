package com.example.traveling_app.entity;

public class LoadVoucher {
    public static void load(){
        luu_discount_obj voucher1 = new luu_discount_obj("ond9af", 700,68,"10.9.2023-00:00", "https://s3.amazonaws.com/thumbnails.venngage.com/template/5456834b-ba95-41a9-85b2-4abd4d313c11.png");
        luu_discount_obj voucher2 = new luu_discount_obj("ahk3ks", 500,68,"10.9.2023-00:00", "https://quangcaoytuong.vn/userfiles/users/5d85354d9c2a5a9251814a206bb09b7bdda23272629db699ed2cf6918f11b3a6/voucher-1-quang-cao-y-tuong.jpeg");
        luu_discount_obj voucher3 = new luu_discount_obj("a5d7cd", 300,68,"10.9.2023-00:00", "https://inbacha.com/wp-content/uploads/2021/05/Voucher-Coupon-Giftcard2.jpg");
        luu_discount_obj voucher4 = new luu_discount_obj("a3gdb6", 450,68,"10.9.2023-00:00", "https://inaniprint.com/wp-content/uploads/2021/09/in-voucher-phieu-giam-gia-inaniprint.com_.jpg");
        luu_discount_obj voucher5 = new luu_discount_obj("ga4fd2", 600,68,"10.9.2023-00:00", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMPh-VKqaKVVN2zlQscLIiaGrwJ00QhhooBg&usqp=CAU");
        luu_discount_obj voucher6 = new luu_discount_obj("fa0da8", 200,68,"10.9.2023-00:00", "https://cdn-kvweb.kiotviet.vn/kiotviet-website/wp-content/uploads/2023/06/Voucher-spa-7.png");
        luu_discount_obj voucher7 = new luu_discount_obj("53fagv", 400,68,"10.9.2023-00:00", "https://promacprinting.com/wp-content/uploads/2023/02/in-voucher.png");
        luu_discount_obj voucher8 = new luu_discount_obj("ad53fs", 100,68,"10.9.2023-00:00", "https://innhanhhaiduong.vn/uploads/shops/2021_01/in-voucher.jpg");

        // Save the voucher to Firebase
        VoucherHelper voucherHelper = new VoucherHelper();
        voucherHelper.saveVoucher(voucher1);
        voucherHelper.saveVoucher(voucher2);
        voucherHelper.saveVoucher(voucher3);
        voucherHelper.saveVoucher(voucher4);
        voucherHelper.saveVoucher(voucher5);
        voucherHelper.saveVoucher(voucher6);
        voucherHelper.saveVoucher(voucher7);
        voucherHelper.saveVoucher(voucher8);

    }
}
