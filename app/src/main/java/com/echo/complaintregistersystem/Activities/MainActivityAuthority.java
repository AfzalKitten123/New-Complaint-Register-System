package com.echo.complaintregistersystem.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;

import com.echo.complaintregistersystem.Fragments.ResolvedAuthorityFragment;
import com.echo.complaintregistersystem.Fragments.UnresolvedAuthorityFragment;
import com.echo.complaintregistersystem.R;

public class MainActivityAuthority extends AppCompatActivity {
    public static final  String ip = "http://10.192.57.238:8000/CS/";
    FragmentTabHost outTabHost;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_authority);
        sharedPreferences = getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE);

        outTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        outTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        outTabHost.addTab(outTabHost.newTabSpec("tab1").setIndicator("Resolved"), ResolvedAuthorityFragment.class, null);
        outTabHost.addTab(outTabHost.newTabSpec("tab2").setIndicator("UnResolved"), UnresolvedAuthorityFragment.class, null);
    }
}
