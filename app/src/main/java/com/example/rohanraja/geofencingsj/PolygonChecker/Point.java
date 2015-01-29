package com.example.rohanraja.geofencingsj.PolygonChecker;

import com.google.android.gms.maps.model.LatLng;

/**
 * Point on 2D landscape
 * 
 * @author Roman Kushnarenko (sromku@gmail.com)</br>
 */

// An internal utility Class!!!!
// GOTO GeoFencerPolygon Class for main functionality

public class Point
{
    public Point(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public float x;
    public float y;

    public Point(LatLng point) {
        this.x = ((float) point.latitude);
        this.y = ((float) point.longitude);
    }

    @Override
    public String toString()
    {
        return String.format("(%.2f,%.2f)", x, y);
    }
}