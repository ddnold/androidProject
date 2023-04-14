package com.example.overgrowthapp.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.overgrowthapp.R;
import com.example.overgrowthapp.ui.PlantDatabaseHelper;
import com.example.overgrowthapp.ui.dashboard.detailAdapter;
import com.squareup.picasso.Picasso;

import java.io.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class plantDetailLocal extends AppCompatActivity {
    String extra;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_detail_personal);

        // Adding actionbar reference and enabling back button
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        // Setup recyclerView
        RecyclerView detailRecyclerView = findViewById(R.id.detailTable);
        detailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // disable scrolling inside recyclerView
        NestedScrollView nestedScrollView = findViewById(R.id.scrollView);
        nestedScrollView.setNestedScrollingEnabled(false);

        // init all custom views
        CalendarView endCalendarView = findViewById(R.id.calendarViewLocal);
        Calendar calendar = Calendar.getInstance();
        ImageView plantIMG = findViewById(R.id.plantIMG);
        TextView waterView = findViewById(R.id.water);
        TextView urlView = findViewById(R.id.url);
        TextView typeView = findViewById(R.id.typeLabel);
        TextView botanicalView = findViewById(R.id.botanicalLabel);
        Bundle extras = intent.getExtras();
        extra = intent.getStringExtra("CommonId");
        id = intent.getIntExtra("id",0);
        this.setTitle(extra);
        if (extras != null) {
            ArrayList<Pair<String, String>> dataList = new ArrayList<>();
            for (String key : extras.keySet()) {
                if (Objects.equals(key, "CommonId")) {
                    continue;
                }
                if (Objects.equals(key, "BotanicalID")) {
                    if (extras.getString(key) != null) {
                        botanicalView.setText(extras.getString(key));
                    }
                    continue;
                }
                if (Objects.equals(key, "Water")) {
                    if (extras.getString(key) != null) {
                        waterView.setText(extras.getString(key));
                    }
                    continue;
                }
                if (Objects.equals(key, "url")) {
                    if (extras.getString(key) != null) {
                        urlView.setText("Learn more at: " + extras.getString(key));
                        urlView.setMovementMethod(LinkMovementMethod.getInstance());
                    }
                    continue;
                }
                if (Objects.equals(key, "Type")) {
                    if (extras.getString(key) != null) {
                        typeView.setText(extras.getString(key));
                    }
                    continue;
                }
                if (Objects.equals(key, "imgSrc")) {
                    if (extras.getString(key) != null) {
                        Picasso.get().load(extras.getString(key)).transform(new CropCircleTransformation()).into(plantIMG);
                    }
                    continue;
                }
                if (Objects.equals(key, "Family")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                    }
                }
                if (Objects.equals(key, "SoilPH")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                    }
                }
                if (Objects.equals(key, "Bloom Time")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                    }
                }
                if (Objects.equals(key, "Hardiness Zone")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                    }
                }
                if (Objects.equals(key, "Sun Exposure")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                    }
                }
                if (Objects.equals(key, "Native Area")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                    }
                }
                if (Objects.equals(key, "Color")){
                    continue; // Skipping for now because of bug in DB.
                }
                if (Objects.equals(key, "Size")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                    }
                }
                if (Objects.equals(key, "Toxicity")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                    }
                }
                if (Objects.equals(key, "Bloom Color")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                    }
                }
                if (Objects.equals(key, "Growing Time")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                    }
                }
                if (Objects.equals(key, "timer_end")){
                    Long value = extras.getLong(key);
                    Long startTime = extras.getLong("timer_start");
                    calendar.setTimeInMillis(startTime);
                    endCalendarView.setDate(calendar.getTimeInMillis());

                    if(value.equals(startTime)){
                        endCalendarView.setVisibility(View.GONE);
                    }
                    if(System.currentTimeMillis()<=value){
                        endCalendarView.setBackgroundColor(Color.parseColor("#FF4444"));
                    }
                }
                continue;
            }

            detailAdapter adapter = new detailAdapter(dataList, this);
            detailRecyclerView.setAdapter(adapter);
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

    public void removeBtn(View view){
        PlantDatabaseHelper dbHelper = new PlantDatabaseHelper(this);
        dbHelper.deletePlant(id);
        Toast.makeText(getApplicationContext(), "Removed plant from my list!", Toast.LENGTH_SHORT).show();
    }

}
