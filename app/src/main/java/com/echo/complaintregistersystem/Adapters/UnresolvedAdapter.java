package com.echo.complaintregistersystem.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.echo.complaintregistersystem.ListItems.ResolvedEntry;
import com.echo.complaintregistersystem.R;

import java.util.List;

/**
 * Created by Rohit on 4/14/16.
 */
public class UnresolvedAdapter extends ArrayAdapter<ResolvedEntry> {
    Activity activity;
    List<ResolvedEntry> resolvedEntries;
    public UnresolvedAdapter(Activity context, List<ResolvedEntry> objects) {
        super(context, R.layout.item_unresolved, objects);
        this.activity = context;
        this.resolvedEntries = objects;
    }

    /**
     * {@inheritDoc}
     *
     * @param position
     * @param convertView
     * @param parent
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null)
            itemView = activity.getLayoutInflater().inflate(R.layout.item_unresolved, parent,false);

        ResolvedEntry entry = resolvedEntries.get(position);
        ((TextView) itemView.findViewById(R.id.unresolved_id)).setText(position+1+"");
        ((TextView) itemView.findViewById(R.id.unresolved_title)).setText(entry.getTitle());
        ((TextView) itemView.findViewById(R.id.unresolved_level)).setText(entry.getLevel());
        ((TextView) itemView.findViewById(R.id.unresolved_date)).setText(entry.getCreatedDate());

        LinearLayout layout = (LinearLayout) itemView.findViewById(R.id.unresolved_layout);
        if(!entry.getLevel().equalsIgnoreCase("individuallevel")){
            layout.setVisibility(View.VISIBLE);
            ((TextView) itemView.findViewById(R.id.unresolved_votes)).setText(entry.getVotes() + " Votes");
        }
        return itemView;
    }
}
