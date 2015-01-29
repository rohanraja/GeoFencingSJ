package com.example.rohanraja.geofencingsj;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.rohanraja.geofencingsj.PolygonChecker.GeoFencerPolygon;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    GeoFencerPolygon geoFencerPolygon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);



        setUpMapIfNeeded();
    }

    public void geoFencing()
    {
        Geofence.Builder gf = new Geofence.Builder();

        gf.setCircularRegion(82.22, 23.22,344);
        Geofence newGF = gf.build();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
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

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
      //  mMap.addMarker(new MarkerOptions().position(new LatLng(26.753768, 75.856819)).title("Marker"));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(26.919808, 75.787811))      // Sets the center of the map to Mountain View
                .zoom(10)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        //mMap.animateCamera(CameraUpdateFactory.zoomTo(6), 1000, null);

        geoFencerPolygon = new GeoFencerPolygon();
        geoFencerPolygon.drawPolygonOnMap(mMap);

        setClickListen();

    }

    public void setClickListen()
    {
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                Boolean isInside = geoFencerPolygon.isPointInPolygon(latLng);
                String msg = "";
                BitmapDescriptor marCol = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED) ;
                if(isInside)
                {
                    marCol = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN) ;
                    msg = "INSIDE";
                }
                else
                    msg = "OUTSIDE";

                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                Log.d("MapsPoly", latLng.toString() + " is " + msg);
                mMap.addMarker(new MarkerOptions().position(latLng).title(msg).icon(marCol));
            }
        });
    }
}
