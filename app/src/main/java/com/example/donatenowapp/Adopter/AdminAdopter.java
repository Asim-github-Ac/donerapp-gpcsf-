package com.example.donatenowapp.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donatenowapp.Model.Restaurant;
import com.example.donatenowapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdminAdopter extends RecyclerView.Adapter<AdminAdopter.RecyclerHolder> {
    private Context mContext;
    private List<Restaurant>  mRestaurants;
    DatabaseReference databaseReference2;

    public AdminAdopter(Context mContext, List<Restaurant> mRestaurants) {
        this.mContext = mContext;
        this.mRestaurants = mRestaurants;
    }

    @Override
    public AdminAdopter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerHolder(LayoutInflater.from(mContext).inflate(R.layout.admin_req_layout,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdminAdopter.RecyclerHolder holder, int position) {
        Restaurant user = mRestaurants.get(position);
        holder.txtname.setText(user.getrName());
        holder.txtphone.setText(user.getrPhone());
        holder.txtaddress.setText(user.getrLocation());
        Picasso.with(mContext).load(user.getrImageUrl()).placeholder(R.mipmap.image_large).into(holder.imageView);

        if(user.getrStatus().equals("1")){
            holder.btncancel.setVisibility(View.VISIBLE);
            holder.btnaccept.setVisibility(View.GONE);
        }else {
            holder.btncancel.setVisibility(View.GONE);
            holder.btnaccept.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView txtname,txtphone,txtaddress;
        ImageView imageView;
        Button btncancel,btnaccept;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txtname);
            txtphone = itemView.findViewById(R.id.txtphone);
            txtaddress = itemView.findViewById(R.id.txtaddress);
            imageView = itemView.findViewById(R.id.imageView);
            btncancel = itemView.findViewById(R.id.btncancel);
            btnaccept = itemView.findViewById(R.id.btnaccept);
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            databaseReference2 = FirebaseDatabase.getInstance().getReference("Restaurant");
            btnaccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Restaurant user = mRestaurants.get(position);
                    databaseReference2.child(user.getrID()).child("rStatus").setValue("1");
                    Toast.makeText(mContext, "Accepted", Toast.LENGTH_SHORT).show();

                }
            });
            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Restaurant user = mRestaurants.get(position);
                    databaseReference2.child(user.getrID()).child("rStatus").setValue("0");
                    Toast.makeText(mContext, "Cancel", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
