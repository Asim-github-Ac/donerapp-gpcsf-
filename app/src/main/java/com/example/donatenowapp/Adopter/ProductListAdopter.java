package com.example.donatenowapp.Adopter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donatenowapp.Activities.DetailActivity;
import com.example.donatenowapp.Model.Constant;
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

public class ProductListAdopter extends RecyclerView.Adapter<ProductListAdopter.RecyclerHolder> {
    private Context mContext;
    private List<Products> mUploads;
    FirebaseAuth firebaseAuthe;
    DatabaseReference databaseReference2;
    public ProductListAdopter(Context mContext, List<Products> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.product_list, parent, false);
        return new RecyclerHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        Products uploadCurrent = mUploads.get(position);
        holder.txtname.setText(uploadCurrent.getpName());
        holder.txtdes.setText(uploadCurrent.getpDes());
        holder.txtprice.setText("Rs "+uploadCurrent.getpPrice());
        Picasso.with(mContext).load(uploadCurrent.getpImage()).placeholder(R.mipmap.image_large).into(holder.imageView);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String rid = firebaseUser.getUid();
        if(uploadCurrent.getrID().equals(rid)){
            holder.txtremove.setVisibility(View.VISIBLE);
            holder.txtcart.setVisibility(View.GONE);
        }else {
            holder.txtremove.setVisibility(View.GONE);
            holder.txtcart.setVisibility(View.VISIBLE);
        }

    }


    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView txtname,txtprice,txtdes,txtremove,txtcart;
        ImageView imageView;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            txtname = itemView.findViewById(R.id.txtname);
            imageView = itemView.findViewById(R.id.imageView);
            txtprice = itemView.findViewById(R.id.txtprice);
            txtdes = itemView.findViewById(R.id.txtdes);
            txtremove = itemView.findViewById(R.id.txtremove);
            txtcart = itemView.findViewById(R.id.txtcart);
            txtcart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    firebaseAuthe= FirebaseAuth.getInstance();
                    String current_user_id = firebaseAuthe.getCurrentUser().getUid();
                    databaseReference2 = FirebaseDatabase.getInstance().getReference().child("User").child(current_user_id);
                    databaseReference2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Constant.uCardID = dataSnapshot.child("uCardID").getValue(String.class);
                            int position = getAdapterPosition();
                            Products uploadCurrent = mUploads.get(position);
                            Constant.pName = uploadCurrent.getpName();
                            Constant.pPrice = uploadCurrent.getpPrice();
                            Constant.pImage = uploadCurrent.getpImage();
                            Constant.pDes = uploadCurrent.getpDes();
                            Constant.pID = uploadCurrent.getpID();
                            Constant.rID = uploadCurrent.getrID();
                            mContext.startActivity(new Intent(mContext, DetailActivity.class));
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            });

        }
    }
}
