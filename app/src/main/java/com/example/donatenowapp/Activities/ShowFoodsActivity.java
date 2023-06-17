package com.example.donatenowapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.donatenowapp.Adopter.ProductListAdopter;
import com.example.donatenowapp.Adopter.ShowFoodsAdopter;
import com.example.donatenowapp.Adopter.UserAdopter;
import com.example.donatenowapp.Adopter.UserHallAdopter;
import com.example.donatenowapp.Model.Hall;
import com.example.donatenowapp.Model.HallFood;
import com.example.donatenowapp.Model.Products;
import com.example.donatenowapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowFoodsActivity extends AppCompatActivity {
    private RecyclerView bRecyclerView;
    private ShowFoodsAdopter bAdapter;
    private DatabaseReference bDatabaseRef;
    private List<HallFood> bUploads;
    private ProgressBar mProgressCircle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_foods);
        bRecyclerView = findViewById(R.id.recycler_view);
        bRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        bUploads = new ArrayList<>();
        bAdapter = new ShowFoodsAdopter(ShowFoodsActivity.this, bUploads);
        bRecyclerView.setAdapter(bAdapter);
        mProgressCircle = findViewById(R.id.progress_circle);
        bDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Hall_Foods");
        bDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                bUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    HallFood upload = postSnapshot.getValue(HallFood.class);
                    if(UserHallAdopter.hID.equals(upload.gethID())) {
                        bUploads.add(upload);
                        mProgressCircle.setVisibility(View.INVISIBLE);
                    }
                }
                bAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ShowFoodsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}