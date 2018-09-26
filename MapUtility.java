package com.carHeroUser.utils.helper.location;

import android.content.Context;

/**
 * Created by NS on 4/7/2017.
 */

public class MapUtility {
    public double[] locationManager(Context context) {
        double[] latLongArray = new double[2];
        // create class object
        GPSTracker gps = new GPSTracker(context);
        // check if GPS enabled
        if (gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            latLongArray[0] = latitude;
            latLongArray[1] = longitude;
            return latLongArray;
        } else {
            gps.showSettingsAlert();
        }

        return null;

    }

    public double[] getCurrentLatLong(Context context) {
        double[] latLong = new double[2];
        GPSTracker gps = new GPSTracker(context);
        if (gps.canGetLocation()) {
            latLong[0] = gps.getLatitude();
            latLong[1] = gps.getLongitude();
        } else {

            gps.showSettingsAlert();
            return null;
        }

        return latLong;

    }
}
