package richa.osahub.com.vaccikaranapp;

import java.util.Locale;
import java.util.zip.Inflater;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class HomeActivity extends AppCompatActivity implements ActionBar.TabListener {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

       final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(0))
                            .setTabListener(this),0,false);
        actionBar.addTab(
                actionBar.newTab()
                        .setText(mSectionsPagerAdapter.getPageTitle(1))
                        .setTabListener(this), 1, true);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

                //noinspection SimplifiableIfStatement
       if (id == R.id.action_notificationsettings) {
        Intent intent = new Intent(getApplicationContext(),NotificationSettings.class);
           startActivity(intent);
   //        finish();
            return true;

        }
        if(id == R.id.action_profile){
            Intent intent = new Intent(getApplicationContext(),Profile.class);
            startActivity(intent);
     //       finish();
            return true;

        }
        if (id == R.id.action_changeNumber){
            Intent intent = new Intent(getApplicationContext(),ChangeNumber.class);
            startActivity(intent);
       //     finish();
            return true;
        }
        if (id == R.id.action_changePassword){
            Intent intent = new Intent(getApplicationContext(),ChangePassword.class);
            startActivity(intent);
         //   finish();
            return true;
        }
        if (id == R.id.action_about_app){
            Intent intent = new Intent(getApplicationContext(),HelpActivity.class);

            startActivity(intent);
//            finish();
            return true;
        }
        if(id == R.id.action_about_us){
            Intent intent = new Intent(getApplicationContext(),AboutUsActivity.class);
            startActivity(intent);
  //            finish();
            return true;
        }
        if(id == R.id.action_signOut){
            final SharedPreferences prefs = getSharedPreferences("Richie", MODE_PRIVATE);
            final SharedPreferences.Editor edit = prefs.edit();

            Intent intent = new Intent(getApplicationContext(),SigninActivity.class);
            edit.putString("email", "");
            edit.putString("password","");
            edit.apply();
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            // When the given tab is selected, switch to the corresponding page in
            // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
           // return PlaceholderFragment.newInstance(position + 1);
            switch (position){
                case 0: return new Updates();
                case 1: return new HomeFragment();

            }
            return null;
          //  return new HomeFragment();
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_updates).toUpperCase(l);
                case 1:
                    return getString(R.string.title_home).toUpperCase(l);
                     }
            return null;
        }
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            return rootView;
        }
    }

}
