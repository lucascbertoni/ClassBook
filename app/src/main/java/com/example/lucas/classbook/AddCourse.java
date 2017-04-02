package com.example.lucas.classbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

public class AddCourse extends AddEvent {

    Button btnCreate;
    EditText title;
    EditText code;
    CheckBox lectures;
    CheckBox labs;
    CheckBox tutorials;
    DatePicker startDate;
    DatePicker endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create course button
        Button btnCreate = (Button) findViewById(R.id.buttonCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gatherInfo(title, code, lectures, labs, tutorials, startDate, endDate);
            }
        });
    }

    public static void gatherInfo(
            EditText title, EditText code, CheckBox lectures, CheckBox labs, CheckBox tutorials,
            DatePicker startDate, DatePicker endDate){

        // Get all the variables and prepare to put them into a file
        String titleText = title.getText().toString();              // Title
        String codeText = code.getText().toString();                // Code

        // The checkboxes need a method to convert the bool value to a string

        // Boolean lecturesCheck = lectures.isChecked();               // Lecture Checkbox
        // Boolean labsCheck = labs.isChecked();                       // Labs Checkbox
        // Boolean tutorialsCheck = tutorials.isChecked();             // Tutorials checkbox

        String startDateText = getDateFromDatePicker(startDate);    // Start Date
        String endDateText = getDateFromDatePicker(endDate);        // End Date

        // Add stuff to file here

    }

}
