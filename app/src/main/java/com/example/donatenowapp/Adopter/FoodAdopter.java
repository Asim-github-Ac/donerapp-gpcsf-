package com.example.donatenowapp.Adopter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donatenowapp.Activities.DetailActivity;
import com.example.donatenowapp.Activities.FoodDetailActivity;
import com.example.donatenowapp.Model.Constant;
import com.example.donatenowapp.Model.FoodDonate;
import com.example.donatenowapp.Model.Products;
import com.example.donatenowapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodAdopter extends RecyclerView.Adapter<FoodAdopter.RecyclerHolder> {
    private Context mContext;
    private List<FoodDonate> mUploads;
    DatabaseReference databaseReference2;
    public static String fName = "";
    public static String fDes = "";
    public static String fImg = "";

    public FoodAdopter(Context mContext, List<FoodDonate> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.food_list, parent, false);
        return new RecyclerHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        FoodDonate uploadCurrent = mUploads.get(position);
        holder.txtname.setText(uploadCurrent.getfName());
        holder.txtdes.setText(uploadCurrent.getfDes());
        Picasso.with(mContext).load(uploadCurrent.getfImg()).placeholder(R.mipmap.image_large).into(holder.imageView);
        String rID = uploadCurrent.getrID();
        databaseReference2 = FirebaseDatabase.getInstance().getReference("User").child(rID);
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String uName = dataSnapshot.child("uName").getValue(String.class);
                String uAddress = dataSnapshot.child("uAddress").getValue(String.class);
                String uPhone = dataSnapshot.child("uPhone").getValue(String.class);
                holder.txtprice.setText(uPhone);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView txtname,txtprice,txtdes;
        ImageView imageView;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txtname);
            imageView = itemView.findViewById(R.id.imageView);
            txtprice = itemView.findViewById(R.id.txtprice);
            txtdes = itemView.findViewById(R.id.txtdes);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    FoodDonate uploadCurrent = mUploads.get(position);
                    fName = uploadCurrent.getfName();
                    fDes = uploadCurrent.getfDes();
                    fImg = uploadCurrent.getfImg();
                    mContext.startActivity(new Intent(mContext, FoodDetailActivity.class));
                }
            });

        }
    }
}
