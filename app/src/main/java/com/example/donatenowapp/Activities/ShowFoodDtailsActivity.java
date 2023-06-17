package com.example.donatenowapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.donatenowapp.Adopter.FoodAdopter;
import com.example.donatenowapp.Adopter.ShowFoodsAdopter;
import com.example.donatenowapp.R;
import com.squareup.picasso.Picasso;

public class ShowFoodDtailsActivity extends AppCompatActivity {
    ImageView imageView;
    TextView txtname,txtquantity,txtduration,txtphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_food_dtails);
        imageView = findViewById(R.id.imageView);
        txtname = findViewById(R.id.txtname);
        txtname.setText(ShowFoodsAdopter.hfName);
        txtquantity = findViewById(R.id.txtquantity);
        txtquantity.setText(ShowFoodsAdopter.hfQuantity);
        txtduration = findViewById(R.id.txtduration);
        txtduration.setText(ShowFoodsAdopter.hfDuration);
        txtphone = findViewById(R.id.txtphone);
        txtphone.setText(ShowFoodsAdopter.hfPhone);
        Picasso.with(ShowFoodDtailsActivity.this).load(ShowFoodsAdopter.hfImage).placeholder(R.mipmap.image_large).into(imageView);


    }
}