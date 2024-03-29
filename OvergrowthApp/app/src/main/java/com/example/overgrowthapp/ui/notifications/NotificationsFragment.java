package com.example.overgrowthapp.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.overgrowthapp.R;
import com.example.overgrowthapp.databinding.FragmentNotificationsBinding;
import com.example.overgrowthapp.ui.PlantDatabaseHelper;
import com.example.overgrowthapp.ui.home.plantPersonal;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    RecyclerView recyclerView;
    notAdapter notAdapter;
    PlantDatabaseHelper dbHelper;
    ArrayList<plantPersonal> localList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        dbHelper = new PlantDatabaseHelper(getContext());
        localList = dbHelper.getAllExpiredPlants();
        recyclerView = view.findViewById(R.id.announcmentTable);
        recyclerView.setHasFixedSize(true);

        // Setup Layout Manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        notAdapter = new notAdapter(view.getContext(), localList, dbHelper);
        recyclerView.setAdapter(notAdapter);

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}