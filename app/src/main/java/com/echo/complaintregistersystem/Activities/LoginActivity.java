package com.echo.complaintregistersystem.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.echo.complaintregistersystem.R;

import org.json.JSONException;
import org.json.JSONObject;
import java.net.CookieHandler;
import java.net.CookieManager;
public class LoginActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    CookieManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        manager = new CookieManager();
        CookieHandler.setDefault(manager);
        final TextView username = (TextView) findViewById(R.id.Username);
        final TextView password = (TextView) findViewById(R.id.password);
        final CheckBox rememberMe = (CheckBox) findViewById(R.id.remember_login);
        RadioGroup type = (RadioGroup) findViewById(R.id.radioGroup_type);
        final String[] typeS = {""};
        assert type != null;
        type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton_user:
                        typeS[0] = "user";
                        break;
                    case R.id.radioButton_authority:
                        typeS[0] = "authority";
                        break;
                }
            }
        });

        final String[] url = new String[1];
        sharedPreferences = getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if(sharedPreferences.getBoolean("ISLOGIN", false)){
            url[0] = MainActivity.ip + "loginUser/"+sharedPreferences.getString("USERNAME","cs1110200")+"/"+sharedPreferences.getString("PASSWORD", "john");
            JsonObjectRequest jsonRequest = new JsonObjectRequest
                    (Request.Method.GET, url[0], null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // the response is already constructed as a JSONObject!
                            try {
                                if (response.getBoolean("success")) {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else
                                    Toast.makeText(LoginActivity.this, "LogIn Unsuccessful", Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                Toast.makeText(LoginActivity.this, "JSONObjectException:\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(LoginActivity.this, "OnErrorResponse:\n" + error.getMessage(), Toast.LENGTH_LONG).show();
                            error.printStackTrace();
                        }
                    });
            Volley.newRequestQueue(LoginActivity.this).add(jsonRequest);
        }
        Button login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putString("typeS", typeS[0]);
                if(typeS[0].equalsIgnoreCase("user"))
                    url[0] = MainActivity.ip + "loginUser/" + username.getText().toString() + "/" + password.getText().toString() + "/";
                else
                    url[0] = MainActivity.ip + "loginAuthority/" + username.getText().toString() + "/" + password.getText().toString() + "/";

                Toast.makeText(LoginActivity.this, "Connecting to the server", Toast.LENGTH_SHORT).show();
                JsonObjectRequest jsonRequest = new JsonObjectRequest
                        (Request.Method.GET, url[0], null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // the response is already constructed as a JSONObject!
                                try {
                                    if (response.getBoolean("success")) {
                                        Toast.makeText(LoginActivity.this, "LogIn Successful", Toast.LENGTH_SHORT).show();
                                        JSONObject userDetails = response.getJSONObject("details");
                                        if (rememberMe.isChecked())
                                            editor.putBoolean("ISLOGIN", true);
                                        Intent intent;
                                        editor.putString("NAME", userDetails.getString("name"));
                                        editor.putString("EMAIL", userDetails.getString("email"));
                                        editor.putString("USERNAME", userDetails.getString("username"));
                                        editor.putString("PASSWORD", userDetails.getString("password"));
                                        editor.putString("CONTACT_NO", userDetails.getString("contact_number"));
                                        editor.putString("PRIMARY_ID", userDetails.getString("primary_id"));
                                        if(typeS[0].equalsIgnoreCase("user")) {
                                            editor.putString("TYPE_OF_USER", userDetails.getString("type_of_user"));
                                            editor.putString("RESIDENCY", userDetails.getString("residency"));
                                            editor.putString("ROOM_NO", userDetails.getString("room_no"));
                                            intent = new Intent(LoginActivity.this, MainActivity.class);

                                        }else{
                                            editor.putString("TYPE",userDetails.getString("type"));
                                            intent = new Intent(LoginActivity.this, MainActivityAuthority.class);
                                        }
                                        editor.commit();
                                        Toast.makeText(LoginActivity.this, "Name : " + userDetails.getString("name"), Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                    } else
                                        Toast.makeText(LoginActivity.this, "LogIn Unsuccessful", Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    Toast.makeText(LoginActivity.this, "JSONObjectException:\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(LoginActivity.this, "OnErrorResponse:\n" + error.getMessage(), Toast.LENGTH_LONG).show();
                                error.printStackTrace();
                            }
                        });
                Volley.newRequestQueue(LoginActivity.this).add(jsonRequest);
            }
        });
    }
}