package com.echo.complaintregistersystem;

import android.content.Intent;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final  String ip = "htt10.192.57.238:8000/";
    FragmentTabHost outTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,CreateComplaint.class);
                startActivity(i);
            }
        });

        outTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        outTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        outTabHost.addTab(outTabHost.newTabSpec("tab1").setIndicator("Individual"), MainIndividualFragment.class, null);
        outTabHost.addTab(outTabHost.newTabSpec("tab2").setIndicator("Resident"), MainResidentFragment.class, null);
        outTabHost.addTab(outTabHost.newTabSpec("tab3").setIndicator("Institute"), MainInstituteFragment.class, null);
    }
}
