package com.echo.complaintregistersystem.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.echo.complaintregistersystem.Activities.MainActivity;
import com.echo.complaintregistersystem.ListItems.ResidentEntry;
import com.echo.complaintregistersystem.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ResidentListAdapter extends ArrayAdapter<ResidentEntry> {

    private Activity myActivity;
    private List<ResidentEntry> residentEntries;
    private SharedPreferences sharedPreferences;


    public ResidentListAdapter(Activity myActivity, List<ResidentEntry> residentEntries) {
        super(myActivity, R.layout.item_institute_r, residentEntries);
        this.myActivity = myActivity;
        this.residentEntries = residentEntries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView=convertView;
        final ResidentEntry residentEntry= residentEntries.get(position);
        if(myView==null)
            myView = myActivity.getLayoutInflater().inflate(R.layout.item_resident,parent,false);

        ((TextView)myView.findViewById(R.id.res_no)).setText(position+1+".");
        ((TextView)myView.findViewById(R.id.res_title)).setText(residentEntry.getTitle());
        ((TextView)myView.findViewById(R.id.res_createdDate)).setText(residentEntry.getCreatedDate());
        ((TextView)myView.findViewById(R.id.res_byname)).setText(residentEntry.getByName());
        ((TextView)myView.findViewById(R.id.resLikes_no)).setText(residentEntry.getVotes() + " Votes");
        sharedPreferences = myActivity.getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE);
        Switch aSwitch = (Switch) myView.findViewById(R.id.res_switch);
        if(sharedPreferences.getString("TYPE_OF_USER", "").equalsIgnoreCase("warden")){
            aSwitch.setVisibility(View.VISIBLE);
            Switch isresolved = (Switch)myView.findViewById(R.id.res_switch);
            isresolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        //Toast.makeText(myActivity,"got this 1",Toast.LENGTH_LONG).show();

                        int id = residentEntry.getID();
                        String url = MainActivity.ip + "resolveComplaint/" + id;

                        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //      Toast.makeText(myActivity,"got this",Toast.LENGTH_LONG).show();

                                try {
                                    if (response.getBoolean("success")) {
                                        Toast.makeText(myActivity, "complaint succesfully resolved", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(myActivity, "comlaint failed to be marked as resolved", Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    Toast.makeText(myActivity, "JSONObjectException:\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(myActivity, "OnErrorResponse:\n" + error.getMessage(), Toast.LENGTH_LONG).show();
                                error.printStackTrace();

                            }
                        }
                        );
                        Volley.newRequestQueue(myActivity).add(jsonRequest);

                    } else {
                        Toast.makeText(myActivity, "fuck this shit", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            final Button aButton = (Button)myView.findViewById(R.id.resLike_btn);
            final Boolean[] isUpvoted = {sharedPreferences.getBoolean(residentEntry.getID()+"", false)};
            if(isUpvoted[0])
                aButton.setBackgroundResource(R.drawable.like_blue);
            else
                aButton.setBackgroundResource(R.drawable.like_black);
            aButton.setVisibility(View.VISIBLE);
            aButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isUpvoted[0].equals(false)){
                        isUpvoted[0] = true;
                        sharedPreferences.edit().putBoolean(residentEntry.getID()+"", true).commit();
                        aButton.setBackgroundResource(R.drawable.like_blue);
                        int ID = residentEntry.getID();
                        String url = MainActivity.ip + "upVoteComplaint/" + ID + "/1";
                        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //      Toast.makeText(myActivity,"got this",Toast.LENGTH_LONG).show();

                                try {
                                    if (response.getBoolean("success")) {
                                        Toast.makeText(myActivity, "Upvote is succesful", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(myActivity, "Upvoting failed", Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    Toast.makeText(myActivity, "JSONObjectException:\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(myActivity, "OnErrorResponse:\n" + error.getMessage(), Toast.LENGTH_LONG).show();
                                error.printStackTrace();

                            }
                        }
                        );
                        Volley.newRequestQueue(myActivity).add(jsonRequest);

                    } else {
                        isUpvoted[0] = false;
                        sharedPreferences.edit().putBoolean(residentEntry.getID()+"", false).commit();
                        aButton.setBackgroundResource(R.drawable.like_black);
                        int ID = residentEntry.getID();
                        String url = MainActivity.ip + "upVoteComplaint/" + ID + "/0";
                        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //      Toast.makeText(myActivity,"got this",Toast.LENGTH_LONG).show();

                                try {
                                    if (response.getBoolean("success")) {
                                        Toast.makeText(myActivity, "Downvote is succesful", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(myActivity, "DownVoting failed", Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    Toast.makeText(myActivity, "JSONObjectException:\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(myActivity, "OnErrorResponse:\n" + error.getMessage(), Toast.LENGTH_LONG).show();
                                error.printStackTrace();

                            }
                        }
                        );
                        Volley.newRequestQueue(myActivity).add(jsonRequest);

                    }
                    }

            });
    }
        return myView;
    }
}
