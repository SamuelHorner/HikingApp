package com.samuelhorner.hikingapp.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.samuelhorner.hikingapp.R;

import java.util.List;
import java.util.Map;

public class ExpandableSocialListAdapter extends BaseExpandableListAdapter {

    private Activity context;
    private Map<String, List<String>> socialCollections;
    private List<String> socials;

    public ExpandableSocialListAdapter(Activity context, List<String> socials,
                                       Map<String, List<String>> socialCollections) {
        this.context = context;
        this.socialCollections = socialCollections;
        this.socials = socials;
    }

    public Object getChild(int groupPosition, int childPosition) {
        return socialCollections.get(socials.get(groupPosition)).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String social = (String) getChild(groupPosition, childPosition);
        LayoutInflater inflater = context.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.social_child_item, null);
        }

        TextView item = (TextView) convertView.findViewById(R.id.social);

        ImageView delete = (ImageView) convertView.findViewById(R.id.delete);
        delete.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to remove?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                List<String> child =
                                        socialCollections.get(socials.get(groupPosition));
                                child.remove(childPosition);
                                notifyDataSetChanged();
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        item.setText(social);
        return convertView;
    }

    public int getChildrenCount(int groupPosition) {
        return socialCollections.get(socials.get(groupPosition)).size();
    }

    public Object getGroup(int groupPosition) {
        return socials.get(groupPosition);
    }

    public int getGroupCount() {
        return socials.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String socialName = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.social_group_item,
                    null);
        }
        TextView item = (TextView) convertView.findViewById(R.id.social);
        item.setTypeface(null, Typeface.BOLD);
        item.setText(socialName);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}