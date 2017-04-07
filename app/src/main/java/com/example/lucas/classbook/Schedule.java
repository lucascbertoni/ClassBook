package com.example.lucas.classbook;

import android.content.Intent;
import android.os.Bundle;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class Schedule extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        // Set an array of possible timeslots for creating TextViews
        final int[] timeSlots = {
                R.id.view0000, R.id.view0100, R.id.view0200, R.id.view0300, R.id.view0400,
                R.id.view0500, R.id.view0600, R.id.view0700, R.id.view0800, R.id.view0900,
                R.id.view1000, R.id.view1100, R.id.view1200, R.id.view1300, R.id.view1400,
                R.id.view1500, R.id.view1600, R.id.view1700, R.id.view1800, R.id.view1900,
                R.id.view2000, R.id.view2100, R.id.view2200, R.id.view2300
            };

        // Toolbar stuff
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // This was our attept at getting an external FAB library working for the Schedule screen.
        // It, uhh, didn't end up working out.

//        // Custom FAB library stuff
//        FloatingActionMenu fabMenu = (FloatingActionMenu) findViewById(R.id.fabMenu);
//        FloatingActionButton fabCourse = (FloatingActionButton) findViewById(R.id.fabCourse);
//        FloatingActionButton fabActivity = (FloatingActionButton) findViewById(R.id.fabActivity);
//
//        fabMenu.addMenuButton(fabCourse);
//        fabMenu.addMenuButton(fabActivity);
//
//        fabMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Find out how to open this menu to display the FABs
//            }
//        });
//
//        fabCourse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Schedule.this, AddCourse.class);
//                startActivity(intent);
//
//            }
//        });
//
//        fabActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Schedule.this, AddEvent.class);
//                startActivity(intent);
//
//            }
//        });


        // Test code to showcase the adding and extracting from file capabilities

        String FILENAME = "schedule.txt";
        String string = "CSI2120|09|24|2017|13:30|14:00";

        File path = getFilesDir();

        File file = new File(path, "schedule.txt");

        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            stream.write(("CSI2120|09|24|2017|1330|1400" +
                    "\n" + "CSI3140|10|20|2018|1230|1600" +
                    "\n" + "CSI5050|10|20|2018|1230|1600").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Nav drawer stuff
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Load any activities for today

        displayDailyActivities(timeSlots, readToday(file));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            //Toast.makeText(Schedule.this, "camera", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), AddEvent.class);
            startActivity(i);
        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(getApplicationContext(), AddCourse.class);
            startActivity(i);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // This is where we read from the Schedule file to get the daily activities
    public void displayDailyActivities(int[] timeSlots, String[][] infoFile){


        String[] todaysActivities = {"CSI2120", "01", "14", "2017", "1300", "1500"};

        // This was an attempt to read through the file that saves all the courses/activities
        // and populate the schedule with it.

//        // Read the infoFile Map
//        System.out.println("InfoFile[0] length: " + infoFile[0].length);
//        for(int i=0; i < infoFile[0].length-1; i++){
//
//            TextView newActivity = new TextView(Schedule.this);
//            System.out.println("Loop counter: " + i);
//            System.out.println("InfoFile[1][" + i + "]: " + infoFile[1][i]);
//            int startTime = Integer.valueOf(infoFile[i][4]);
//            int startTimeHour = startTime / 100;
//            int startTimeMin = startTime % 100;
//
//            RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
//
//            newActivity.setText(infoFile[i][0]);
//            newActivity.setBackgroundColor(0x80002000); // Not burgundy, but it will do for now
//            newActivity.setWidth(400);
//            newActivity.setHeight(100);
//
//            relativeParams.addRule(RelativeLayout.BELOW, timeSlots[startTimeHour + 1]);
//            newActivity.setLayoutParams(relativeParams);
//
//            relativeParams.setMargins(140, startTimeMin-3, 0, 0);
//            newActivity.setLayoutParams(relativeParams);
//
//            // Add to the screen
//            RelativeLayout schedule = (RelativeLayout) findViewById(R.id.scheduleView);
//            schedule.addView(newActivity);
//
//        }

        // Default tester method
        for(int i=0; i < todaysActivities.length; i=i+6){

            // Get the time slot the TextView is supposed to appear under (the one before it, actually)
            int startTime = Integer.valueOf(todaysActivities[i+4]);
            int startTimeHour = startTime / 100;
            int startTimeMin = startTime % 100;

            // Make the TextView and add it to the Schedule
            TextView newActivity = new TextView(Schedule.this);
            RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            newActivity.setText(todaysActivities[i]);
            newActivity.setBackgroundColor(0x80002000); // Not burgundy, but it will do for now
            newActivity.setWidth(400);
            newActivity.setHeight(100);


            relativeParams.addRule(RelativeLayout.BELOW, timeSlots[startTimeHour + 1]);
            newActivity.setLayoutParams(relativeParams);

            relativeParams.setMargins(140, startTimeMin-3, 0, 0);
            newActivity.setLayoutParams(relativeParams);

            // Add to the screen
            RelativeLayout schedule = (RelativeLayout) findViewById(R.id.scheduleView);
            schedule.addView(newActivity);

            // Make sure we're not going out of bounds
            if(i + 6 > todaysActivities.length){
                i = todaysActivities.length;
            }
            else{

            }
        }
    }

    public void displayActivitySection(Calendar current, int height, String title){
        //createActivityView();
    }

    // This returns an array that contains all the strings in the file
    public String[][] readToday(File file){

        int length = (int) file.length();

        byte[] bytes = new byte[length];

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            in.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String contents = new String(bytes);
        String[] lines = contents.split("[\\r\\n]+");
        String[][] entry = new String[lines.length][6];

        for (int i = 0 ; i< lines.length ; i++){

            for(int j = 0 ; j < 6 ; j++){
                String[] parts = lines[i].split("[|]");
                entry[i][j] = parts[j];

            }
        }

        String[] parts = contents.split("[|]");
        //testing code
//        for(int i = 0 ; i < lines.length ; i++){
//            Toast.makeText(Schedule.this, lines[i], Toast.LENGTH_LONG).show();
//        }
        //Toast.makeText(Schedule.this, entry[3][1], Toast.LENGTH_LONG).show();
        return entry;

    }

}
