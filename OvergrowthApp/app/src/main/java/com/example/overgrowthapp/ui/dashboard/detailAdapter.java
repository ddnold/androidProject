package com.example.overgrowthapp.ui.dashboard;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.overgrowthapp.R;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.Map;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class detailAdapter extends RecyclerView.Adapter<detailAdapter.ViewHolder> {
    private ArrayList<Pair<String, String>> mDataList;
    private Context mContext;
    private Map<String, Integer> mCategoryIcons;


    public detailAdapter(ArrayList<Pair<String, String>> dataList, Map<String, Integer> categoryIcons, Context context) {
        mDataList = dataList;
        mCategoryIcons = categoryIcons;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pair<String, String> data = mDataList.get(position);

        holder.titleTextView.setText(data.first+":");
        holder.descTextView.setText(data.second);

        if (mCategoryIcons.containsKey(data.first)) {
            int iconResId = mCategoryIcons.get(data.first);
            holder.imgSrc.setImageResource(iconResId);
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descTextView;
        ImageView imgSrc;

        public ViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.categoryTV);
            descTextView = itemView.findViewById(R.id.detailTV);
            imgSrc = itemView.findViewById(R.id.detailIcon);
        }
    }
}

