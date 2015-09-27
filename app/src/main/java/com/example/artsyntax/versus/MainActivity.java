
    /*

    Key for Android apps (with certificates)
    API key:	AIzaSyCGl2H0HPJVh2NTgdexf39b_D6ESukUGf0
    Android apps:	3C:95:71:1B:BD:EF:59:3C:D9:EE:E5:CE:82:5C:F6:06:32:45:FB:E4;com.example.artsyntax.versus
    Activated on:	May 4, 2015 11:29 AM
    Activated by:	arttra.eagleb@gmail.com – you

    KU lat = 13.845783923770902 long = 100.56858203870516
    */


package com.example.artsyntax.versus;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;


    public class MainActivity extends ActionBarActivity implements LocationListener {


    // GPS KU
    double latitude = 13.845783923770902;
    double longitude = 100.56858203870516;

    double latitudeGod = 15.845783923770902;
    double longitudeGod = 100.56858203870516;

    double latitudeMike = 23.845783923770902;
    double longitudeMike = 103.56858203870516 ;

    double latitudeTete = 13.845783923770902;
    double longitudeTete = 95.56858203870516;

    private Button goto_hit;
    private Button goto_heal;

    LatLng latLngGod = new LatLng(latitudeGod, longitudeGod);
    LatLng latLngMike = new LatLng(latitudeMike, longitudeMike);
    LatLng latLngTete = new LatLng(latitudeTete, longitudeTete);

    private Marker markerGod;
    private Marker markerMike;
    private Marker markerTete;

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private ImageButton menu_setting;
    private ImageButton menu_profile;
    private LocationManager locationManager;
    private String provider;
    private Circle mCircle;
    private Marker mMarker,mMarker1,mMarker2,mMarker3;

    private ImageView image_profile;

    private  TextView level,name,hp_bar,sp_bar;

    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        Log.d("location", "lat = " + latitude + " long = " + longitude);

        // Creating a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);

        // Showing the current location in Google Map
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // Zoom in the Google Map
        mMap.animateCamera(CameraUpdateFactory.zoomTo(18));

        if(mCircle == null || mMarker == null){
            drawMarkerWithCircle(latLng);
        }else{
            updateMarkerWithCircle(latLng);
        }

        //locate.setText("location " + "lat = " + latitude + " long = " + longitude );
        //myurl = "http://158.108.181.176:8000/add?lat="+latitude+"&lng="+longitude+"&color=ff5c00&title=5510504048";
        //submitLocate.start();

    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            if (v == menu_profile) {
                Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);

            }
            else if (v == menu_setting) {
                Intent intent=new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent);
            }
            else if (v == goto_heal) {
                Intent intent=new Intent(MainActivity.this,HealActivity.class);
                startActivity(intent);
            }
            else if (v == goto_hit) {
                Intent intent=new Intent(MainActivity.this,KillActivity.class);
                startActivity(intent);
            }

            finish();
        }
    };

    public void onStatusChanged(String provider, int status, Bundle extras) {  }
    public void onProviderEnabled(String provider) { }
    public void onProviderDisabled(String provider) { }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setUpMapIfNeeded();

        level = (TextView)findViewById(R.id.level);
        name = (TextView)findViewById(R.id.name);
        hp_bar = (TextView)findViewById(R.id.hp_bar);
        sp_bar = (TextView)findViewById(R.id.sp_bar);

        menu_setting = (ImageButton) findViewById(R.id.menu_setting);
        menu_setting.setOnClickListener(clickListener);

        menu_profile = (ImageButton) findViewById(R.id.menu_profile);
        menu_profile.setOnClickListener(clickListener);

        image_profile = (ImageView) findViewById(R.id.image_profile);

        goto_heal = (Button) findViewById(R.id.goto_heal);
        goto_heal.setOnClickListener(clickListener);

        goto_hit = (Button) findViewById(R.id.goto_hit);
        goto_hit.setOnClickListener(clickListener);


        //loadUserStatus();
        loadProfile(); // load image profile

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
        if(status!= ConnectionResult.SUCCESS){ // Google Play Services are not available

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        }else { // Google Play Services are available

            // Getting reference to the SupportMapFragment of activity_main.xml
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

            // Getting GoogleMap object from the fragment
            mMap = fm.getMap();

            // Enabling MyLocation Layer of Google Map
            mMap.setMyLocationEnabled(true);

            // Getting LocationManager object from System Service LOCATION_SERVICE
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


            // Creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();

            // Getting the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);

            // Getting Current Location
            Location location = locationManager.getLastKnownLocation(provider);

            if(location!=null){
                onLocationChanged(location);
            }
            locationManager.requestLocationUpdates(provider, 20000, 0, this);
        }
    }


    private void loadUserStatus() {
        level.setText(Action.thisplayer.level);
        name.setText(Action.thisplayer.name);
        hp_bar.setText(Action.thisplayer.userhp);
        sp_bar.setText(Action.thisplayer.usersp);
    }


    private  void loadProfile()
    {
        File imgFile = new File(ProfileActivity.path_image_profile);
        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            image_profile.setImageBitmap(myBitmap);
        }

    }

    private void updateMarkerWithCircle(LatLng position) {
        mCircle.setCenter(position);

        //mMarker.setPosition(position);        // this user
//        mMarker1.setPosition(latLngGod);
//        mMarker2.setPosition(latLngMike);
//        mMarker3.setPosition(latLngTete);

    }


    private void drawMarkerWithCircle(LatLng position){

        double radiusInMeters = 50.0;
        int strokeColor = 0x9900ffff; //outline
        int shadeColor = 0x3300ffff; //opaque red fill

        CircleOptions circleOptions = new CircleOptions().center(position).radius(radiusInMeters).fillColor(shadeColor).strokeColor(strokeColor).strokeWidth(8);
        //mMap.clear();
        mCircle = mMap.addCircle(circleOptions);


        MarkerOptions markerOptions = new MarkerOptions().position(position);
        //mMarker = mMap.addMarker(markerOptions);              // show marker




        MarkerOptions markerOptions1 = new MarkerOptions().position(latLngGod);
        MarkerOptions markerOptions2 = new MarkerOptions().position(latLngMike);
        MarkerOptions markerOptions3 = new MarkerOptions().position(latLngTete);


        mMarker = mMap.addMarker(markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        mMap.addMarker(markerOptions);



        drawMarker(latLngGod);
        drawMarker(latLngMike);
        drawMarker(latLngTete);
    }

    private void drawMarker(LatLng point){
        // Creating an instance of MarkerOptions
        MarkerOptions markerOptions = new MarkerOptions();

        // Setting latitude and longitude for the marker
        markerOptions.position(point);

        // Adding marker on the Google Map
        mMap.addMarker(markerOptions);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        //mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}




