package com.echo.complaintregistersystem.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.echo.complaintregistersystem.ListItems.Individual_CLEntry;
import com.echo.complaintregistersystem.R;

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
        Individual_CLEntry Individual_clEntry= individual_clEntries.get(position);
        ((TextView)myView.findViewById(R.id.indcl_no)).setText(position+1+".");
        ((TextView)myView.findViewById(R.id.indcl_title)).setText(Individual_clEntry.getTitle());
        ((TextView)myView.findViewById(R.id.indcl_createdDate)).setText(Individual_clEntry.getCreatedDate());

        return myView;
    }
}
