package com.example.donatenowapp.Adopter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donatenowapp.Model.AddToCart;
import com.example.donatenowapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;


public class AddToCart_Adapter extends RecyclerView.Adapter<AddToCart_Adapter.ImageViewHolder> {
    private Context mContext;
    private List<AddToCart> mUploads;
    Dialog mDialog;
    private int i;
    DatabaseReference databaseReference2,ddatabase,ddatabase2;
    FirebaseAuth mAuth;
    String current_user_id;


    public AddToCart_Adapter(Context context, List<AddToCart> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.add_to_cart_list, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        AddToCart uploadCurrent = mUploads.get(position);
        holder.txtpname.setText(uploadCurrent.getpName());
        holder.txtprice.setText("PKR "+uploadCurrent.gettPrice());
        holder.txtqty.setText(uploadCurrent.getpQuantity());
        holder.txtsub.setText("PKR "+uploadCurrent.getpPrice());


        Picasso.with(mContext).load(uploadCurrent.getmURL()).placeholder(R.mipmap.ic_launcher).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder  {
        public TextView txtpname,txtprice,txtqty,txtsub;
        public Button  removes;
        public ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            txtpname = itemView.findViewById(R.id.textView);
            txtprice = itemView.findViewById(R.id.textView2);
            txtqty = itemView.findViewById(R.id.textView3);
            txtsub = itemView.findViewById(R.id.textView4);
            imageView = itemView.findViewById(R.id.imageView);
            removes = itemView.findViewById(R.id.edtremove);

            removes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mAuth = FirebaseAuth.getInstance();
                    current_user_id = mAuth.getCurrentUser().getUid();

                    databaseReference2 = FirebaseDatabase.getInstance().getReference().child("User").child(current_user_id);
                    databaseReference2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String uCardID = dataSnapshot.child("uCardID").getValue(String.class);
                            final int position = getAdapterPosition();
                            AddToCart selected = mUploads.get(position);
                            String id = selected.getKey();
                            String mKey = selected.mKey;
                            ddatabase = FirebaseDatabase.getInstance().getReference().child("AddToCart").child(current_user_id).child(uCardID);
                            ddatabase2 = FirebaseDatabase.getInstance().getReference().child("FoodDonate").child(mKey);
                            ddatabase2.removeValue();
                            ddatabase.child(id).removeValue();


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

