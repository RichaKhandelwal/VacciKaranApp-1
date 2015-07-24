package richa.osahub.com.vaccikaranapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;


public class HomePage extends Fragment {

    LinearLayout schedule, wall;
    LinearLayout forum, locator;

    public HomePage() {
        // Required empty public constructor
    }

    public static LocationManager locationManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);


        View rootView = inflater.inflate(R.layout.fragment_home_page, container, false);
        schedule = (LinearLayout) rootView.findViewById(R.id.schedule);
        forum = (LinearLayout) rootView.findViewById(R.id.forum);
        locator = (LinearLayout) rootView.findViewById(R.id.locator);
        wall = (LinearLayout) rootView.findViewById(R.id.wall);

        locator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity().getApplicationContext(), VacciLocatorActivity.class);
                startActivity(in);
            }
        });

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(inte);
            }
        });

        wall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intend = new Intent(getActivity().getApplicationContext(), VacciWall.class);
                startActivity(intend);
            }
        });

        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intr = new Intent(getActivity().getApplicationContext(), VacciForumActivity.class);
                startActivity(intr);
            }
        });
        return rootView;
    }


}
