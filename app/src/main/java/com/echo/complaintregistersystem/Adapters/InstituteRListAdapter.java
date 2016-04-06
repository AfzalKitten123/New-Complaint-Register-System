package com.echo.complaintregistersystem.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.echo.complaintregistersystem.ListItems.InstituteREntry;
import com.echo.complaintregistersystem.R;

import java.util.List;

public class InstituteRListAdapter extends ArrayAdapter<InstituteREntry>{
    private Activity myActivity;
    private List<InstituteREntry> instituteREntries;

    public InstituteRListAdapter(Activity myActivity, List<InstituteREntry> instituteREntries) {
        super(myActivity, R.layout.item_institute_r, instituteREntries);
        this.myActivity = myActivity;
        this.instituteREntries = instituteREntries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView = convertView;
        if(myView==null)
            myView=myActivity.getLayoutInflater().inflate(R.layout.item_institute_r,parent,false);
        InstituteREntry instituteREntry=instituteREntries.get(position);
        ((TextView)myView.findViewById(R.id.insR_no)).setText(position+1+".");
        ((TextView)myView.findViewById(R.id.insR_title)).setText(instituteREntry.getTitle());
        ((TextView)myView.findViewById(R.id.insR_resolvedDate)).setText(instituteREntry.getResolvedDate());

        return myView;
    }
}
