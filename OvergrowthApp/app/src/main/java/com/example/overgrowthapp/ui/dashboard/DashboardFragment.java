package com.example.overgrowthapp.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.overgrowthapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference database;
    myAdapter myAdapter;
    ArrayList<plant> list;
    boolean isLoading = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = view.findViewById(R.id.plantList);
        database = FirebaseDatabase.getInstance().getReference();
        list = new ArrayList<>();

        Query query = database.orderByChild("CommonId").limitToFirst(5);
        createQuery(query);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        myAdapter = new myAdapter(view.getContext(), list);
        recyclerView.setAdapter(myAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // Check if the last item is about to be shown and isLoading is false
                if (!recyclerView.canScrollVertically(1) && !isLoading && list.size() != 0) {
                    isLoading = true;

                    // Get the last item in the list to use as the starting point for the next query
                    plant lastUser = list.get(list.size() - 1);
                    String lastUserId = lastUser.getCommonID();

                    // Update the query to fetch the next 3 items
                    Query nextQuery = database.orderByChild("CommonId").startAfter(lastUserId).limitToFirst(5);
                    nextQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int sizeBefore = list.size();

                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                plant User = dataSnapshot.getValue(plant.class);
                                list.add(User);

                            }

                            int sizeAfter = list.size();
                            myAdapter.notifyItemRangeInserted(sizeBefore, sizeAfter - sizeBefore);
                            isLoading = false;
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            isLoading = false;
                        }
                    });
                }
            }
        });

        // Get a reference to the search bar EditText
        EditText searchBar = view.findViewById(R.id.search_bar);
        Button searchBTN = view.findViewById(R.id.search_button);

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void createQuery(Query query) {

        query.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    plant User = dataSnapshot.getValue(plant.class);
                    list.add(User);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}

