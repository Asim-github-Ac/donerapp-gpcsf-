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

import com.example.donatenowapp.Activities.AddHallFoodActivity;
import com.example.donatenowapp.Activities.AddItemActivity;
import com.example.donatenowapp.Activities.AddProductActivity;
import com.example.donatenowapp.Model.Hall;
import com.example.donatenowapp.Model.Restaurant;
import com.example.donatenowapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HallAdopter extends RecyclerView.Adapter<HallAdopter.RecyclerHolder> {
    private Context mContext;
    private List<Hall>  mRestaurants;
    public static String hID = "";

    public HallAdopter(Context mContext, List<Hall> mRestaurants) {
        this.mContext = mContext;
        this.mRestaurants = mRestaurants;
    }

    @Override
    public HallAdopter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerHolder(LayoutInflater.from(mContext).inflate(R.layout.hall_layout,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull HallAdopter.RecyclerHolder holder, int position) {
        Hall user = mRestaurants.get(position);
        holder.txtname.setText(user.gethName());
        holder.txtphone.setText(user.gethPhone());
        holder.txtaddress.setText(user.gethAddress());
        holder.txtemail.setText(user.gethEmail());
        Picasso.with(mContext).load(user.gethImageUrl()).placeholder(R.mipmap.image_large).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView txtname,txtphone,txtaddress,txtadd,txtProduct,txtemail;
        ImageView imageView;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txtname);
            txtemail = itemView.findViewById(R.id.txtemail);
            txtProduct = itemView.findViewById(R.id.txtProduct);
            txtphone = itemView.findViewById(R.id.txtphone);
            txtadd = itemView.findViewById(R.id.txtadd);
            txtaddress = itemView.findViewById(R.id.txtaddress);
            imageView = itemView.findViewById(R.id.imageView);
            txtadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Hall user = mRestaurants.get(position);
                    hID = user.gethID();
                    mContext.startActivity(new Intent(mContext, AddHallFoodActivity.class));
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
