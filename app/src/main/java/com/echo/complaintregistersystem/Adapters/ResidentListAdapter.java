package com.echo.complaintregistersystem.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.echo.complaintregistersystem.ListItems.ResidentEntry;
import com.echo.complaintregistersystem.R;

import java.util.List;

public class ResidentListAdapter extends ArrayAdapter<ResidentEntry> {

    private Activity myActivity;
    private List<ResidentEntry> residentEntries;

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

        ((TextView)myView.findViewById(R.id.Number_tv)).setText(position);
        ((TextView)myView.findViewById(R.id.Complaint_tv)).setText(residentEntry.getTitle());
        ((TextView)myView.findViewById(R.id.createdDate_tv)).setText(residentEntry.getCreatedDate());
        ((TextView)myView.findViewById(R.id.ByName_tv)).setText(residentEntry.getByName());

        return myView;
    }
}
