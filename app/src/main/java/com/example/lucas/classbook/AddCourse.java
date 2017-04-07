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

    Button btnNext;
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

        // Go to next screen
        Button btnNext = (Button) findViewById(R.id.buttonCreate);
        
    }

    public void gatherInfo(){
        // Get all the variables and prepare to put them into a file
        String titleText = title.getText().toString();                  // Title
        String codeText = code.getText().toString();                    // Code
        String lecturesCheck = String.valueOf(lectures.isChecked());    // Lecture Checkbox
        String labsCheck = String.valueOf(labs.isChecked());            // Labs Checkbox
        String tutorialsCheck = String.valueOf(tutorials.isChecked());  // Tutorials checkbox
        String startDateText = getDateFromDatePicker(startDate);        // Start Date
        String endDateText = getDateFromDatePicker(endDate);            // End Date

        // Code to add stuff to file here
        // [Ask Sam what to do here]

    }

    public void nextScreen(){
        if(lectures.isChecked()){
            Intent intent = new Intent(AddCourse.this, AddCourseLectures.class);
            startActivity(intent);
        }
        else if(labs.isChecked()){
            // Intent intent = new Intent(AddCourse.this, AddCourseLabs.class);
            // startActivity(intent);
        }
        else if(tutorials.isChecked()){
            // Intent intent = new Intent(AddCourse.this, AddCourseTutorials.class);
            // startActivity(intent);
        }
        else{
            // Intent intent = new Intent(AddCourse.this, Schedule.class);
            // startActivity(intent);
        }

    }

}
