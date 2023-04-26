package com.example.overgrowthapp.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.overgrowthapp.R;
import com.example.overgrowthapp.ui.PlantDatabaseHelper;
import com.example.overgrowthapp.ui.home.plantPersonal;

import java.util.ArrayList;

public class notAdapter extends RecyclerView.Adapter<notAdapter.myViewHolder> {
    private Context context;
    private ArrayList<plantPersonal> list;
    private PlantDatabaseHelper dbHelper;

    public notAdapter (Context context, ArrayList<plantPersonal> list, PlantDatabaseHelper dbHelper){
        this.context = context;
        this.list = list;
        this.dbHelper = dbHelper;
    }

    @NonNull
    @Override
    public notAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.plant_notification, parent, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull notAdapter.myViewHolder holder, int position) {
        plantPersonal user = list.get(position);
        holder.plantTV.setText(user.getCommonID());

        holder.waterBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(context, ?);


                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder {
        TextView plantTV;
        Button waterBTN;

        public myViewHolder(@NonNull View itemView){
            super(itemView);

            plantTV = itemView.findViewById(R.id.plantTV);
            waterBTN = itemView.findViewById(R.id.waterBTN);
        }
    }
}
