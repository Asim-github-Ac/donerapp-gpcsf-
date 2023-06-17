package com.example.donatenowapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.donatenowapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuthe;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        firebaseAuthe = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuthe.getCurrentUser();
        if(firebaseUser!= null)
        {
            String uID = firebaseUser.getUid();
            databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(uID);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String uType = dataSnapshot.child("uType").getValue(String.class);
                    String uStatus = dataSnapshot.child("uStatus").getValue(String.class);
                    if(uType.equals("User"))
                    {
                        startActivity(new Intent(SplashActivity.this, UserHomeActivity.class));
                        Toast.makeText(SplashActivity.this, "User Login", Toast.LENGTH_SHORT).show();
                        finish();
                    }else if(uType.equals("Admin")){
                        startActivity(new Intent(SplashActivity.this, AdminHomeActivity.class));
                        Toast.makeText(SplashActivity.this, "Admin Login", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, UserHomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 4000);

        }
    }
}