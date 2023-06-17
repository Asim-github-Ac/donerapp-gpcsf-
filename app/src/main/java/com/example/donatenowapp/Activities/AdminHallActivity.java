package com.example.donatenowapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.donatenowapp.Adopter.AdminAdopter;
import com.example.donatenowapp.Adopter.AdminHallAdopter;
import com.example.donatenowapp.Model.Hall;
import com.example.donatenowapp.Model.Restaurant;
import com.example.donatenowapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminHallActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<Hall> mUser;
    AdminHallAdopter adminHomeAdopter;
    private DatabaseReference bDatabaseRef;
    private ProgressBar mProgressCircle;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_hall);
        firebaseAuth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        mProgressCircle = findViewById(R.id.progress_circle);
        mUser = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AdminHallActivity.this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adminHomeAdopter = new AdminHallAdopter(AdminHallActivity.this,mUser);
        recyclerView.setAdapter(adminHomeAdopter);
        bDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Hall");
        bDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUser.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Hall upload = postSnapshot.getValue(Hall.class);
                    mUser.add(upload);
                    mProgressCircle.setVisibility(View.INVISIBLE);
                }
                adminHomeAdopter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AdminHallActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}