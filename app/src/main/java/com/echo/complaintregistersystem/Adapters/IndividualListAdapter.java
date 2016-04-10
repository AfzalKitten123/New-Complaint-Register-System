package com.echo.complaintregistersystem.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
import com.echo.complaintregistersystem.ListItems.Individual_CLEntry;
import com.echo.complaintregistersystem.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class IndividualListAdapter extends ArrayAdapter<Individual_CLEntry> {
    private Activity myActivity;
    private List<Individual_CLEntry> individual_clEntries ;
    SharedPreferences sharedPreferences;

    public IndividualListAdapter(Activity myActivity, List<Individual_CLEntry> individual_clEntries) {
        super(myActivity, R.layout.item_individual, individual_clEntries);
        this.myActivity = myActivity;
        this.individual_clEntries = individual_clEntries;
        this.sharedPreferences = myActivity.getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView=convertView;
        if(myView==null)
            myView = myActivity.getLayoutInflater().inflate(R.layout.item_individual,parent,false);
        final Individual_CLEntry Individual_clEntry= individual_clEntries.get(position);
        ((TextView)myView.findViewById(R.id.indcl_no)).setText(position+1+".");
        ((TextView)myView.findViewById(R.id.indcl_title)).setText(Individual_clEntry.getTitle());
        ((TextView)myView.findViewById(R.id.indcl_createdDate)).setText(Individual_clEntry.getCreatedDate());
        Switch isresolved = (Switch)myView.findViewById(R.id.indcl_switch);
        isresolved.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //Toast.makeText(myActivity,"got this 1",Toast.LENGTH_LONG).show();

                    int id = Individual_clEntry.getID();
                    String url = MainActivity.ip + "resolveComplaint/"+id;

                    JsonObjectRequest jsonRequest = new JsonObjectRequest(
                            Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                      //      Toast.makeText(myActivity,"got this",Toast.LENGTH_LONG).show();

                            try {
                                if(response.getBoolean("success")){
                                    Toast.makeText(myActivity,"complaint succesfully resolved",Toast.LENGTH_LONG).show();
                                }else {
                                    Toast.makeText(myActivity,"comlaint failed to be marked as resolved",Toast.LENGTH_LONG).show();
                                }

                            }catch (JSONException e){
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

                }else{
                    Toast.makeText(myActivity,"fuck this shit",Toast.LENGTH_LONG).show();
                }
            }
        });

        return myView;
    }
}
