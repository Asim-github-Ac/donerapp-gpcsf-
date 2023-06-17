package com.example.donatenowapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.donatenowapp.Adopter.FoodAdopter;
import com.example.donatenowapp.Adopter.ItemAdopter;
import com.example.donatenowapp.R;
import com.squareup.picasso.Picasso;

public class ItemDetailActivity extends AppCompatActivity {
    TextView txtname,txtdes,txtaddress,txtphone;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        imageView = findViewById(R.id.imageView);
        txtname = findViewById(R.id.txtname);
        txtdes = findViewById(R.id.txtdes);
        txtaddress = findViewById(R.id.txtaddress);
        txtphone = findViewById(R.id.txtphone);
        txtname.setText(ItemAdopter.iName);
        txtdes.setText(ItemAdopter.iDes);
        txtaddress.setText(ItemAdopter.iAddress);
        txtphone.setText(ItemAdopter.iPhone);
        Picasso.with(ItemDetailActivity.this).load(ItemAdopter.iImage).placeholder(R.mipmap.image_large).into(imageView);

    }
}