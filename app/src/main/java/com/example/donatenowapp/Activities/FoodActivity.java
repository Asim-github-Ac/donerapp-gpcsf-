package com.example.donatenowapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.donatenowapp.Adopter.FoodAdopter;
import com.example.donatenowapp.Adopter.ProductListAdopter;
import com.example.donatenowapp.Adopter.UserAdopter;
import com.example.donatenowapp.Model.FoodDonate;
import com.example.donatenowapp.Model.Products;
import com.example.donatenowapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {
    private RecyclerView bRecyclerView;
    private FoodAdopter bAdapter;
    private DatabaseReference bDatabaseRef;
    private List<FoodDonate> bUploads;
    private ProgressBar mProgressCircle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        bRecyclerView = findViewById(R.id.recycler_view);
        bRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        bUploads = new ArrayList<>();
        bAdapter = new FoodAdopter(FoodActivity.this, bUploads);
        bRecyclerView.setAdapter(bAdapter);
        mProgressCircle = findViewById(R.id.progress_circle);
        bDatabaseRef = FirebaseDatabase.getInstance().getReference().child("FoodDonate");
        bDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                bUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    FoodDonate upload = postSnapshot.getValue(FoodDonate.class);
                    if(UserAdopter.rid.equals(upload.getrID())) {
                        bUploads.add(upload);
                        mProgressCircle.setVisibility(View.INVISIBLE);
                    }
                }
                bAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(FoodActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}