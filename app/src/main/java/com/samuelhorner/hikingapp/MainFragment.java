package com.samuelhorner.hikingapp;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.samuelhorner.hikingapp.adapters.ExpandableListAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> laptopCollection;
    ExpandableListView expListView;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);



        /*final Button mainButton = (Button) rootView.findViewById(R.id.main_button);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainButton.setBackgroundColor(getContext().getResources().getColor(R.color.colorAccent));
            }
        });*/

        createGroupList();

        createCollection();

        expListView = (ExpandableListView) rootView.findViewById(R.id.laptop_list);

       final ExpandableListAdapter expListAdapter = new ExpandableListAdapter(
                getActivity(), groupList, laptopCollection);

        expListView.setAdapter(expListAdapter);

        //setGroupIndicatorToRight();

        expListView.setOnChildClickListener(new OnChildClickListener() {

            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                final String selected = (String) expListAdapter.getChild(
                        groupPosition, childPosition);
                Toast.makeText(getActivity().getBaseContext(), selected, Toast.LENGTH_LONG)
                        .show();

                return true;
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    private void createGroupList() {
        groupList = new ArrayList<>();
        groupList.add("Sherwood Forest");
        groupList.add("Rydall Hall Lake District");
        groupList.add("Kinder Scout");
        groupList.add("Night Hike");
        groupList.add("Snowdonia");
        groupList.add("Cairngorms");
    }

    private void createCollection() {
        // preparing laptops collection(child)
        String[] sherwoodDetails = { "Sun, 21st Feb 2016", "Meet: Swimming Pool - 08:30", "Cost: £10.00" };
        String[] rydallDetails = { "Fri, 26th Feb 2016 - Sun, 28th Feb 2016", "Meet: Swimming Pool - 18:00", "Cost: £50.00" };
        String[] kinderDetails = { "Sun, 6th Mar 2016", "Meet: Swimming Pool - 08:00", "Cost: £10.00" };
        String[] nightDetails = { "Wed, 9th Mar 2016", "Meet: Swimming Pool - 13:30", "Cost: £10.00" };
        String[] snowdoniaDetails = { "Fri, 11th Mar 2016 - Sun, 13th Mar 2016", "Meet: Swimming Pool - 18:00", "Cost: £50.00" };
        String[] cairngormsDetails = { "Sun, 10th Apr 2016 - Fri, 15th Apr 2016", "Meet: Swimming Pool - 09:00", "Cost: £120.00" };

        laptopCollection = new LinkedHashMap<>();

        for (String laptop : groupList) {
            switch (laptop) {
                case "Sherwood Forest":
                    loadChild(sherwoodDetails);
                    break;
                case "Snowdonia":
                    loadChild(snowdoniaDetails);
                    break;
                case "Night Hike":
                    loadChild(nightDetails);
                    break;
                case "Rydall Hall Lake District":
                    loadChild(rydallDetails);
                    break;
                case "Cairngorms":
                    loadChild(cairngormsDetails);
                    break;
                default:
                    loadChild(kinderDetails);
                    break;
            }

            laptopCollection.put(laptop, childList);
        }
    }

    private void loadChild(String[] laptopModels) {
        childList = new ArrayList<>();
        Collections.addAll(childList, laptopModels);
    }

    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        expListView.setIndicatorBounds(width - getDipsFromPixel(35), width
                - getDipsFromPixel(5));
    }

    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

}
