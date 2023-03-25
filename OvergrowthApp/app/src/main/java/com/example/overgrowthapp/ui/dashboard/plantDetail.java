package com.example.overgrowthapp.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Pair;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.overgrowthapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class plantDetail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_detail);

        // Adding actionbar reference and enabling back button
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        RecyclerView detailRecyclerView = findViewById(R.id.detailTable);
        detailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ImageView plantIMG = findViewById(R.id.plantIMG);
        TextView waterView = findViewById(R.id.water);
        TextView urlView = findViewById(R.id.url);
        Bundle extras = intent.getExtras();
        String extra = intent.getStringExtra("CommonId");
        this.setTitle(extra);
        if (extras != null) {
            ArrayList<Pair<String, String>> dataList = new ArrayList<>();
            for (String key : extras.keySet()) {
                if(extras.getString(key) == ""){
                    continue;
                }
                if (Objects.equals(key, "Water")) {
                    waterView.setText(extras.getString(key));
                    continue;
                }
                if (Objects.equals(key, "url")) {
                    urlView.setText("Learn more at: " + extras.getString(key));
                    urlView.setMovementMethod(LinkMovementMethod.getInstance());

                    continue;
                }
                if (Objects.equals(key, "imgSrc")) {
                    Picasso.get().load(extras.getString(key)).transform(new CropCircleTransformation()).into(plantIMG);
                    continue;
                }
                String value = extras.getString(key);
                dataList.add(new Pair<>(key, value));
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

}
