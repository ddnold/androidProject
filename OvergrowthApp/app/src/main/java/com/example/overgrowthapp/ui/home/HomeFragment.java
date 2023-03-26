package com.example.overgrowthapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.overgrowthapp.R;
import com.example.overgrowthapp.ui.PlantDatabaseHelper;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    localAdapter localAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        PlantDatabaseHelper dbHelper = new PlantDatabaseHelper(getContext());
        ArrayList<plantPersonal> localList = dbHelper.getAllPlants();
        recyclerView = view.findViewById(R.id.localTable);
        recyclerView.setHasFixedSize(true);

        // Set up layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        localAdapter = new localAdapter(view.getContext(), localList);
        recyclerView.setAdapter(localAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}