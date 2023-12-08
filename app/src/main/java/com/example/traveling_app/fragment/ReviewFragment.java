package com.example.traveling_app.fragment;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.traveling_app.DetailActivity;
import com.example.traveling_app.R;
import com.example.traveling_app.entity.CurrentUser;
import com.example.traveling_app.entity.ImageLoader;
import com.example.traveling_app.entity.Review;
import com.example.traveling_app.entity.ReviewAdapter;
import com.example.traveling_app.entity.SharedViewModel;
import com.example.traveling_app.entity.Tour;
import com.example.traveling_app.model.user.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ReviewFragment extends Fragment {

    private List<Review> reviews=new ArrayList<>();
    private RecyclerView review_rcv;
    private DetailActivity detailActivity;
    private View view;
    LinearLayout sao1, sao2, sao3, sao4, sao5;
    private static final int PICK_IMAGE = 1;
    ImageView btnUpload,bl_anh1, bl_anh2,bl_anh3,bl_anh4,star1,star2,star3,star4,star5, btn_bl1,image_user_review;
    int numStar;
    DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
    EditText inputBl;
    FirebaseStorage storage=FirebaseStorage.getInstance();
    StorageReference storageRef=storage.getReferenceFromUrl("gs://traveling-app-7d1f0.appspot.com");
    List<Uri> uriList=new ArrayList<>();
    private SharedViewModel viewModel;
    ReviewAdapter reviewAdapter;
    ProgressDialog progressDialog;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault());
    String now=sdf.format(new Date());
    TextView numCom1,numCom2,numCom3,numCom4,numCom5;
    int nC1=0,nC2=0,nC3=0,nC4=0,nC5=0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        detailActivity= (DetailActivity) getActivity();
        view= inflater.inflate(R.layout.fragment_review, container, false);

        review_rcv=view.findViewById(R.id.review_rcv);
        LinearLayoutManager ln=new LinearLayoutManager(detailActivity, LinearLayoutManager.VERTICAL,false);
        review_rcv.setLayoutManager(ln);

        bl_anh1=view.findViewById(R.id.bl_anh1);
        bl_anh2=view.findViewById(R.id.bl_anh2);
        bl_anh3=view.findViewById(R.id.bl_anh3);
        bl_anh4=view.findViewById(R.id.bl_anh4);

        star1=view.findViewById(R.id.star1);
        star2=view.findViewById(R.id.star2);
        star3=view.findViewById(R.id.star3);
        star4=view.findViewById(R.id.star4);
        star5=view.findViewById(R.id.star5);
        sao1=view.findViewById(R.id.sao1);
        sao2=view.findViewById(R.id.sao2);
        sao3=view.findViewById(R.id.sao3);
        sao4=view.findViewById(R.id.sao4);
        sao5=view.findViewById(R.id.sao5);
        image_user_review=view.findViewById(R.id.image_user_review);
        btnUpload=view.findViewById(R.id.btn_up_bl);

        numCom1=view.findViewById(R.id.numCom1);
        numCom2=view.findViewById(R.id.numCom2);
        numCom3=view.findViewById(R.id.numCom3);
        numCom4=view.findViewById(R.id.numCom4);
        numCom5=view.findViewById(R.id.numCom5);

        btn_bl1=view.findViewById(R.id.btn_bl1);
        inputBl=view.findViewById(R.id.inputBl);

        bindingData();
        Bundle bundle = getArguments();
        if (bundle != null) {
            numStar=bundle.getInt("numStar",9999);
            viewModel.setNumRate(numStar);
        }

        sao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataByStar(1);
            }
        });
        sao2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataByStar(2);
            }
        });
        sao3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataByStar(3);
            }
        });
        sao4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataByStar(4);
            }
        });
        sao5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataByStar(5);
            }
        });

        btn_bl1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (inputBl.getText()==null || inputBl.getText().toString().trim().equals("") || viewModel.getNumRate()==0){
                    AlertDialog.Builder b = new AlertDialog.Builder(detailActivity);
                    b.setTitle("Thông báo");
                    b.setMessage("Vui lòng nhập đầy đủ thông tin");
                    b.setPositiveButton("BACK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    
                    b.setIcon(R.drawable.error);
                    AlertDialog al = b.create();
                    al.show();
                }
                else {
                    progressDialog = new ProgressDialog(detailActivity);
                    progressDialog.setMessage("Đang tạo đánh giá...");
                    progressDialog.show();
                    Calendar calendar = Calendar.getInstance();

                    // Tạo danh sách tác vụ (Tasks) để lấy URL
                    List<Task<Uri>> tasks = new ArrayList<>();

                    for (int i = 0; i < uriList.size(); i++) {
                        Uri imageUri = uriList.get(i);
                        StorageReference reviewsRef = storageRef.child("images").child("reviews").child("image" + calendar.getTimeInMillis() + "_" + i + ".png");
                        UploadTask uploadTask = reviewsRef.putFile(imageUri);

                        // Thêm Task vào danh sách
                        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if (!task.isSuccessful()) {
                                    throw task.getException();
                                }
                                return reviewsRef.getDownloadUrl();
                            }
                        });

                        tasks.add(urlTask);
                    }

                    // Sử dụng Tasks.whenAllSuccess() để đợi cho đến khi tất cả các tác vụ hoàn thành
                    Tasks.whenAllSuccess(tasks).addOnSuccessListener(new OnSuccessListener<List<Object>>() {
                        @Override
                        public void onSuccess(List<Object> list) {
                            // Tất cả các URL đã được lấy thành công
                            List<String> urlImages = new ArrayList<>();
                            for (Object obj : list) {
                                String imgAvaUrl = ((Uri) obj).toString();
                                urlImages.add(imgAvaUrl);
                            }

                            // Tạo đối tượng Review chỉ khi urlImages không rỗng
                            if (!urlImages.isEmpty() || uriList.size() == 0) {
                                Review review = new Review(CurrentUser.getCurrentUser().getUsername(),
                                        CurrentUser.getCurrentUser().getProfileImage(),
                                        viewModel.getNumRate(),
                                        now,
                                        inputBl.getText().toString(),
                                        urlImages);

                                // Đẩy dữ liệu lên Firebase
                                ref.child("tours").child(detailActivity.getIntent().getStringExtra("id")).child("reviews").child(CurrentUser.getCurrentUser().getUsername()).push().setValue(review, new DatabaseReference.CompletionListener() {
                                    @Override
                                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                        if (error == null) {
                                            progressDialog.cancel();
                                            for (int i = 0; i < 4; i++) {
                                                uriList.clear();
                                                // ẩn hình ảnh
                                                getImageViewByIndex(i).setVisibility(View.GONE);
                                            }
                                            for (int i = 1; i <= viewModel.getNumRate(); i++)
                                                getStarByIndex(i).setImageResource(R.drawable.main_star_disable);
                                            inputBl.setText("");
                                            // set giá trị để xem list danh sách
                                            getDataByStar(0);
                                            // vì giá trị này không lưu trong db nên t gọi hàm
                                            bindingData();
                                            // lưu giá trị của numRate và numComment lại
                                            DatabaseReference reference=FirebaseDatabase.getInstance().getReference();
                                            reference.child("tours").child(detailActivity.getIntent().getStringExtra("id")).addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    Tour tour=snapshot.getValue(Tour.class);
                                                    double numRateCurrent=tour.getNumStar();
                                                    int numCommentCurrent=tour.getNumComment();
                                                    // cập nhập số lượng rate
                                                    double numNewRate=(numRateCurrent*(numCommentCurrent)/(numCommentCurrent+1))+viewModel.getNumRate()*1.0/(numCommentCurrent+1);
                                                    reference.child("tours").child(detailActivity.getIntent().getStringExtra("id")).child("numStar").setValue(numNewRate);
                                                    // cập nhập số lượng comment
                                                    reference.child("tours").child(detailActivity.getIntent().getStringExtra("id")).child("numComment").setValue(numCommentCurrent+1);
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });
                                        } else {
                                            // Xử lý lỗi khi push dữ liệu lên Firebase
                                            Toast.makeText(detailActivity, "Lỗi khi đẩy dữ liệu lên Firebase", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
        }

        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true); // Cho phép chọn nhiều ảnh
                startActivityForResult(intent, PICK_IMAGE);
            }
        });
        return view;
    }
    

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==PICK_IMAGE && resultCode==RESULT_OK && data!=null){
            if (data.getClipData() != null) {
                uriList.clear();
                ClipData clipData = data.getClipData();
                int length=clipData.getItemCount()>4?4:clipData.getItemCount();
                for (int i = 0; i < length; i++) {
                    Uri imageUri = clipData.getItemAt(i).getUri();
                    setImageView(imageUri,i);
                    uriList.add(imageUri);
                }
            } else if (data.getData() != null) {
                Uri imageUri = data.getData();
                uriList.add(imageUri);
                setImageView(imageUri,0);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setImageView(Uri imageUri, int index) {
        ImageView imageView = getImageViewByIndex(index);
        Glide.with(this)
                .load(imageUri)
                .into(imageView);
        imageView.setVisibility(View.VISIBLE);
    }

    private ImageView getImageViewByIndex(int index) {
        switch (index) {
            case 0:
                return bl_anh1;
            case 1:
                return bl_anh2;
            case 2:
                return bl_anh3;
            case 3:
                return bl_anh4;
            default:
                return null;
        }
    }

    private LinearLayout getLlByIndex(int index) {
        switch (index) {
            case 1:
                return sao1;
            case 2:
                return sao2;
            case 3:
                return sao3;
            case 4:
                return sao4;
            case 5:
                return sao5;
            default:
                return null;
        }
    }

    private ImageView getStarByIndex(int index) {
        switch (index) {
            case 1:
                return star1;
            case 2:
                return star2;
            case 3:
                return star3;
            case 4:
                return star4;
            case 5:
                return star5;
            default:
                return null;
        }
    }

    public void bindingData(){
        nC1=0;
        nC2=0;
        nC3=0;
        nC4=0;
        nC5=0;
        if (reviews.size()>0)
            reviews.clear();
        ref.child("tours").child(detailActivity.getIntent().getStringExtra("id")).child("reviews").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.exists()){
                    for (DataSnapshot reviewSnapshot : snapshot.getChildren()) {
                        Review review = reviewSnapshot.getValue(Review.class);
                        review.setNameReviewer(snapshot.getKey());

                        ref.child("users").child(review.getNameReviewer()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshotUser) {
//                                Log.d("oke",snapshotUser.getValue()+"");
                                if (snapshotUser.getValue(User.class).getProfileImage()!=null)
                                    review.setAvatarReviewer(snapshotUser.getValue(User.class).getProfileImage());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        switch (review.getRate())
                        {
                            case 1:
                                nC1++;
                                break;
                            case 2:
                                nC2++;
                                break;
                            case 3:
                                nC3++;
                                break;
                            case 4:
                                nC4++;
                                break;
                            case 5:
                                nC5++;
                                break;
                        }
                        reviews.add(review);
                    }
                    numCom1.setText(nC1+"");
                    numCom2.setText(nC2+"");
                    numCom3.setText(nC3+"");
                    numCom4.setText(nC4+"");
                    numCom5.setText(nC5+"");

                    List<Review> sortReviews = reviews.stream()
                            .sorted()
                            .collect(Collectors.toList());
                    Collections.reverse(sortReviews);

                    viewModel.setReviews(sortReviews);
                    reviewAdapter=new ReviewAdapter(detailActivity, sortReviews);
                    review_rcv.setAdapter(reviewAdapter);
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (!CurrentUser.getCurrentUser().getProfileImage().equals(""))
            //ImageLoader.loadImage(CurrentUser.getCurrentUser().getProfileImage(),image_user_review);
            Glide.with(getContext()).load(CurrentUser.getCurrentUser().getProfileImage()).into(image_user_review);

    }


    public void getDataByStar(int star){

        for (int i = 1; i <= 5; i++) {
            LinearLayout ln = getLlByIndex(i);
            ln.setBackgroundResource(i==star ? R.color.star : R.drawable.review_border);
        }
        List<Review> list= viewModel.getReviews().stream().filter(r -> r.getRate()==star).collect(Collectors.toList());
        reviewAdapter=new ReviewAdapter(detailActivity, list);
        review_rcv.setAdapter(reviewAdapter);
    }
}