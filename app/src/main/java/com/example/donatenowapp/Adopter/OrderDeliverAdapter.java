package com.example.donatenowapp.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.donatenowapp.Model.AddToCart;
import com.example.donatenowapp.R;

import java.util.List;

public class OrderDeliverAdapter extends RecyclerView.Adapter<OrderDeliverAdapter.ImageViewHolder> {
    private Context mContext;
    private List<AddToCart> mUploads;

    public OrderDeliverAdapter(Context context, List<AddToCart> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.order_deliver_list, parent, false);
        final ImageViewHolder imageViewHolder = new ImageViewHolder(v);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        AddToCart uploadCurrent = mUploads.get(position);
        holder.txtqty.setText(uploadCurrent.getpQuantity());
        holder.txtname.setText(uploadCurrent.getpName());
        holder.txtprice.setText("Rs. "+uploadCurrent.pPrice);

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView txtqty, txtname,txtprice;


        public ImageViewHolder(View itemView) {
            super(itemView);
            txtqty = itemView.findViewById(R.id.txtqty);
            txtname = itemView.findViewById(R.id.txtname);
            txtprice = itemView.findViewById(R.id.txtprice);




        }


    }

}