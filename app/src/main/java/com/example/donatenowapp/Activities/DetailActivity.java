package com.example.donatenowapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donatenowapp.Model.AddToCart;
import com.example.donatenowapp.Model.Constant;
import com.example.donatenowapp.Model.FoodDonate;
import com.example.donatenowapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    TextView txtname,txtprice,txtdes,txt_count,txt_count2;
    ImageView picfood,plusBtn,minusBtn,plusBtn2,minusBtn2;
    private int i = 1;
    private int i2 = 1;
    DatabaseReference mdatabase,mdatabase2,databaseReference2,databaseReference3;
    FirebaseAuth firebaseAuthe;
    CheckBox checkbox;
    LinearLayout line1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        checkbox = findViewById(R.id.checkbox);
        line1 = findViewById(R.id.line1);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked){
                    line1.setVisibility(View.VISIBLE);
                }else {
                    line1.setVisibility(View.GONE);
                }
            }
        });

        txtname = findViewById(R.id.txtname);
        txtname.setText(Constant.pName);
        txtprice = findViewById(R.id.txtprice);
        txtprice.setText("Price: "+Constant.pPrice+" Rs");
        picfood = findViewById(R.id.picfood);
        Picasso.with(DetailActivity.this).load(Constant.pImage).placeholder(R.mipmap.image_large).into(picfood);
        txtdes = findViewById(R.id.txtdes);
        txtdes.setText(Constant.pDes);
        plusBtn = findViewById(R.id.plusBtn);
        plusBtn2 = findViewById(R.id.plusBtn2);
        txt_count = findViewById(R.id.txt_count);
        txt_count2 = findViewById(R.id.txt_count2);
        minusBtn = findViewById(R.id.minusBtn);
        minusBtn2 = findViewById(R.id.minusBtn2);
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_count.setText(String.valueOf(++i));
            }
        });
        plusBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_count2.setText(String.valueOf(++i2));
            }
        });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i>=1){
                    txt_count.setText(String.valueOf(i--));
                }
            }
        });
        minusBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i2>=1){
                    txt_count2.setText(String.valueOf(i2--));
                }
            }
        });

        databaseReference3 = FirebaseDatabase.getInstance().getReference().child("Product").child(Constant.pID);
        mdatabase2 = FirebaseDatabase.getInstance().getReference().child("FoodDonate");


    }
    public void addToCart(View view) {
        firebaseAuthe = FirebaseAuth.getInstance();
        String current_user_id = firebaseAuthe.getCurrentUser().getUid();
        mdatabase = FirebaseDatabase.getInstance().getReference().child("AddToCart").child(current_user_id).child(Constant.uCardID);
        if(checkbox.isChecked()){
            String uploadId1 = mdatabase.push().getKey();
            int sum = Integer.valueOf(Constant.pPrice)*Integer.valueOf(txt_count.getText().toString());
            AddToCart addToCart = new AddToCart(txtname.getText().toString(),String.valueOf(sum), String.valueOf(Constant.pPrice),txt_count.getText().toString().trim(),Constant.pImage,uploadId1);
            mdatabase.child(uploadId1).setValue(addToCart).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    String uploadId2 = mdatabase.push().getKey();
                    int sum = Integer.valueOf(Constant.pPrice)*Integer.valueOf(txt_count2.getText().toString());
                    AddToCart addToCart = new AddToCart(txtname.getText().toString(),String.valueOf(sum), String.valueOf(Constant.pPrice),txt_count2.getText().toString().trim(),Constant.pImage,uploadId2);
                    mdatabase.child(uploadId2).setValue(addToCart).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            FoodDonate foodDonate = new FoodDonate(
                                    txtname.getText().toString(),
                                    txtdes.getText().toString(),
                                    Constant.pImage,
                                    Constant.rID,
                                    current_user_id,
                                    uploadId2
                            );
                            mdatabase2.child(uploadId2).setValue(foodDonate).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(DetailActivity.this, "Check your cart", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                        }
                    });
                }
            });
        }else {
            String uploadId = mdatabase.push().getKey();
            int sum = Integer.valueOf(Constant.pPrice)*Integer.valueOf(txt_count.getText().toString());
            AddToCart addToCart = new AddToCart(txtname.getText().toString(),String.valueOf(sum), String.valueOf(Constant.pPrice),txt_count.getText().toString().trim(),Constant.pImage,uploadId);
            mdatabase.child(uploadId).setValue(addToCart).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(DetailActivity.this, "Check your cart", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }


    }
}