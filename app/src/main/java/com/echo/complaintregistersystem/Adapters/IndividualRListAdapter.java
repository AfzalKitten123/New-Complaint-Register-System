package com.echo.complaintregistersystem.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.echo.complaintregistersystem.ListItems.IndividualR_CLEntry;
import com.echo.complaintregistersystem.R;

import java.util.List;

public class IndividualRListAdapter extends ArrayAdapter<IndividualR_CLEntry> {
    private Activity myActivity;
    public static List<IndividualR_CLEntry> individualR_clEntries;

    public IndividualRListAdapter(Activity myActivity, List<IndividualR_CLEntry> individualR_clEntries) {
        super(myActivity, R.layout.item_individual_r, individualR_clEntries);
        this.myActivity = myActivity;
        this.individualR_clEntries = individualR_clEntries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView=convertView;
        if(myView==null)
            myView=myActivity.getLayoutInflater().inflate(R.layout.item_individual_r,parent,false);
        IndividualR_CLEntry individualR_clEntry=individualR_clEntries.get(position);
        ((TextView)myView.findViewById(R.id.indclR_no)).setText(position+1+".");
        ((TextView)myView.findViewById(R.id.indclR_title)).setText(individualR_clEntry.getTitle());
        ((TextView)myView.findViewById(R.id.indclR_resolvedDate)).setText(individualR_clEntry.getResolvedDate());
        return myView;
    }
}
