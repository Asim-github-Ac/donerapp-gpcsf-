package com.example.donatenowapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donatenowapp.Adopter.FeedBackAdopter;
import com.example.donatenowapp.Model.Rating;
import com.example.donatenowapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FeedBackActivity extends AppCompatActivity {

    //////////////////
    RecyclerView mRecyclerView;
    List<Rating> mUploads;
    FeedBackAdopter homeAdopter;
    DatabaseReference mDataBaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        showData();
    }
    public void  showData(){
        mRecyclerView = findViewById(R.id.mRecyclerView);
        mUploads = new ArrayList<>();
        homeAdopter = new FeedBackAdopter(this, mUploads);
        mRecyclerView.setAdapter(homeAdopter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,1));
        mDataBaseReference = FirebaseDatabase.getInstance().getReference().child("Rating");
        mDataBaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUploads.clear();
                for(DataSnapshot postSnapShot : dataSnapshot.getChildren())
                {
                    Rating product = postSnapShot.getValue(Rating.class);
                    product.setuKey(postSnapShot.getKey());
                    mUploads.add(product);
                }
                homeAdopter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(FeedBackActivity.this, databaseError.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}