package com.samuelhorner.hikingapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.samuelhorner.hikingapp.adapters.ExpandableSocialListAdapter;

import android.util.DisplayMetrics;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SocialsFragment extends Fragment {

    List<String> socialGroupList;
    List<String> socialChildList;
    Map<String, List<String>> socialCollection;
    ExpandableListView expListView;

    public SocialsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_socials, container, false);

        createGroupList();

        createCollection();

        expListView = (ExpandableListView) rootView.findViewById(R.id.social_list);

        final ExpandableSocialListAdapter expListAdapter = new ExpandableSocialListAdapter(
                getActivity(), socialGroupList, socialCollection);

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
        socialGroupList = new ArrayList<>();
        socialGroupList.add("Pub Crawl");
        socialGroupList.add("Punch Party");
        socialGroupList.add("Cinema");
        socialGroupList.add("FND");
        socialGroupList.add("Nottingham");
        socialGroupList.add("Echoes");
    }

    private void createCollection() {
        // preparing laptops collection(child)
        String[] pubCrawlDetails = { "Sun, 21st Feb 2016", "Meet: Swimming Pool - 08:30", "Cost: £10.00" };
        String[] punchDetails = { "Fri, 26th Feb 2016 - Sun, 28th Feb 2016", "Meet: Swimming Pool - 18:00", "Cost: £50.00" };
        String[] cinemaDetails = { "Sun, 6th Mar 2016", "Meet: Swimming Pool - 08:00", "Cost: £10.00" };
        String[] fndDetails = { "Wed, 9th Mar 2016", "Meet: Swimming Pool - 13:30", "Cost: £10.00" };
        String[] nottinghamDetails = { "Fri, 11th Mar 2016 - Sun, 13th Mar 2016", "Meet: Swimming Pool - 18:00", "Cost: £50.00" };
        String[] echoesDetails = { "Sun, 10th Apr 2016 - Fri, 15th Apr 2016", "Meet: Swimming Pool - 09:00", "Cost: £120.00" };

        socialCollection = new LinkedHashMap<>();

        for (String social : socialGroupList) {
            switch (social) {
                case "Pub Crawl":
                    loadChild(pubCrawlDetails);
                    break;
                case "Punch Party":
                    loadChild(punchDetails);
                    break;
                case "Cinema":
                    loadChild(cinemaDetails);
                    break;
                case "FND":
                    loadChild(fndDetails);
                    break;
                case "Nottingham":
                    loadChild(nottinghamDetails);
                    break;
                default:
                    loadChild(echoesDetails);
                    break;
            }

            socialCollection.put(social, socialChildList);
        }
    }

    private void loadChild(String[] socialDetails) {
        socialChildList = new ArrayList<>();
        Collections.addAll(socialChildList, socialDetails);
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
