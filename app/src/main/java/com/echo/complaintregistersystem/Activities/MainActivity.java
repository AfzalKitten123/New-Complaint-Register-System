package com.echo.complaintregistersystem.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.echo.complaintregistersystem.R;

public class MainActivity extends AppCompatActivity {
    public static final  String ip = "http://10.192.57.238:8000/CS/";
    FragmentTabHost outTabHost;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE);
        outTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        outTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        outTabHost.addTab(outTabHost.newTabSpec("tab1").setIndicator("Individual"), MainIndividualFragment.class, null);
        if(!sharedPreferences.getString("TYPE_OF_USER", "Student").equalsIgnoreCase("Faculty"))
            outTabHost.addTab(outTabHost.newTabSpec("tab2").setIndicator("Resident"), MainResidentFragment.class, null);
        outTabHost.addTab(outTabHost.newTabSpec("tab3").setIndicator("Institute"), MainInstituteFragment.class, null);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CreateComplaint.class);
                startActivity(i);
            }
        });
    }
}
