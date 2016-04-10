package com.echo.complaintregistersystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.echo.complaintregistersystem.Activities.CreateComplaint;
import com.echo.complaintregistersystem.Activities.MainActivity;
import com.echo.complaintregistersystem.Adapters.CommentListAdapter;
import com.echo.complaintregistersystem.ListItems.CommentEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ComplaintInfo extends AppCompatActivity {

    private TextView title,description,createdOn,resolvedOn,byName,category; int id;
    private ListView listView;

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

        Button add_Comment = (Button)findViewById(R.id.button);
        add_Comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComplaintInfo.this,addComment_popup.class);
                startActivity(intent);
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
        resolvedOn.setText(i.getStringExtra("date_resolved").equalsIgnoreCase("null")?"Pending":i.getStringExtra("date_resolved"));

        byName = (TextView)findViewById(R.id.Lodger_tv);
        byName.setText(i.getStringExtra("byname"));

        category = (TextView)findViewById(R.id.category_tv);
        category.setText(i.getStringExtra("category"));

        id = i.getIntExtra("ID", 1);

        listView = (ListView)findViewById(R.id.listView_comments);
        final List<CommentEntry> commentEntries = new ArrayList<>();

        String url = MainActivity.ip+"getComments/"+id;
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        try {
                            JSONArray comments = response.getJSONArray("list_of_comments");
                            for(int i=0; i<comments.length(); i++)
                                commentEntries.add(new CommentEntry(comments.getJSONObject(i).getString("title"), comments.getJSONObject(i).getString("creator"), comments.getJSONObject(i).getString("date")));

                            CommentListAdapter adapter = new CommentListAdapter(ComplaintInfo.this, commentEntries);
                            listView.setAdapter(adapter);
                        } catch (JSONException e) {
                            Toast.makeText(ComplaintInfo.this, "JSONObjectException:\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ComplaintInfo.this, "ErrorResponse:\n"+error.getMessage(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(this).add(jsonRequest);

    }
}