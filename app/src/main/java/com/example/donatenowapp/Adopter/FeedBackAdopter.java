package com.example.donatenowapp.Adopter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donatenowapp.Model.Rating;
import com.example.donatenowapp.R;

import java.util.List;

public class FeedBackAdopter extends RecyclerView.Adapter<FeedBackAdopter.RecyclerHolder> {
    private Context mContext;
    private List<Rating> mUploads;

    public FeedBackAdopter(Context mContext, List<Rating> mUploads) {
        this.mContext = mContext;
        this.mUploads = mUploads;
    }

    @Override
    public FeedBackAdopter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.review_list, parent, false);
        return new RecyclerHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedBackAdopter.RecyclerHolder holder, int position) {
        Rating uploadCurrent = mUploads.get(position);
        holder.txtmsg.setText(uploadCurrent.getrMsg());
        holder.txtname.setText(uploadCurrent.getuName());
        holder.txtdate.setText(uploadCurrent.getrDate());
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView txtmsg,txtname,txtdate;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            txtmsg = itemView.findViewById(R.id.txtmsg);
            txtname = itemView.findViewById(R.id.txtname);
            txtdate = itemView.findViewById(R.id.txtdate);

        }
    }
}
