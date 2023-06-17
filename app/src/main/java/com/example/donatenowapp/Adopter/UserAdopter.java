package com.example.donatenowapp.Adopter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donatenowapp.Activities.FoodActivity;
import com.example.donatenowapp.Activities.LoginActivity;
import com.example.donatenowapp.Activities.ProductActivity;
import com.example.donatenowapp.Model.Restaurant;
import com.example.donatenowapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserAdopter extends RecyclerView.Adapter<UserAdopter.RecyclerHolder> {
    private Context mContext;
    private List<Restaurant>  mRestaurants;
    public static String rid = "";
    public UserAdopter(Context mContext, List<Restaurant> mRestaurants) {
        this.mContext = mContext;
        this.mRestaurants = mRestaurants;
    }

    @Override
    public UserAdopter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerHolder(LayoutInflater.from(mContext).inflate(R.layout.user_layout,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull UserAdopter.RecyclerHolder holder, int position) {
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
        TextView txtname,txtphone,txtaddress,txtorder,txtfood;
        ImageView imageView;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txtname);
            txtphone = itemView.findViewById(R.id.txtphone);
            txtfood = itemView.findViewById(R.id.txtfood);
            txtaddress = itemView.findViewById(R.id.txtaddress);
            imageView = itemView.findViewById(R.id.imageView);
            txtorder = itemView.findViewById(R.id.txtorder);
            txtfood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Restaurant user = mRestaurants.get(position);
                    rid = user.getrID();
                    mContext.startActivity(new Intent(mContext, FoodActivity.class));
                }
            });
            txtorder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    if(firebaseUser!=null) {
                        int position = getAdapterPosition();
                        Restaurant user = mRestaurants.get(position);
                        rid = user.getrID();
                        mContext.startActivity(new Intent(mContext, ProductActivity.class));
                    }else {
                        mContext.startActivity(new Intent(mContext, LoginActivity.class));
                    }
                }
            });
            txtaddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Restaurant user = mRestaurants.get(position);
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("geo:0,0?q="+user.getrLatitude()+","+user.getrLongitude()+" (" + "name" + ")"));
                    mContext.startActivity(intent);
                }
            });


        }
    }
}
