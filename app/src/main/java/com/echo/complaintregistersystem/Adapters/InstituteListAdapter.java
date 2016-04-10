package com.echo.complaintregistersystem.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.echo.complaintregistersystem.ListItems.InstituteEntry;
import com.echo.complaintregistersystem.R;

import java.util.List;

public class InstituteListAdapter extends ArrayAdapter<InstituteEntry>{
    private Activity myActivity;
    private List<InstituteEntry> instituteEntries;
    private SharedPreferences sharedPreferences;

    public InstituteListAdapter(Activity myActivity, List<InstituteEntry> instituteEntries) {
        super(myActivity, R.layout.item_institute, instituteEntries);
        this.myActivity = myActivity;
        this.instituteEntries = instituteEntries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView = convertView;
        if(myView==null)
            myView=myActivity.getLayoutInflater().inflate(R.layout.item_institute,parent,false);
        InstituteEntry instituteEntry=instituteEntries.get(position);
        ((TextView)myView.findViewById(R.id.ins_no)).setText(position+1+".");
        ((TextView)myView.findViewById(R.id.ins_title)).setText(instituteEntry.getTitle());
        ((TextView)myView.findViewById(R.id.ins_ByName)).setText(instituteEntry.getByName());
        ((TextView)myView.findViewById(R.id.ins_createdDate)).setText(instituteEntry.getCreatedDate());

        sharedPreferences = myActivity.getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE);
        Switch aSwitch = (Switch) myView.findViewById(R.id.ins_switch);
        if(sharedPreferences.getString("TYPE_OF_USER", "").equalsIgnoreCase("warden")){
            aSwitch.setVisibility(View.VISIBLE);
        }

        return myView;
    }
}
