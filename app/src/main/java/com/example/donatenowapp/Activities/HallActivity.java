package com.example.donatenowapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.donatenowapp.Adopter.HallAdopter;
import com.example.donatenowapp.Adopter.RestaurantAdopter;
import com.example.donatenowapp.Model.Hall;
import com.example.donatenowapp.Model.Restaurant;
import com.example.donatenowapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HallActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<Hall> mUser;
    HallAdopter adminHomeAdopter;
    private DatabaseReference bDatabaseRef;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hall);
        recyclerView = findViewById(R.id.recyclerView);
        mUser = new ArrayList<>();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String uid = firebaseUser.getUid();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HallActivity.this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adminHomeAdopter = new HallAdopter(HallActivity.this,mUser);
        recyclerView.setAdapter(adminHomeAdopter);
        bDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Hall");
        bDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUser.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Hall upload = postSnapshot.getValue(Hall.class);
                    if(upload.gethStatus().equals("1")) {
                        if(upload.gethID().equals(uid)){
                            mUser.add(upload);
                        }
                    }
                }
                adminHomeAdopter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(HallActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void addRE(View view) {
        startActivity(new Intent(this, AddHallActivity.class));
    }
}