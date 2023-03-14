package com.example.overgrowthapp.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.overgrowthapp.R;
import com.squareup.picasso.Picasso;

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
        TableLayout tableLayout = findViewById(R.id.plantTable);
        ImageView plantIMG = findViewById(R.id.plantIMG);
        TextView waterView = findViewById(R.id.water);
        TextView urlView = findViewById(R.id.url);
        Bundle extras = intent.getExtras();
        String extra = intent.getStringExtra("CommonId");
        this.setTitle(extra);
        if (extras.size() != 0) {
            for (String key: extras.keySet()) {
                String value = extras.getString(key);
                if(value != null && value != "Null") {
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
                    TableRow row1 = new TableRow(this);
                    TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                    TextView col1row1 = new TextView(this);
                    col1row1.setLayoutParams(params);
                    col1row1.setText(key + ":");
                    col1row1.setTextColor(getResources().getColor(R.color.black));
                    col1row1.setTextSize(30);
                    row1.addView(col1row1);

                    TextView col2row1 = new TextView(this);
                    col2row1.setLayoutParams(params);
                    col2row1.setText(value);
                    col2row1.setTextColor(getResources().getColor(R.color.black));
                    col2row1.setTextSize(15);
                    row1.addView(col2row1);
                    tableLayout.addView(row1);
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
