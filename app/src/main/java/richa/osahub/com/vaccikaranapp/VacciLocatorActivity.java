package richa.osahub.com.vaccikaranapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Random;


public class VacciLocatorActivity extends AppCompatActivity {

    MapView mMapView;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_fragment);
        mMapView = (MapView)findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();

        setUpMap();
        addingMarkers();
       // addingCircles();
        setOnLongClickListenerForAddingNewMarkers();

    }

    private void setUpMap() {

        // To show my location button on Google maps
        googleMap.setMyLocationEnabled(true);
        LocationManager locationManager = HomeFragment.locationManager;
        Criteria criteria = new Criteria();

        final Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (location != null) {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(location.getLatitude(), location.getLongitude()), 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            // Adding a circle around Pacific Mall of 1000 meters radius.
            googleMap.addCircle(new CircleOptions()
                    .center(new LatLng(location.getLatitude(), location.getLongitude())) // Setting center point
                    .radius(10000) // In meters
                    .strokeColor(Color.RED) // Color of Border of Circle
                    .fillColor(R.color.semi_blue) // Colour of fill, I've set the color to semi transparent blue which I've defined in colors.xml. For fill colour always use semi transparent colour.
                    .strokeWidth(5)); // Width of red border.

        }

    }

    private void addingMarkers() {
        // Adding marker to locations - This is a marker for Shipra Mall, which is close to my house. Zoom out to see the Marker
        double latitude = 28.62498;
        double longitude =  77.098477;

        // create marker
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(latitude, longitude)).title("Vardhman Clinic");

        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        googleMap.addMarker(marker);
        // End - Adding marker to location


        // Another marker for District Center in Janakpuri
        double latitude2 = 28.637896;
        double longitude2 = 77.112388;

        MarkerOptions marker2 = new MarkerOptions().position(
                new LatLng(latitude2, longitude2)).title("CHILD CARE CLINIC AND VACCINATION CENTRE");
        marker2.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        googleMap.addMarker(marker2);
        // End - Another marker for District Center in Janakpuri

        double latitude3 = 28.650703;
        double longitude3 = 77.161502;

        MarkerOptions marker3 = new MarkerOptions().position(
                new LatLng(latitude2, longitude2)).title("Dr A K Bansal, Consultant Child Specialist , Mother & Child Clinic and Vaccination Centre");
        marker3.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        googleMap.addMarker(marker3);

        double latitude4 = 28.592899;
        double longitude4 = 77.046151;

        MarkerOptions marker4 = new MarkerOptions().position(
                new LatLng(latitude2, longitude2)).title("Kumar Child Clinic");
        marker4.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        googleMap.addMarker(marker4);

    }
/*
    private void addingCircles() {
        // Latitude and Longitude of Pacific Mall Subash Nagar
        double latitude = 28.6438947;
        double longitude = 77.1128296;

    }
*/
    private void setOnLongClickListenerForAddingNewMarkers() {
        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng latLng) {
                // This method will add a new marker to wherever the user long clicks on the Map.
                // The title of the marker will be set based on what is located at that location.
                // The Geocoder.getFromLocation() method gets the address and details of the location.

                // Find Details of location
                Geocoder geocoder;
                List<Address> addresses = null;
                geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                assert addresses != null;
                String address = addresses.get(0).getAddressLine(0);
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();
                // End - Find details of location

                // Add the new marker to the map
                MarkerOptions mo = new MarkerOptions();
                mo.position(latLng);
                mo.title(knownName + ", " + address + ", " + city + ", " + state + ", " + country);
                mo.icon(BitmapDescriptorFactory
                        .defaultMarker(new Random().nextFloat() * 360));
                googleMap.addMarker(mo);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
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

}
