package com.echo.complaintregistersystem;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.echo.complaintregistersystem.Activities.CreateComplaint;


public class ComplaintInfo extends AppCompatActivity {

    private TextView title,description,createdOn,resolvedOn,byName,category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_info);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ComplaintInfo.this, CreateComplaint.class);
                startActivity(i);
            }
        });

        Intent i = getIntent();
        title = (TextView)findViewById(R.id.title_tv);
        title.setText(i.getStringExtra("title"));

        description = (TextView)findViewById(R.id.textView);
        description.setText(i.getStringExtra("description"));

        createdOn = (TextView)findViewById(R.id.createdDate_tv);
        createdOn.setText(i.getStringExtra("date_created"));

        resolvedOn = (TextView)findViewById(R.id.resolvedDate_tv);
        resolvedOn.setText(i.getStringExtra("date_resolved"));

        byName = (TextView)findViewById(R.id.Lodger_tv);
        byName.setText(i.getStringExtra("byname"));

        category = (TextView)findViewById(R.id.category_tv);
        category.setText(i.getStringExtra("category"));

    }






}
