package com.echo.complaintregistersystem.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.echo.complaintregistersystem.ListItems.CommentEntry;
import com.echo.complaintregistersystem.R;

import java.util.List;

/**
 * Created by Rohit.
 */
public class CommentListAdapter extends ArrayAdapter<CommentEntry> {

    Activity activity;
    List<CommentEntry> commentEntries;

    public CommentListAdapter(Context context, List<CommentEntry> commentEntries) {
        super(context, R.layout.item_comment, commentEntries);
        this.activity = (Activity) context;
        this.commentEntries = commentEntries;
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
        if (itemView==null)
            itemView = activity.getLayoutInflater().inflate(R.layout.item_comment, parent, false);

        CommentEntry commentEntry = commentEntries.get(position);

        ((TextView)itemView.findViewById(R.id.comment_text)).setText(commentEntry.getTitle());
        ((TextView)itemView.findViewById(R.id.comment_name)).setText(commentEntry.getName());
        ((TextView)itemView.findViewById(R.id.comment_date)).setText(commentEntry.getDate());

        return itemView;
    }
}
