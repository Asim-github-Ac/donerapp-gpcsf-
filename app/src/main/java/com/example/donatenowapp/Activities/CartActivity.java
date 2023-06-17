package com.example.donatenowapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.donatenowapp.Adopter.AddToCart_Adapter;
import com.example.donatenowapp.Model.AddToCart;
import com.example.donatenowapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private AddToCart_Adapter mAdapter;
    TextView totals;
    FirebaseAuth mAuth;
    String current_user_id;
    private DatabaseReference mDatabaseRef,mdatabase,udatabase,databaseReference2;
    private ValueEventListener mDBListener;
    private List<AddToCart> mUploads;
    int total = 0;
    ImageView imageView;
    NestedScrollView nested;
    Button btnok;
    public static String uCardID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        mAuth = FirebaseAuth.getInstance();
        current_user_id = mAuth.getCurrentUser().getUid();
        mRecyclerView = findViewById(R.id.gujranwala_recycler_view);
        totals = findViewById(R.id.txttotal);
        imageView = findViewById(R.id.imageView);
        nested = findViewById(R.id.nested);
        btnok = findViewById(R.id.btnok);
        mUploads = new ArrayList<AddToCart>();
        mAdapter = new AddToCart_Adapter(this, mUploads);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,1));
        mRecyclerView.setAdapter(mAdapter);
        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("User").child(current_user_id);
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                uCardID = dataSnapshot.child("uCardID").getValue(String.class);
                mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("AddToCart").child(current_user_id).child(uCardID);
                mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mUploads.clear();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            AddToCart upload = postSnapshot.getValue(AddToCart.class);
                            upload.setKey(postSnapshot.getKey());
                            mUploads.add(upload);
                            total = total + Integer.parseInt(upload.getpPrice());
                        }
                        if(mUploads.isEmpty()){
                            imageView.setVisibility(View.VISIBLE);
                            nested.setVisibility(View.GONE);
                        }

                        mAdapter.notifyDataSetChanged();
                        totals.setText("PKR " + String.valueOf(total));

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this,ProcedToCheckoutActivity.class));

            }
        });
    }
}