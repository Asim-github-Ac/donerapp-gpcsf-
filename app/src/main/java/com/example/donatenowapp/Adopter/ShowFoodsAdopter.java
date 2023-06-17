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
import com.example.donatenowapp.Activities.ShowFoodDtailsActivity;
import com.example.donatenowapp.Model.Constant;
import com.example.donatenowapp.Model.HallFood;
import com.example.donatenowapp.Model.Items;
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

public class ShowFoodsAdopter extends RecyclerView.Adapter<ShowFoodsAdopter.RecyclerHolder> {
    private Context mContext;
    private List<HallFood> mUploads;
    FirebaseAuth firebaseAuthe;
    DatabaseReference databaseReference2;
    public static String hfName = "";
    public static String hfQuantity = "";
    public static String hfDuration = "";
    public static String hfPhone = "";
    public static String hfImage = "";
    public ShowFoodsAdopter(Context mContext, List<HallFood> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.food_product_list, parent, false);
        return new RecyclerHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        HallFood uploadCurrent = mUploads.get(position);
        holder.txtname.setText(uploadCurrent.getHfName());
        holder.txtphone.setText(uploadCurrent.getHfPhone());
        holder.txtduration.setText(uploadCurrent.getHfDuration());
        holder.txtquantity.setText(uploadCurrent.getHfQuantity());
        Picasso.with(mContext).load(uploadCurrent.getHfImage()).placeholder(R.mipmap.image_large).into(holder.imageView);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String hid = firebaseUser.getUid();
        if(uploadCurrent.gethID().equals(hid)){
            holder.txtstatus.setVisibility(View.GONE);
            holder.txtbook.setVisibility(View.VISIBLE);
        }else {
            holder.txtbook.setVisibility(View.GONE);
            holder.txtstatus.setVisibility(View.VISIBLE);
        }

        if(uploadCurrent.getIsBooked().equals("No")){
            holder.txtstatus.setText("Status: Book Now");
            holder.txtbook.setText("Book Now");
        }else if(uploadCurrent.getIsBooked().equals("Yes")){
            holder.txtstatus.setText("Status: Booked");
            holder.txtbook.setText("Booked");
        }else if(uploadCurrent.getIsBooked().equals("Get")){
            holder.txtstatus.setText("Status: Received");
            holder.txtbook.setText("Received");
        }
    }


    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView txtname,txtphone,txtquantity,txtduration,txtbook,txtstatus;
        ImageView imageView;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txtname);
            txtstatus = itemView.findViewById(R.id.txtstatus);
            imageView = itemView.findViewById(R.id.imageView);
            txtphone = itemView.findViewById(R.id.txtphone);
            txtquantity = itemView.findViewById(R.id.txtquantity);
            txtduration = itemView.findViewById(R.id.txtduration);
            txtbook = itemView.findViewById(R.id.txtbook);
            databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Hall_Foods");
            txtbook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    HallFood uploadCurrent = mUploads.get(position);
                    if(txtbook.getText().toString().equals("Book Now")){
                        databaseReference2.child(uploadCurrent.getHfID()).child("isBooked").setValue("Yes");
                    }else if(txtbook.getText().toString().equals("Booked")){
                        databaseReference2.child(uploadCurrent.getHfID()).child("isBooked").setValue("Get");
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    HallFood uploadCurrent = mUploads.get(position);
                    hfName = uploadCurrent.getHfName();
                    hfQuantity = uploadCurrent.getHfQuantity();
                    hfDuration = uploadCurrent.getHfDuration();
                    hfPhone = uploadCurrent.getHfPhone();
                    hfImage = uploadCurrent.getHfImage();
                    mContext.startActivity(new Intent(mContext, ShowFoodDtailsActivity.class));
                }
            });
        }
    }
}
