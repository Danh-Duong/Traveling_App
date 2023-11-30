package com.example.traveling_app;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.traveling_app.entity.BillGenerator;
import com.example.traveling_app.entity.VoucherHelper;
import com.example.traveling_app.entity.luu_history_obj;
import com.example.traveling_app.fragment.DetailFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import vn.momo.momo_partner.AppMoMoLib;

public class luu_book_tour extends AppCompatActivity {
    Button dattour_btn;
    TextView amount_tv,name_tour_tv,address_tour_tv,price_tour_tv,saleprice_tour_tv,point_tv, email_tv, name_person_tv,phone_number_tv;
    TextInputEditText dateEnd_inp,dateStart_inp;
    ImageView tour_img;
    TextInputLayout calStart,calEnd;
    ImageButton next_mgg_btn,next_point_btn;
    private static String point, sale, dateEnd1, dateStart1, amount, email, name_person, phone_number, price1,idTour;
    private  DatabaseReference ref;
    private String fee = "0";
    int environment = 0;//developer default
    private String merchantName = "LeQuangLuu";
    private String merchantCode = "MOMOC2IC20220510";
    private String merchantNameLabel = "Nhà cung cấp";
    private String description = "Thanh toán dịch vụ đặt tour du lịch";

    //--------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luu_book_tour);
        anhXa();
        loadData();
        dateStart_inp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgay(dateStart_inp);
            }
        });
        dateEnd_inp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgay(dateEnd_inp);
            }
        });

        AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT);
        dattour_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePayment();
                requestPayment();
            }
        });

        next_mgg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveForm();
                Intent intent = new Intent(luu_book_tour.this, luu_magiamgia.class);
                startActivity(intent);
                amount_tv.setText(amount);
            }
        });
        next_point_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveForm();
                Intent intent = new Intent(luu_book_tour.this, ThongTinTichDiem_activity.class);
                startActivity(intent);
                amount_tv.setText(amount);
            }
        });
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_24);
        actionBar.setTitle("Đặt tour");
    }
    private void saveForm() {
        name_person = name_person_tv.getText().toString();
        email = email_tv.getText().toString();
        phone_number = phone_number_tv.getText().toString();
        dateEnd1 = dateEnd_inp.getText().toString();
        dateStart1 = dateStart_inp.getText().toString();
    }
    private void loadData() {
        if (getIntent().getStringExtra("id")!=null)
        {
            idTour= getIntent().getStringExtra("id");
        }

        if (idTour != null) {
            ref = FirebaseDatabase.getInstance().getReference().child("tours").child(idTour);
        }
        else {
            ref = FirebaseDatabase.getInstance().getReference().child("tours").child("03qqfi");
        }
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue(String.class);
                String address = snapshot.child("address").getValue(String.class);
                String end = snapshot.child("dateEnd").getValue(String.class);
                String start = snapshot.child("dateStart").getValue(String.class);
                String mainImageUrl = snapshot.child("mainImageUrl").getValue(String.class);
                name_tour_tv.setText(name);
                address_tour_tv.setText(address);
                dateStart_inp.setText(start);
                dateEnd_inp.setText(end);
                Glide.with(tour_img).load(mainImageUrl).into(tour_img);
                price1 = snapshot.child("price").getValue(Integer.class).toString();
                price_tour_tv.setText(price1+"đ");
                if (getIntent().getStringExtra("key_saleprice")==null && getIntent().getStringExtra("key_point")==null) {
                    amount_tv.setText(price1);
                    saleprice_tour_tv.setText("0");
                    point_tv.setText("0");
                }
                else {
                    if (getIntent().getStringExtra("key_saleprice")!=null)
                    {
                        int saleprice = Integer.parseInt(getIntent().getStringExtra("key_saleprice"))*1000;
                        amount_tv.setText(Integer.parseInt(price1) -saleprice - Integer.parseInt((String) point_tv.getText())  +"");
                        amount = amount_tv.getText().toString();
                        sale = saleprice+"";
                        saleprice_tour_tv.setText(saleprice+"");
                    }
                    if (getIntent().getStringExtra("key_point")!=null)
                    {
                        point = getIntent().getStringExtra("key_point");
                        amount_tv.setText(String.valueOf(Integer.parseInt(price1) - Integer.parseInt((String) saleprice_tour_tv.getText())- Integer.parseInt(point)));
                        amount = amount_tv.getText().toString();
                        point_tv.setText(point);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        if (getIntent().getStringExtra("key_point")!=null) {
            String point = getIntent().getStringExtra("key_point");
            point_tv.setText(point);
        }
        if (sale!=null) {
            saleprice_tour_tv.setText(sale);
        }
        if (point !=null) {
            point_tv.setText(point);
        }
        if (email!= null) {
            email_tv.setText(email);
        }
        if (name_person!=null) {
            name_person_tv.setText(name_person);
        }
        if (phone_number!=null) {
            phone_number_tv.setText(phone_number);
        }
        if (dateEnd1!=null) {
            dateEnd_inp.setText(dateEnd1);
        }
        if (dateStart1!=null) {
            dateStart_inp.setText(dateStart1);
        }

    }
    private void anhXa() {
        dattour_btn = (Button) findViewById(R.id.dattour_btn);
        name_tour_tv = findViewById(R.id.name_tour);
        address_tour_tv = findViewById(R.id.address_tour);
        price_tour_tv = findViewById(R.id.price_tour);
        amount_tv = findViewById(R.id.amount);
        next_mgg_btn = (ImageButton) findViewById(R.id.next_mgg_btn);
        next_point_btn = findViewById(R.id.next_point_btn);
        saleprice_tour_tv = findViewById(R.id.saleprice_tour);
        point_tv = findViewById(R.id.point_tv);
        dateEnd_inp = findViewById(R.id.dateEnd);
        dateStart_inp = findViewById(R.id.dateStart);
        calEnd = findViewById(R.id.calEnd);
        calStart = findViewById(R.id.calStart);
        email_tv = findViewById(R.id.email_tv);
        name_person_tv = findViewById(R.id.name_person_tv);
        phone_number_tv = findViewById(R.id.phone_number_tv);
        tour_img = findViewById(R.id.tour_img);
    }
    private void ChonNgay(TextInputEditText edtDate) {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year =  calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("dd/MM/yyyy");
                edtDate.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },year,month,day);
        datePickerDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notification, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void  savePayment() {
        luu_history_obj historyObj = new luu_history_obj();
        historyObj.setIdtour(idTour);
        historyObj.setPrice(Integer.parseInt((String) amount_tv.getText()));
        historyObj.setStartDate(String.valueOf(dateStart_inp.getText()));
        historyObj.setEndDate(String.valueOf(dateEnd_inp.getText()));
        historyObj.setUsername("defaultuser0");
        DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference().child("booking");
        ref.child(BillGenerator.generateBillId()).setValue(historyObj);
        if (getIntent().getStringExtra("key_id")!=null) {
            VoucherHelper voucherHelper = new VoucherHelper();
            voucherHelper.deleteVoucher(getIntent().getStringExtra("key_id").toString());
        }

    }
    //----------------------------------------------
    // request app MoMo
    private void requestPayment() {
        AppMoMoLib.getInstance().setAction(AppMoMoLib.ACTION.PAYMENT);
        AppMoMoLib.getInstance().setActionType(AppMoMoLib.ACTION_TYPE.GET_TOKEN);
        Map<String, Object> eventValue = new HashMap<>();
        //client Required
        eventValue.put("merchantname", merchantName); //Tên đối tác. được đăng ký tại https://business.momo.vn. VD: Google, Apple, Tiki , CGV Cinemas
        eventValue.put("merchantcode", merchantCode); //Mã đối tác, được cung cấp bởi MoMo tại https://business.momo.vn
        eventValue.put("amount", amount);
        eventValue.put("orderId", "bookId123456789"); //uniqueue id cho Bill order, giá trị duy nhất cho mỗi đơn hàng
        eventValue.put("orderLabel", "Mã đơn hàng");

        //client Optional - bill info
        eventValue.put("merchantnamelabel", "Dịch vụ");
        eventValue.put("fee", "0"); //Kiểu integer
        eventValue.put("description", description);

        //client extra data
        eventValue.put("requestId",  merchantCode+"merchant_billId_"+System.currentTimeMillis());
        eventValue.put("partnerCode", merchantCode);
        //Example extra data
        JSONObject objExtraData = new JSONObject();
        try {
            objExtraData.put("site_code", "008");
            objExtraData.put("site_name", "CGV Cresent Mall");
            objExtraData.put("screen_code", 0);
            objExtraData.put("screen_name", "Special");
            objExtraData.put("movie_name", "Kẻ Trộm Mặt Trăng 3");
            objExtraData.put("movie_format", "2D");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        eventValue.put("extraData", objExtraData.toString());
        eventValue.put("extra", "");
        AppMoMoLib.getInstance().requestMoMoCallBack(this, eventValue);


    }
    //Get token callback from MoMo app an submit to server side
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == AppMoMoLib.getInstance().REQUEST_CODE_MOMO && resultCode == -1) {
            if(data != null) {
                if(data.getIntExtra("status", -1) == 0) {
                    Intent intent = new Intent(luu_book_tour.this, luu_notifysuccess.class);
                    startActivity(intent);
                    //TOKEN IS AVAILABLE
                    Log.d("Thành công", data.getStringExtra("mesage"));
                    String token = data.getStringExtra("data"); //Token response
                    String phoneNumber = data.getStringExtra("phonenumber");
                    String env = data.getStringExtra("env");
                    if(env == null){
                        env = "app";
                    }

                    if(token != null && !token.equals("")) {
                    } else {
                        Log.d("Thành công", "Không thành công");
                    }
                } else if(data.getIntExtra("status", -1) == 1) {
                    //TOKEN FAIL
                    String message = data.getStringExtra("message") != null?data.getStringExtra("message"):"Thất bại";
                    Log.d("Thành công", "Không thành công");
                } else if(data.getIntExtra("status", -1) == 2) {
                    //TOKEN FAIL
                    Log.d("Thành công", "Không thành công");
                } else {
                    //TOKEN FAIL
                    Log.d("Thành công", "Không thành công");
                }
            } else {
                Log.d("Thành công", "Không thành công");
            }
        } else {
            Log.d("Thành công", "Không thành công");
        }
    }

}