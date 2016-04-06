package com.echo.complaintregistersystem.Adapters;

import android.app.Activity;
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

    public IndividualListAdapter(Activity myActivity, List<Individual_CLEntry> individual_clEntries) {
        super(myActivity, R.layout.item_individual_cl, individual_clEntries);
        this.myActivity = myActivity;
        this.individual_clEntries = individual_clEntries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView=convertView;
        if(myView==null)
            myView = myActivity.getLayoutInflater().inflate(R.layout.item_individual_cl,parent,false);
        Individual_CLEntry Individual_clEntry= individual_clEntries.get(position);
        ((TextView)myView.findViewById(R.id.ind_no)).setText(position+1);
        ((TextView)myView.findViewById(R.id.ind_title)).setText(Individual_clEntry.getTitle());
        ((TextView)myView.findViewById(R.id.I_createdDate)).setText(Individual_clEntry.getCreatedDate());

        return myView;
    }
}
