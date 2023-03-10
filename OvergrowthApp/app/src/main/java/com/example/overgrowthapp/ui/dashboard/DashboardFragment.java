package com.example.overgrowthapp.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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
    ArrayList<user> list;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard,container,false);
        recyclerView = view.findViewById(R.id.plantList);
        database = FirebaseDatabase.getInstance().getReference();
        Query query = database.orderByChild("index").limitToFirst(10);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();
        myAdapter = new myAdapter(view.getContext(),list);
        recyclerView.setAdapter(myAdapter);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    user User = dataSnapshot.getValue(user.class);
                    list.add(User);

                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // Check if the last item is about to be shown
                if (!recyclerView.canScrollVertically(1)) {
                    // Get the last item in the list to use as the starting point for the next query
                    user lastUser = list.get(list.size() - 1);
                    String lastUserId = lastUser.getIndex();

                    // Update the query to fetch the next 10 items
                    Query nextQuery = database.orderByChild("index").startAfter(lastUserId).limitToFirst(10);
                    nextQuery.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int sizeBefore = list.size();

                            for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                                user User = dataSnapshot.getValue(user.class);
                                list.add(User);

                            }

                            int sizeAfter = list.size();
                            myAdapter.notifyItemRangeInserted(sizeBefore, sizeAfter - sizeBefore);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
