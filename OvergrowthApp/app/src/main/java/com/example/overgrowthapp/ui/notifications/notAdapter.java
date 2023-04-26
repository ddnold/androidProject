package com.example.overgrowthapp.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.overgrowthapp.R;
import com.example.overgrowthapp.ui.PlantDatabaseHelper;
import com.example.overgrowthapp.ui.home.plantPersonal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class notAdapter extends RecyclerView.Adapter<notAdapter.myViewHolder> {
    private Context context;
    private ArrayList<plantPersonal> list;
    private PlantDatabaseHelper dbHelper;

    public notAdapter(Context context, ArrayList<plantPersonal> list, PlantDatabaseHelper dbHelper) {
        this.context = context;
        this.list = list;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public notAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.plant_notification,parent,false);
        return new notAdapter.myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull notAdapter.myViewHolder holder, int position) {
        plantPersonal user = list.get(position);
        holder.commonId.setText(user.getCommonID());
        holder.botanicalID.setText(user.getBotanicalID());
        Picasso.get().load(user.getImgSrc()).transform(new CropCircleTransformation()).into(holder.imgSrc);

        // add onClickListener to the item view.
        holder.waterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start detail activity
                dbHelper.updateTimer(user.id);
                list.remove(user);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder {

        TextView commonId, botanicalID;
        ImageView imgSrc;
        Button waterBTN;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            commonId = itemView.findViewById(R.id.detailTV);
            botanicalID = itemView.findViewById(R.id.tvBotanicalName);
            imgSrc = itemView.findViewById(R.id.icon);
            waterBTN = itemView.findViewById(R.id.waterBTN);
        }
    }
}
