package com.example.rohanraja.geofencingsj.PolygonChecker;

import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

/**
 *  Created by Rohan Raja on 29/01/15.
 *  Special Thanks to Roman Kushnarenko, author of PolygonChecker

 *  GeoFencerPolygon Class : To Initialize the polygon and check if a LatLng Point lies inside the defined Polygon

 *  Usage Example :

              GeoFencerPolygon geoFencerPolygon = new GeoFencerPolygon();
              Boolean isInside = geoFencerPolygon.isPointInPolygon(new LatLng(26.9234,75.6740));

 */

public class GeoFencerPolygon {

    Polygon polygon;
    ArrayList<LatLng> points;
    PolygonChecker polygon1 ;

    public GeoFencerPolygon()
    {
        // Define the Polygon corners here by adding the points to the ArrayList

        points = new ArrayList<>() ;

        // Jaipur Boundary - Corners Entry Start

        points.add(new LatLng(26.753768, 75.856819));
        points.add(new LatLng(26.796987, 75.758286));
        points.add(new LatLng(26.889804, 75.670395));
        points.add(new LatLng(26.919808, 75.638809));
        points.add(new LatLng(26.968164, 75.684814));
        points.add(new LatLng(26.989582, 75.732193));
        points.add(new LatLng(27.020782, 75.761032));
        points.add(new LatLng(27.007324, 75.808411));
        points.add(new LatLng(26.944907, 75.813904));
        points.add(new LatLng(26.944907, 75.813904));
        points.add(new LatLng(26.970612, 75.847549));
        points.add(new LatLng(26.943683, 75.883255));
        points.add(new LatLng(26.921644, 75.870209));
        points.add(new LatLng(26.908175, 75.918274));
        points.add(new LatLng(26.892866, 75.903168));
        points.add(new LatLng(26.883680, 75.940933));
        points.add(new LatLng(26.846315, 75.900421));
        points.add(new LatLng(26.802809, 75.894241));
        points.add(new LatLng(26.753768, 75.856819));

        // Jaipur Boundary - Corners Entry End

        buildPolygon();

    }

    // **** THE MAIN FUNCTION : CALL THIS TO CHECK IF A LatLng LIES INSDIE THE POLYGON ****

    public Boolean isPointInPolygon(LatLng point)
    {
        Point testingPoint = new Point(point);
        return polygon1.contains(testingPoint);
    }

    private void buildPolygon()
    {
        PolygonChecker.Builder polyBuilder = new PolygonChecker.Builder();

        for(LatLng l : points)
        {
            Point tmp = new Point(l);
            polyBuilder.addVertex(tmp);
        }

        polygon1 = polyBuilder.build();

    }

    // **** Draws the polygon on the provided GoogleMap instance, mainly to test functionality ****

    public void drawPolygonOnMap(GoogleMap pMap)
    {
        polygon = pMap.addPolygon(new PolygonOptions()
                .add(new LatLng(0, 0))
                .strokeColor(Color.RED));

        polygon.setPoints(points);
    }


}


