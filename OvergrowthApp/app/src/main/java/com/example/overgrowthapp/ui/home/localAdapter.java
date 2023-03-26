package com.example.overgrowthapp.ui.home;

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
import com.example.overgrowthapp.ui.PlantDatabaseHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class localAdapter extends RecyclerView.Adapter<localAdapter.myViewHolder> {


    private Context context;
    private ArrayList<plantPersonal> list;
    private PlantDatabaseHelper dbHelper;

    public localAdapter(Context context, ArrayList<plantPersonal> list) {
        this.context = context;
        this.list = list;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        plantPersonal user = list.get(position);
        holder.commonId.setText(user.getCommonID());
        holder.botanicalID.setText(user.getBotanicalID());
        Picasso.get().load(user.getImgSrc()).transform(new CropCircleTransformation()).into(holder.imgSrc);

        // add onClickListener to the item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start detail activity
                Intent intent;
                intent = new Intent(context, plantDetailLocal.class);
                intent.putExtra("id", user.getId());
                intent.putExtra("CommonId", user.getCommonID());
                intent.putExtra("BotanicalID", user.getBotanicalID());
                intent.putExtra("Family", user.getFamily());
                intent.putExtra("SoilPH", user.getSoilPH());
                intent.putExtra("Soil", user.getSoil());
                intent.putExtra("Bloom Time", user.getBloomTime());
                intent.putExtra("Hardiness Zone", user.getHardinessZone());
                intent.putExtra("Type", user.getType());
                intent.putExtra("url", user.getUrl());
                intent.putExtra("Sun Exposure", user.getSun());
                intent.putExtra("Native Area", user.getNativeArea());
                intent.putExtra("Color", user.getColor());
                intent.putExtra("Size", user.getSize());
                intent.putExtra("Toxicity", user.getToxicity());
                intent.putExtra("Water", user.getWater());
                intent.putExtra("imgSrc", user.getImgSrc());
                intent.putExtra("Bloom Color", user.getBloomColor());
                intent.putExtra("Growing Time", user.getGrowingTime());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(ArrayList<plantPersonal> newList) {
        list = newList;
        notifyDataSetChanged();
    }

    public void updateList() {
        dbHelper = new PlantDatabaseHelper(context);
        list = dbHelper.getAllPlants();
        notifyDataSetChanged();
    }

    static class myViewHolder extends RecyclerView.ViewHolder {

        TextView commonId, botanicalID;
        ImageView imgSrc;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            commonId = itemView.findViewById(R.id.detailTV);
            botanicalID = itemView.findViewById(R.id.tvBotanicalName);
            imgSrc = itemView.findViewById(R.id.icon);
        }
    }
}