package com.echo.complaintregistersystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.echo.complaintregistersystem.Activities.CreateComplaint;
import com.echo.complaintregistersystem.Activities.MainIndividualFragment;
import com.echo.complaintregistersystem.Activities.MainInstituteFragment;
import com.echo.complaintregistersystem.Activities.MainResidentFragment;
import com.echo.complaintregistersystem.Fragments.ResolvedIndividualFragment;

public class MainActivity extends AppCompatActivity {
    public static final  String ip = "http://10.192.57.238:8000/CS/";
    FragmentTabHost outTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        outTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        outTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        outTabHost.addTab(outTabHost.newTabSpec("tab1").setIndicator("Individual"), MainIndividualFragment.class, null);
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
