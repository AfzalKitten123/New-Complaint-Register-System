package com.echo.complaintregistersystem.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.echo.complaintregistersystem.ListItems.InstituteEntry;
import com.echo.complaintregistersystem.R;
import com.echo.complaintsystem.ListItem.InstituteEntry;
import com.echo.complaintsystem.R;

import java.util.List;

public class InstituteListAdapter extends ArrayAdapter<InstituteEntry>{
    private Activity myActivity;
    private List<InstituteEntry> instituteEntries;

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
        ((TextView)myView.findViewById(R.id.Number_tv)).setText(position);
        // ((TextView)myView.findViewById(R.id.Complaint_tv)).setText(instituteEntry.getComplaint());
        ((TextView)myView.findViewById(R.id.createdDate_tv)).setText(instituteEntry.getCreatedDate());

        return myView;
    }
}
