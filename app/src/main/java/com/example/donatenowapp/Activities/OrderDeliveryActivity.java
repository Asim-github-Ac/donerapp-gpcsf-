package com.example.donatenowapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donatenowapp.Adopter.OrderDeliverAdapter;
import com.example.donatenowapp.Adopter.ShowOrderAdapter;
import com.example.donatenowapp.Model.AddToCart;
import com.example.donatenowapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderDeliveryActivity extends AppCompatActivity {
    private RecyclerView bRecyclerView;
    private OrderDeliverAdapter bAdapter;
    private DatabaseReference bDatabaseRef;
    private List<AddToCart> bUploads;
    TextView txttoalt,textView15;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_delivery);
        bRecyclerView = findViewById(R.id.recycler_view);
        textView15 = findViewById(R.id.textView15);
        textView15.setText(ShowOrderAdapter.namees+" "+  ShowOrderAdapter.phone);
        bRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        bUploads = new ArrayList<>();
        bAdapter = new OrderDeliverAdapter(OrderDeliveryActivity.this, bUploads);
        bRecyclerView.setAdapter(bAdapter);
        String uID = getIntent().getStringExtra("uID");
        String cardID = getIntent().getStringExtra("cardID");
        txttoalt = findViewById(R.id.txttotal);
        bDatabaseRef = FirebaseDatabase.getInstance().getReference().child("AddToCart").child(uID).child(cardID);
        bDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bUploads.clear();
                int total = 0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    AddToCart upload = postSnapshot.getValue(AddToCart.class);
                    upload.setKey(postSnapshot.getKey());
                    bUploads.add(upload);
                    total = total + Integer.parseInt(upload.getpPrice());

                }
                bAdapter.notifyDataSetChanged();
                txttoalt.setText("Rs. "+String.valueOf(total+0));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(OrderDeliveryActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}