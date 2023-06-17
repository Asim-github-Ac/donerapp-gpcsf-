package com.example.donatenowapp.Adopter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.donatenowapp.Activities.OrderDeliveryActivity;
import com.example.donatenowapp.Model.Order;
import com.example.donatenowapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ShowOrderAdapter extends RecyclerView.Adapter<ShowOrderAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Order> mUploads;
    Dialog mDialog;
    DatabaseReference databaseReference;
    public static String namees ="";
    public static  String  phone="";
    public ShowOrderAdapter(Context context, List<Order> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.customer_order_list, parent, false);
        final ImageViewHolder imageViewHolder = new ImageViewHolder(v);
        //Dialog Frame call
        mDialog = new Dialog(mContext);
        mDialog.setContentView(R.layout.dialog_delete);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Order uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getoName().substring(0,1));
        holder.textViewNames.setText("Name: "+uploadCurrent.getoName());
        holder.textViewcellno.setText("Contact: "+uploadCurrent.getoPhone());
        holder.textViewAddress.setText("Address: "+uploadCurrent.getoAddress());



    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName, textViewNames,textViewcellno, textViewAddress;


        public ImageViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textView6);
            textViewNames = itemView.findViewById(R.id.textView9);
            textViewcellno = itemView.findViewById(R.id.textView11);
            textViewAddress  = itemView.findViewById(R.id.textView12);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Order selected = mUploads.get(position);
                    final String cardID = selected.getCardID();
                    final String uID = selected.getuID();
                    namees = selected.getoName();
                    phone = selected.getoPhone();
                    Intent profileIntent = new Intent(mContext, OrderDeliveryActivity.class);
                    profileIntent.putExtra("cardID", cardID);
                    profileIntent.putExtra("uID", uID);
                    mContext.startActivity(profileIntent);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    TextView cancel = mDialog.findViewById(R.id.btncancel);
                    TextView delete = mDialog.findViewById(R.id.btndelete);
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDialog.dismiss();
                        }
                    });
                    final int position = getAdapterPosition();
                    Order selected = mUploads.get(position);
                    final String id = selected.getoKey();
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("CustomerOrder");
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            databaseReference.child(id).removeValue();
                            Toast.makeText(mContext, "Item deleted", Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }
                    });
                    mDialog.show();
                    return false;
                }
            });



        }


    }

}