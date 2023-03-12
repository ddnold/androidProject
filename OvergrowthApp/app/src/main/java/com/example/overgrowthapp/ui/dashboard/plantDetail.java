package com.example.overgrowthapp.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.overgrowthapp.R;

public class plantDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_detail);

        // Adding actionbar reference and enabling back button
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        String extra = intent.getStringExtra("BotanicalID");
        if(extra== ""){
            extra = intent.getStringExtra("CommonId");
        }
        this.setTitle(extra);
        if (extras.size() != 0) {
            for (String key: extras.keySet()) {
                Object value = extras.get(key);
                // Set title
                if (key == "BotanicalID"){
                    this.setTitle(R.layout.plant_detail);
                }
                else if(key == "CommonId"){
                    this.setTitle(value.toString());
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
