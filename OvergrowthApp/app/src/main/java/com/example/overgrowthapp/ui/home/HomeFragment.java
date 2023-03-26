package com.example.overgrowthapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.overgrowthapp.R;
import com.example.overgrowthapp.databinding.FragmentHomeBinding;
import com.example.overgrowthapp.ui.PlantDatabaseHelper;
import com.example.overgrowthapp.ui.dashboard.myAdapter;
import com.example.overgrowthapp.ui.dashboard.plant;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    myAdapter myAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        PlantDatabaseHelper dbHelper = new PlantDatabaseHelper(getContext());
        ArrayList<plant> localList = dbHelper.getAllPlants();
        recyclerView = view.findViewById(R.id.localTable);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        myAdapter = new myAdapter(view.getContext(), localList);
        recyclerView.setAdapter(myAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}