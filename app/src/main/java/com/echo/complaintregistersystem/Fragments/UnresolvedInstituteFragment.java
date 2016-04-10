package com.echo.complaintregistersystem.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.echo.complaintregistersystem.Adapters.InstituteListAdapter;
import com.echo.complaintregistersystem.ComplaintInfo;
import com.echo.complaintregistersystem.ListItems.InstituteEntry;
import com.echo.complaintregistersystem.Activities.MainActivity;
import com.echo.complaintregistersystem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UnresolvedInstituteFragment extends Fragment {

    ListView listView;
    List<InstituteEntry> complaintList;
    SharedPreferences sharedPreferences;
    public UnresolvedInstituteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView=inflater.inflate(R.layout.fragment_unresolved_institute, container, false);
        listView=(ListView)myView.findViewById(R.id.Ins_lv);
        sharedPreferences = getActivity().getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE);
        String userID = sharedPreferences.getString("PRIMARY_ID","1");
//url need to be added
        String url= MainActivity.ip + "getURInstituteComplaints/";
        Toast.makeText(getActivity()," Retrieving the Complaints ",Toast.LENGTH_SHORT).show();

        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        try {
                            JSONArray complaintArray = response.getJSONArray("List_of_InstituteUnresolvedComplaints");
                            complaintList = new ArrayList<>();
                            for(int i=0;i<complaintArray.length();i++){
                                complaintList.add(new InstituteEntry(complaintArray.getJSONObject(i).getString("title"),
                                        complaintArray.getJSONObject(i).getString("description"),
                                        complaintArray.getJSONObject(i).getString("category"),
                                        complaintArray.getJSONObject(i).getString("date_created"),
                                        complaintArray.getJSONObject(i).getString("date_resolved"),
                                        complaintArray.getJSONObject(i).getString("byname"),
                                        complaintArray.getJSONObject(i).getString("username"),
                                        complaintArray.getJSONObject(i).getString("room_no"),
                                        complaintArray.getJSONObject(i).getString("origin")
                               //         complaintArray.getJSONObject(i).getString("comments")
                                ));
                            }
                            InstituteListAdapter adapter= new InstituteListAdapter(getActivity(),complaintList);
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            Toast.makeText(getActivity(), "JSONObjectException:\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "ErrorResponse:\n"+error.getMessage(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(getActivity()).add(jsonRequest);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), ComplaintInfo.class);
                i.putExtra("title", complaintList.get(position).getTitle());
                i.putExtra("description", complaintList.get(position).getDescription());
                i.putExtra("category", complaintList.get(position).getCategory());
                i.putExtra("date_created", complaintList.get(position).getCreatedDate());
                i.putExtra("date_resolved", complaintList.get(position).getResolvedDate());
                i.putExtra("byname", complaintList.get(position).getByName());
                i.putExtra("username", complaintList.get(position).getUsername());
                i.putExtra("room_no", complaintList.get(position).getRoomNo());
                // i.putExtra("comments",complaintList.get(position).getComments());

                startActivity(i);
            }
        });



        return myView;
    }

}

