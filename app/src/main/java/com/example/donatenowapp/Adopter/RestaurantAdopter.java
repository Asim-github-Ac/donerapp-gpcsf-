package com.example.donatenowapp.Adopter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donatenowapp.Activities.AddItemActivity;
import com.example.donatenowapp.Activities.AddProductActivity;
import com.example.donatenowapp.Model.Restaurant;
import com.example.donatenowapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RestaurantAdopter extends RecyclerView.Adapter<RestaurantAdopter.RecyclerHolder> {
    private Context mContext;
    private List<Restaurant>  mRestaurants;

    public RestaurantAdopter(Context mContext, List<Restaurant> mRestaurants) {
        this.mContext = mContext;
        this.mRestaurants = mRestaurants;
    }

    @Override
    public RestaurantAdopter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerHolder(LayoutInflater.from(mContext).inflate(R.layout.restaurant_layout,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdopter.RecyclerHolder holder, int position) {
        Restaurant user = mRestaurants.get(position);
        holder.txtname.setText(user.getrName());
        holder.txtphone.setText(user.getrPhone());
        holder.txtaddress.setText(user.getrLocation());
        Picasso.with(mContext).load(user.getrImageUrl()).placeholder(R.mipmap.image_large).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView txtname,txtphone,txtaddress,txtadd,txtProduct;
        ImageView imageView;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txtname);
            txtProduct = itemView.findViewById(R.id.txtProduct);
            txtphone = itemView.findViewById(R.id.txtphone);
            txtadd = itemView.findViewById(R.id.txtadd);
            txtaddress = itemView.findViewById(R.id.txtaddress);
            imageView = itemView.findViewById(R.id.imageView);
            txtProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, AddItemActivity.class));
                }
            });
            txtadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Restaurant user = mRestaurants.get(position);
                    mContext.startActivity(new Intent(mContext, AddProductActivity.class));
                }
            });
        }
    }
}
