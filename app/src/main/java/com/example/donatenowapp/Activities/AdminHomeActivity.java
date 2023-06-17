package com.example.donatenowapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.donatenowapp.Adopter.AdminAdopter;
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

public class AdminHomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<Restaurant> mUser;
    AdminAdopter adminHomeAdopter;
    private DatabaseReference bDatabaseRef;
    private ProgressBar mProgressCircle;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        firebaseAuth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        mProgressCircle = findViewById(R.id.progress_circle);
        mUser = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AdminHomeActivity.this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adminHomeAdopter = new AdminAdopter(AdminHomeActivity.this,mUser);
        recyclerView.setAdapter(adminHomeAdopter);
        bDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Restaurant");
        bDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mUser.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Restaurant upload = postSnapshot.getValue(Restaurant.class);
                    mUser.add(upload);
                    mProgressCircle.setVisibility(View.INVISIBLE);
                }
                adminHomeAdopter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AdminHomeActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.log_out:
                firebaseAuth.signOut();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.hall:
                startActivity(new Intent(this, AdminHallActivity.class));
                return true;
            case R.id.feedback:
                startActivity(new Intent(this,FeedBackActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}