package com.example.donatenowapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.donatenowapp.Adopter.FoodAdopter;
import com.example.donatenowapp.R;
import com.squareup.picasso.Picasso;

public class FoodDetailActivity extends AppCompatActivity {
    TextView txtname,txtdes;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        imageView = findViewById(R.id.imageView);
        txtname = findViewById(R.id.txtname);
        txtname.setText(FoodAdopter.fName);
        txtdes = findViewById(R.id.txtdes);
        txtdes.setText(FoodAdopter.fDes);
        Picasso.with(FoodDetailActivity.this).load(FoodAdopter.fImg).placeholder(R.mipmap.image_large).into(imageView);
    }

}