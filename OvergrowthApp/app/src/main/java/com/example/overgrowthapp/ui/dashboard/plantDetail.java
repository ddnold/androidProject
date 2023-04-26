package com.example.overgrowthapp.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
import com.example.overgrowthapp.ui.home.plantPersonal;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class plantDetail extends AppCompatActivity {
    plantPersonal newPlant;
    EditText timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_detail);

        // Adding actionbar reference and enabling back button
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        timer = findViewById(R.id.timerET);
        Intent intent = getIntent();
        // Setup recyclerView
        RecyclerView detailRecyclerView = findViewById(R.id.detailTable);
        detailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // disable scrolling inside recyclerView
        NestedScrollView nestedScrollView = findViewById(R.id.scrollView);
        nestedScrollView.setNestedScrollingEnabled(false);

        // init all custom views
        ImageView plantIMG = findViewById(R.id.plantIMG);
        TextView waterView = findViewById(R.id.water);
        TextView urlView = findViewById(R.id.url);
        TextView typeView = findViewById(R.id.typeLabel);
        TextView botanicalView = findViewById(R.id.botanicalLabel);
        Bundle extras = intent.getExtras();
        String extra = intent.getStringExtra("CommonId");
        newPlant = new plantPersonal();
        this.setTitle(extra);
        if (extras != null) {
            ArrayList<Pair<String, String>> dataList = new ArrayList<>();
            for (String key : extras.keySet()) {
                if (Objects.equals(key, "CommonId")) {
                    newPlant.CommonId = extras.getString(key);
                    continue;
                }
                if (Objects.equals(key, "BotanicalID")) {
                    if (extras.getString(key) != null) {
                        botanicalView.setText(extras.getString(key));
                        newPlant.BotanicalID = extras.getString(key);
                    }
                    continue;
                }
                if (Objects.equals(key, "Water")) {
                    if (extras.getString(key) != null) {
                        waterView.setText(extras.getString(key));
                        newPlant.Water = extras.getString(key);
                    }
                    continue;
                }
                if (Objects.equals(key, "url")) {
                    if (extras.getString(key) != null) {
                        urlView.setText("Learn more at: " + extras.getString(key));
                        urlView.setMovementMethod(LinkMovementMethod.getInstance());
                        newPlant.url = extras.getString(key);
                    }
                    continue;
                }
                if (Objects.equals(key, "Type")) {
                    if (extras.getString(key) != null) {
                        typeView.setText(extras.getString(key));
                        newPlant.Type = extras.getString(key);
                    }
                    continue;
                }
                if (Objects.equals(key, "imgSrc")) {
                    if (extras.getString(key) != null) {
                        Picasso.get().load(extras.getString(key)).transform(new CropCircleTransformation()).into(plantIMG);
                        newPlant.imgSrc = extras.getString(key);
                    }
                    continue;
                }
                if (Objects.equals(key, "Family")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                        newPlant.Family = value;
                    }
                }
                if (Objects.equals(key, "SoilPH")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                        newPlant.SoilPH = value;
                    }
                }
                if (Objects.equals(key, "Soil")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                        newPlant.Soil = value;
                    }
                }
                if (Objects.equals(key, "Bloom Time")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                        newPlant.BloomTime = value;
                    }
                }
                if (Objects.equals(key, "Hardiness Zone")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                        newPlant.HardinessZone = value;
                    }
                }
                if (Objects.equals(key, "Sun Exposure")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                        newPlant.Sun = value;
                    }
                }
                if (Objects.equals(key, "Native Area")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                        newPlant.NativeArea = value;
                    }
                }
                if (Objects.equals(key, "Color")){
                    newPlant.Color = extras.getString(key);
                    continue; // Skipping for now because of bug in DB.
                }
                if (Objects.equals(key, "Size")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                        newPlant.Size = value;
                    }
                }
                if (Objects.equals(key, "Toxicity")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                        newPlant.Toxicity = value;
                    }
                }
                if (Objects.equals(key, "Bloom Color")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                        newPlant.BloomColor = value;
                    }
                }
                if (Objects.equals(key, "Growing Time")){
                    String value = extras.getString(key);
                    if (value != null) {
                        dataList.add(new Pair<>(key, value));
                        newPlant.GrowingTime = value;
                    }
                }

            }

            Map<String, Integer> categoryIcons = new HashMap<>();
            categoryIcons.put("Sun Exposure", R.drawable.sun);
            categoryIcons.put("Growing Time", R.drawable.growingtime);
            categoryIcons.put("Bloom Color", R.drawable.bloomcolor);
            categoryIcons.put("Toxicity", R.drawable.toxicity);
            categoryIcons.put("Size", R.drawable.size);
            categoryIcons.put("Bloom Time", R.drawable.bloomtime);
            categoryIcons.put("Native Area", R.drawable.nativearea);
            categoryIcons.put("Hardiness Zone", R.drawable.hardiness);
            categoryIcons.put("SoilPH", R.drawable.ph);
            categoryIcons.put("Family", R.drawable.family);
            categoryIcons.put("Soil", R.drawable.soil);


            detailAdapter adapter = new detailAdapter(dataList, categoryIcons, this);

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

    public void addBtn(View view){
        if(timer.getText().toString().isEmpty()){
            newPlant.timerEnd = 0L;
            newPlant.timerLength = 0;
        }
        else{
            newPlant.timerLength = Integer.parseInt(timer.getText().toString())*86400000;
            newPlant.timerEnd = newPlant.timerLength+System.currentTimeMillis();
        }

        PlantDatabaseHelper dbHelper = new PlantDatabaseHelper(this);
        boolean success = dbHelper.addPlant(newPlant);
        if (success) {
            Toast.makeText(getApplicationContext(), "Plant added to my list!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Failed to add plant to my list!", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
