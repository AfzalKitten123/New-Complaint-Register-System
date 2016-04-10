package com.echo.complaintregistersystem.Adapters;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.echo.complaintregistersystem.ListItems.ResidentEntry;
import com.echo.complaintregistersystem.R;

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
        ResidentEntry residentEntry= residentEntries.get(position);
        if(myView==null)
            myView = myActivity.getLayoutInflater().inflate(R.layout.item_resident,parent,false);

        ((TextView)myView.findViewById(R.id.res_no)).setText(position+1+".");
        ((TextView)myView.findViewById(R.id.res_title)).setText(residentEntry.getTitle());
        ((TextView)myView.findViewById(R.id.res_createdDate)).setText(residentEntry.getCreatedDate());
        ((TextView)myView.findViewById(R.id.res_byname)).setText(residentEntry.getByName());

        Switch aSwitch = (Switch) myView.findViewById(R.id.res_switch);
        if(sharedPreferences.getString("TYPE_OF_USER", "").equalsIgnoreCase("warden")){
            aSwitch.setVisibility(View.VISIBLE);
        }

        return myView;
    }
}
