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
        this.setTitle("Plant Planner");
        // Adding actionbar reference and enabling back button
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        CalendarView startCalendarView = findViewById(R.id.calendarViewStart);
        CalendarView endCalendarView = findViewById(R.id.calendarViewEnd);
        Calendar calendar = Calendar.getInstance();
        // START
        startCalendarView.setMaxDate(calendar.getTimeInMillis()); // set the maximum date to today
        calendar.add(Calendar.WEEK_OF_YEAR, -1);
        startCalendarView.setMinDate(calendar.getTimeInMillis()); // set the minimum date to week ago

        //END
        calendar = Calendar.getInstance();
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

                Calendar startCalendar = Calendar.getInstance();
                startCalendar.setTimeInMillis(startInMillis);
                startCalendar.set(Calendar.HOUR_OF_DAY, 0); // set the hour to 0
                startCalendar.set(Calendar.MINUTE, 0); // set the minute to 0
                startCalendar.set(Calendar.SECOND, 0); // set the seconds to 0

                Calendar endCalendar = Calendar.getInstance();
                endCalendar.setTimeInMillis(endInMillis);
                endCalendar.set(Calendar.HOUR_OF_DAY, 23); // set the hour to 23
                endCalendar.set(Calendar.MINUTE, 59); // set the minute to 59
                endCalendar.set(Calendar.SECOND, 59); // set the seconds to 59
                long startTimeInMillis = startCalendar.getTimeInMillis();
                long endTimeInMillis = endCalendar.getTimeInMillis();

                // Create a new intent and put the start and end times as extras
                Intent intent = new Intent();
                intent.putExtra("start_time", startTimeInMillis);
                intent.putExtra("end_time", endTimeInMillis);
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
