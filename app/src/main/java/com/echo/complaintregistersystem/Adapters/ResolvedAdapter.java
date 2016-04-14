package com.echo.complaintregistersystem.Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.echo.complaintregistersystem.ListItems.ResolvedEntry;
import com.echo.complaintregistersystem.R;

import java.util.List;

/**
 * Created by Rohit on 4/14/16.
 */
public class ResolvedAdapter extends ArrayAdapter<ResolvedEntry> {
    Activity activity;
    List<ResolvedEntry> resolvedEntries;
    public ResolvedAdapter(Activity context, List<ResolvedEntry> objects) {
        super(context, R.layout.item_resolved, objects);
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
            itemView = activity.getLayoutInflater().inflate(R.layout.item_resolved, parent,false);

        ResolvedEntry entry = resolvedEntries.get(position);
        ((TextView) itemView.findViewById(R.id.resolved_id)).setText(position+1+"");
        ((TextView) itemView.findViewById(R.id.resolved_title)).setText(entry.getTitle());
        ((TextView) itemView.findViewById(R.id.resolved_level)).setText(entry.getLevel());
        ((TextView) itemView.findViewById(R.id.resolved_date)).setText(entry.getResolvedDate());

        return itemView;
    }
}
