package com.example.donatenowapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donatenowapp.Adopter.UserAdopter;
import com.example.donatenowapp.Adopter.UserHallAdopter;
import com.example.donatenowapp.Model.Hall;
import com.example.donatenowapp.Model.HallFood;
import com.example.donatenowapp.Model.Restaurant;
import com.example.donatenowapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserHallActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    RecyclerView recyclerView;
    private List<Hall> mUser;
    UserHallAdopter adminHomeAdopter;
    private DatabaseReference bDatabaseRef;
    private ProgressBar mProgressCircle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_hall);
        recyclerView = findViewById(R.id.recyclerView);
        mProgressCircle = findViewById(R.id.progress_circle);
        mUser = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UserHallActivity.this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adminHomeAdopter = new UserHallAdopter(UserHallActivity.this,mUser);
        recyclerView.setAdapter(adminHomeAdopter);
        bDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Hall");
        bDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUser.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Hall upload = postSnapshot.getValue(Hall.class);
                    if(upload.gethStatus().equals("1")) {
                        mUser.add(upload);
                        mProgressCircle.setVisibility(View.INVISIBLE);
                    }
                }
                adminHomeAdopter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UserHallActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}