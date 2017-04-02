package com.example.lucas.classbook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AddEvent extends AppCompatActivity {

    Spinner dropdownCourse;
    Spinner dropdownRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    }

}
