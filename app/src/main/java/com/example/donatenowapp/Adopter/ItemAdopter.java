package com.example.donatenowapp.Adopter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donatenowapp.Activities.FoodActivity;
import com.example.donatenowapp.Activities.ItemDetailActivity;
import com.example.donatenowapp.Activities.ProductActivity;
import com.example.donatenowapp.Model.Items;
import com.example.donatenowapp.Model.Restaurant;
import com.example.donatenowapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemAdopter extends RecyclerView.Adapter<ItemAdopter.RecyclerHolder> {
    private Context mContext;
    private List<Items>  mRestaurants;
    public static String iName = "";
    public static String iDes = "";
    public static String iPhone = "";
    public static String iAddress = "";
    public static String iImage = "";
    DatabaseReference databaseReference2;
    public ItemAdopter(Context mContext, List<Items> mRestaurants) {
        this.mContext = mContext;
        this.mRestaurants = mRestaurants;
    }

    @Override
    public ItemAdopter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdopter.RecyclerHolder holder, int position) {
        Items user = mRestaurants.get(position);
        holder.txtname.setText(user.getiName());
        holder.txtphone.setText(user.getiPhone());
        holder.txtaddress.setText(user.getiAddress());
        Picasso.with(mContext).load(user.getiImage()).placeholder(R.mipmap.image_large).into(holder.imageView);
        String rID = user.getrID();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String uid = firebaseUser.getUid();
        if (uid.equals(rID)) {
            holder.txtstatus.setVisibility(View.GONE);
            holder.txtbook.setVisibility(View.VISIBLE);
        } else {
            holder.txtbook.setVisibility(View.GONE);
            holder.txtstatus.setVisibility(View.VISIBLE);
        }
        if(user.getIsBooked().equals("No")){
            holder.txtstatus.setText("Status: Book Now");
            holder.txtbook.setText("Book Now");
        }else if(user.getIsBooked().equals("Yes")){
            holder.txtstatus.setText("Status: Booked");
            holder.txtbook.setText("Booked");
        }else if(user.getIsBooked().equals("Get")){
            holder.txtstatus.setText("Status: Received");
            holder.txtbook.setText("Received");
        }
    }

    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView txtname,txtphone,txtaddress,txtbook,txtstatus;
        ImageView imageView;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txtname);
            txtstatus = itemView.findViewById(R.id.txtstatus);
            txtphone = itemView.findViewById(R.id.txtphone);
            txtaddress = itemView.findViewById(R.id.txtaddress);
            imageView = itemView.findViewById(R.id.imageView);
            txtbook = itemView.findViewById(R.id.txtbook);
            databaseReference2 = FirebaseDatabase.getInstance().getReference("Items");
            txtbook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Items user = mRestaurants.get(position);
                    if(txtbook.getText().toString().equals("Book Now")){
                        databaseReference2.child(user.getiID()).child("isBooked").setValue("Yes");
                    }else if(txtbook.getText().toString().equals("Booked")){
                        databaseReference2.child(user.getiID()).child("isBooked").setValue("Get");
                    }

                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Items user = mRestaurants.get(position);
                    iName = user.getiName();
                    iDes = user.getiDes();
                    iPhone = user.getiPhone();
                    iAddress = user.getiAddress();
                    iImage = user.getiImage();
                    mContext.startActivity(new Intent(mContext, ItemDetailActivity.class));
                }
            });
            txtaddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Items user = mRestaurants.get(position);
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("geo:0,0?q="+user.getiLatitude()+","+user.getiLongitude()+" (" + "name" + ")"));
                    mContext.startActivity(intent);
                }
            });


        }
    }
}
