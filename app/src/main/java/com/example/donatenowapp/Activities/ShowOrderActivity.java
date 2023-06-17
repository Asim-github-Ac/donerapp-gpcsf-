package com.example.donatenowapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.donatenowapp.Adopter.ShowOrderAdapter;
import com.example.donatenowapp.Model.Order;
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

public class ShowOrderActivity extends AppCompatActivity {
    private RecyclerView bRecyclerView;
    private ShowOrderAdapter bAdapter;
    private DatabaseReference bDatabaseRef;
    private List<Order> bUploads;
    private ProgressBar mProgressCircle;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_order);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String uid = firebaseUser.getUid();
        bRecyclerView = findViewById(R.id.recycler_view);
        bRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        bUploads = new ArrayList<>();
        bAdapter = new ShowOrderAdapter(ShowOrderActivity.this, bUploads);
        bRecyclerView.setAdapter(bAdapter);
        mProgressCircle = findViewById(R.id.progress_circle);
        bDatabaseRef = FirebaseDatabase.getInstance().getReference().child("CustomerOrder");
        bDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bUploads.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Order upload = postSnapshot.getValue(Order.class);
                    if(uid.equals(upload.getrID())) {
                        upload.setoKey(postSnapshot.getKey());
                        bUploads.add(upload);
                        mProgressCircle.setVisibility(View.INVISIBLE);
                    }
                }
                bAdapter.notifyDataSetChanged();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ShowOrderActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}