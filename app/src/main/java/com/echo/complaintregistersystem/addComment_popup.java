package com.echo.complaintregistersystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.echo.complaintregistersystem.Activities.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Hrushi pc on 10/04/2016.
 */
public class addComment_popup extends Activity{
    int user_id, complaint_id;
    @Override
    protected  void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adpopup);

        Intent i = getIntent();
        complaint_id = i.getIntExtra("id", -1);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.85),(int)(height));

        final EditText comment = (EditText)findViewById(R.id.comment);
        Button done = (Button)findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE);
                user_id = Integer.parseInt(sharedPreferences.getString("PRIMARY_ID", "0"));
                String url = null;
                try {
                    url = MainActivity.ip + "postComment/"+ user_id + "/" + complaint_id + "/" + URLEncoder.encode(comment.getText().toString(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    Toast.makeText(addComment_popup.this,"@encoding addcoment" + e.getMessage() ,Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                        Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getBoolean("success")){
                                Toast.makeText(addComment_popup.this,"comment posted",Toast.LENGTH_LONG).show();
//                                Intent intent = new Intent(addComment_popup.this,ComplaintInfo.class);
//                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(addComment_popup.this,"failed to post comment",Toast.LENGTH_LONG).show();
                            }

                        }catch (JSONException e){
                            Toast.makeText(addComment_popup.this, "JSONObjectException:\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(addComment_popup.this,"onErrorResponse" + error.getMessage(),Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                }

                );
            }
        });


    }

}
