package com.example.overgrowthapp;

import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.overgrowthapp.databinding.ActivityMainBinding;

import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class MyCSVReader {
    public static void main(String[] args) {
        try {
            // Create a CSVParser object to read the CSV file
            CSVParser parser = new CSVParser(new FileReader("path/to/your/file.csv"), CSVFormat.DEFAULT);

            // Iterate over each record in the CSV file
            for (CSVRecord record : parser) {
                // Iterate over each field in the record and print its value
                for (String field : record) {
                    System.out.print(field + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        Menu menu = navView.getMenu();
        menu.findItem(R.id.navigation_home).setTitle("Your plants");

        menu.findItem(R.id.navigation_dashboard).setTitle("Browse plants");

        menu.findItem(R.id.navigation_notifications).setTitle("Notifications");

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}