package com.example.lucas.classbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class AddEvent extends AppCompatActivity {

    Button btnCreate;
    Spinner dropdownCourse;
    Spinner dropdownRepeat;
    EditText title;
    EditText description;
    DatePicker date;
    TimePicker time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Title
        title = (EditText) findViewById(R.id.editTextTitle);

        // Description
        description = (EditText) findViewById(R.id.editTextDescription);

        // Date
        date = (DatePicker) findViewById(R.id.datePicker);

        // Time
        time = (TimePicker) findViewById(R.id.timePicker);

        // Course selection spinner
        dropdownCourse = (Spinner)findViewById(R.id.spinnerCourses);
        String[] courses = new String[]{"Example1", "Example2"}; // Replace this with method that populates the spinner with existing courses
        ArrayAdapter<String> adapterCourses = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, courses);
        dropdownCourse.setAdapter(adapterCourses);

        // Repeat choices spinner
        dropdownRepeat = (Spinner) findViewById(R.id.spinnerRepeat);
        String[] repeatChoices = new String[]{"Daily", "Weekly", "Monthly"}; // Replace this with a method that allows user to customize
        ArrayAdapter<String> adapterRepeat = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, repeatChoices);
        dropdownRepeat.setAdapter(adapterRepeat);

        // Button stuff
        btnCreate = (Button) findViewById(R.id.buttonCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gatherInfo();
                // Go to next screen

            }
        });
    }

    public void gatherInfo(){

        // Get all the variables and prepare to put them into a file
        String titleText = title.getText().toString();                              // Title
        String descriptionText = description.getText().toString();                  // Description
        String dropdownCourseText = dropdownCourse.getSelectedItem().toString();    // Course selection spinner
        String dropdownRepeatText = dropdownRepeat.getSelectedItem().toString();    // Repeat selection spinner
        String datePickerText = getDateFromDatePicker(date);                        // Date
        String timePickerText = getTimeFromTimePicker(time);                        // Time

    }

    public String getDateFromDatePicker(DatePicker datePicker){
        String day = String.valueOf(datePicker.getDayOfMonth());
        String month = String.valueOf(datePicker.getMonth());
        String year =  String.valueOf(datePicker.getYear());

        return day + month + year;
    }

    public String getTimeFromTimePicker(TimePicker timePicker){
        String hour = String.valueOf(timePicker.getHour());
        String min = String.valueOf(timePicker.getMinute());

        return hour + min;
    }

}
