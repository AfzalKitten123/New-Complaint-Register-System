package com.echo.complaintregistersystem.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.echo.complaintregistersystem.MainActivity;
import com.echo.complaintregistersystem.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CreateComplaint extends AppCompatActivity {

    EditText title,description;
    Spinner category;
    RadioGroup levelchoice;
    String level,category_selected;
    ArrayAdapter<CharSequence> spinnerAdapter;
    Button create;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_complaint);

        title = (EditText)findViewById(R.id.title_et);
        description = (EditText)findViewById(R.id.description_et);

        create=(Button)findViewById(R.id.create_btn);

        levelchoice=(RadioGroup)findViewById(R.id.radioGroup);
        levelchoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.individual_btn:
                        level = "individualLevel";
                        break;
                    case R.id.resident_btn:
                        level = "hostelLevel";
                        break;
                    case R.id.institute_btn:
                        level = "instituteLevel";
                        break;

                }
        }});

        category_selected="";
        level="";
        category=(Spinner)findViewById(R.id.spinner1);
        spinnerAdapter= ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(spinnerAdapter);


        category.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        category_selected = parent.getItemAtPosition(position).toString();
                        Toast.makeText(CreateComplaint.this,"spinner selection done",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                        Toast.makeText(CreateComplaint.this,"spinner selection failed",Toast.LENGTH_SHORT).show();
                    }
                });

                sharedPreferences = getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(level.equals("") || category_selected.equals("")){
                    Toast.makeText(CreateComplaint.this,"select the level and category and then try again",Toast.LENGTH_SHORT).show();
                }else {

                    String url = null;
                    try {if(level.equalsIgnoreCase("instituteLevel")){
                        url = MainActivity.ip + "createNewComplaint/" + URLEncoder.encode(title.getText().toString(), "UTF-8") + "/" + URLEncoder.encode(description.getText().toString(), "UTF-8") + "/" + URLEncoder.encode(sharedPreferences.getString("ROOM_NO", ""), "UTF-8")
                            + "Institute/" + URLEncoder.encode(level, "UTF-8") + "/" + URLEncoder.encode(category_selected, "UTF-8") + "/" + URLEncoder.encode(sharedPreferences.getString("USERNAME", ""), "UTF-8");

                    }else {
                            url = MainActivity.ip + "createNewComplaint/" + URLEncoder.encode(title.getText().toString(), "UTF-8") + "/" + URLEncoder.encode(description.getText().toString(), "UTF-8") + "/" + URLEncoder.encode(sharedPreferences.getString("ROOM_NO", ""), "UTF-8")
                                + "/" + URLEncoder.encode(sharedPreferences.getString("RESIDENCY", ""), "UTF-8") + "/" + URLEncoder.encode(level, "UTF-8") + "/" + URLEncoder.encode(category_selected, "UTF-8") + "/" + URLEncoder.encode(sharedPreferences.getString("USERNAME", ""), "UTF-8");
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(CreateComplaint.this, " Lodging the Complaint of " + sharedPreferences.getString("NAME", "Afzal"), Toast.LENGTH_SHORT).show();

                    JsonObjectRequest jsonRequest = new JsonObjectRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // the response is already constructed as a JSONObject!
                                    try {
                                        boolean IsSuccess = response.getBoolean("success");
                                        if (IsSuccess) {
                                            Toast.makeText(CreateComplaint.this, "complaint lodged succesfully", Toast.LENGTH_SHORT).show();
                                            finish();
//                                            Intent myIntent = new Intent(CreateComplaint.this, MainActivity.class);
//                                            myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);// clear back stack
//                                            startActivity(myIntent);
//                                            finish();
//                                            return;
                                        } else {
                                            Toast.makeText(CreateComplaint.this, "complaint lodging failed try again", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        Toast.makeText(CreateComplaint.this, "JSONObjectException:\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(CreateComplaint.this, "ErrorResponse:\n" + error.getMessage(), Toast.LENGTH_LONG).show();
                                    error.printStackTrace();
                                }
                            });

                    Volley.newRequestQueue(CreateComplaint.this).add(jsonRequest);
                }
            }
        });
    }


    protected void Radio(View view){
        boolean isCheccked = ((RadioButton) view).isChecked();
        if(isCheccked){
            switch (view.getId()){
                case R.id.individual_btn:
                    level = "individualLevel";
                    break;
                case R.id.resident_btn:
                    level = "hostelLevel";
                    break;
                case R.id.institute_btn:
                    level = "instituteLevel";
                    break;

            }

        }
    }
}
