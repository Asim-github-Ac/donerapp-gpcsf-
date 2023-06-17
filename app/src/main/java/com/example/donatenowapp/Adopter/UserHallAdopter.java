package com.example.donatenowapp.Adopter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donatenowapp.Activities.FoodActivity;
import com.example.donatenowapp.Activities.ItemActivity;
import com.example.donatenowapp.Activities.LoginActivity;
import com.example.donatenowapp.Activities.ProductActivity;
import com.example.donatenowapp.Activities.ShowFoodsActivity;
import com.example.donatenowapp.Activities.UserHomeActivity;
import com.example.donatenowapp.Model.Hall;
import com.example.donatenowapp.Model.HallFood;
import com.example.donatenowapp.Model.Restaurant;
import com.example.donatenowapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserHallAdopter extends RecyclerView.Adapter<UserHallAdopter.RecyclerHolder> {
    private Context mContext;
    private List<Hall>  mRestaurants;
    public static String hID = "";
    public UserHallAdopter(Context mContext, List<Hall> mRestaurants) {
        this.mContext = mContext;
        this.mRestaurants = mRestaurants;
    }

    @Override
    public UserHallAdopter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerHolder(LayoutInflater.from(mContext).inflate(R.layout.user_hall_layout,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull UserHallAdopter.RecyclerHolder holder, int position) {
        Hall user = mRestaurants.get(position);
        holder.txtname.setText(user.gethName());
        holder.txtphone.setText(user.gethPhone());
        holder.txtaddress.setText(user.gethAddress());
        holder.txtemail.setText(user.gethEmail());
        holder.txtweb.setText(user.gethUrl());
        Picasso.with(mContext).load(user.gethImageUrl()).placeholder(R.mipmap.image_large).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView txtname,txtphone,txtaddress,txtemail,txtweb,txtView;
        ImageView imageView;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txtname);
            txtView = itemView.findViewById(R.id.txtView);
            txtphone = itemView.findViewById(R.id.txtphone);
            txtemail = itemView.findViewById(R.id.txtemail);
            txtaddress = itemView.findViewById(R.id.txtaddress);
            imageView = itemView.findViewById(R.id.imageView);
            txtweb = itemView.findViewById(R.id.txtweb);
            txtView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    if(firebaseUser!=null) {
                        int position = getAdapterPosition();
                        Hall user = mRestaurants.get(position);
                        hID = user.gethID();
                        mContext.startActivity(new Intent(mContext, ShowFoodsActivity.class));
                    }else {
                        mContext.startActivity(new Intent(mContext, LoginActivity.class));
                    }

                }
            });
            txtaddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Hall user = mRestaurants.get(position);
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("geo:0,0?q="+user.gethLatitude()+","+user.gethLongitude()+" (" + "name" + ")"));
                    mContext.startActivity(intent);
                }
            });




        }
    }
}
