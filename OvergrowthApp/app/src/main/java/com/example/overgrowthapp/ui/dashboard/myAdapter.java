package com.example.overgrowthapp.ui.dashboard;

import android.content.Context;
import android.content.Intent;
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

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {

    Context context;

    ArrayList<plant> list;

    public myAdapter(Context context, ArrayList<plant> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        plant user = list.get(position);
        holder.commonId.setText(user.getCommonID());
        holder.botanicalID.setText(user.getBotanicalID());
        Picasso.get().load(user.getImgSrc()).transform(new CropCircleTransformation()).into(holder.imgSrc);

        // add onClickListener to the item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start detail activity
                Intent intent = new Intent(context, plantDetail.class);


                intent.putExtra("CommonId", user.getCommonID());
                intent.putExtra("BotanicalID", user.getBotanicalID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder {

        TextView commonId, botanicalID;
        ImageView imgSrc;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            commonId = itemView.findViewById(R.id.tvCommonName);
            botanicalID = itemView.findViewById(R.id.tvBotanicalName);
            imgSrc = itemView.findViewById(R.id.plantImage);
        }
    }

    public void setList(ArrayList<plant> newList) {
        list = newList;
        notifyDataSetChanged();
    }
}
