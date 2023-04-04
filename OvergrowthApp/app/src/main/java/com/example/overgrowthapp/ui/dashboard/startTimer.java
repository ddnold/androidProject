package com.example.overgrowthapp.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.overgrowthapp.R;

import java.util.Calendar;

public class startTimer extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datepicker);

        // Adding actionbar reference and enabling back button
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        CalendarView startCalendarView = findViewById(R.id.calendarViewStart);
        CalendarView endCalendarView = findViewById(R.id.calendarViewEnd);
        Calendar calendar = Calendar.getInstance();
        // START
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        startCalendarView.setMinDate(calendar.getTimeInMillis()); // set the minimum date to week ago
        calendar.add(Calendar.WEEK_OF_YEAR, 1); // add a week to the calendar
        startCalendarView.setMaxDate(calendar.getTimeInMillis()); // set the maximum date to today

        //END
        endCalendarView.setMinDate(calendar.getTimeInMillis()); // set the minimum date to week ago
        calendar.add(Calendar.WEEK_OF_YEAR, 4); // add a week to the calendar
        endCalendarView.setMaxDate(calendar.getTimeInMillis()); // set the maximum date to today

        Button saveButton = findViewById(R.id.submitBTN);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the selected start and end times
                long startInMillis = startCalendarView.getDate();
                long endInMillis = endCalendarView.getDate();

                // Create a new intent and put the start and end times as extras
                Intent intent = new Intent();
                intent.putExtra("start_time", startInMillis);
                intent.putExtra("end_time", endInMillis);
                setResult(RESULT_OK, intent);

                // Finish the activity and return to the original detail view
                finish();
            }
        });

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
