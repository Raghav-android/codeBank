
import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class PermissionManager {

    // code required to access the permission
    public static final int EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE = 2;
    public static final int CAMERA_PERMISSION_REQUEST_CODE = 3;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 4;
    public static final int CALL_PERMISSION_REQUEST_CODE = 5;

    // sting required to display the messages.
    private String cameraPermissionRequired = "Camera permission is required to access the camera functionality of this application.";
    private String externalStoragePermissionRequired = "External storage permission is required to access the device storage in this application.";
    private String locationPermissionRequired = "Location Permission required to access the location in this application.",alert = "Alert!!!",
            ok = "Ok", cancel = "Cancel" , openSettings = "Open Settings";
    private String callPermissionRequired = "Call Permission required to access the call functionality in this application.";
    private Activity activity;


    /**
     * @param activity : instance of an activity.
     */
    public PermissionManager(Activity activity) {
        this.activity = activity;
    }


    // setters for settings the custom display messages for the particular permission.
    public void setCameraPermissionRequired(String cameraPermissionRequired) {
        if(cameraPermissionRequired != null && !cameraPermissionRequired.equals("")) {
            this.cameraPermissionRequired = cameraPermissionRequired;
        }
    }

    public void setExternalStoragePermissionRequired(String externalStoragePermissionRequired) {
        if(externalStoragePermissionRequired != null && !externalStoragePermissionRequired.equals("")) {
            this.externalStoragePermissionRequired = externalStoragePermissionRequired;
        }
    }

    public void setLocationPermissionRequired(String locationPermissionRequired) {
        if(locationPermissionRequired != null && !locationPermissionRequired.equals("")) {
            this.locationPermissionRequired = locationPermissionRequired;
        }
    }

    public void setCallPermissionRequired(String callPermissionRequired) {
        if(callPermissionRequired != null && !callPermissionRequired.equals("")) {
            this.callPermissionRequired = callPermissionRequired;
        }
    }


    /**
     * check external storage permission in application.
     * @return : true or false
     */
    public boolean checkPermissionForExternalStorage() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }


    /**
     * check camera permission in application.
     * @return : true or false
     */
    public boolean checkPermissionForCamera() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        return result == PackageManager.PERMISSION_GRANTED;
    }


    /**
     * check location permission in application.
     * @return : true or false
     */
    public boolean checkPermissionForLocation() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * check call permission in application.
     * @return : true or false
     */
    public boolean checkPermissionForCall() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * check coarse location permission in application.
     * @return : true or false
     */
    public boolean checkPermissionForCoarseLocation() {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION);
        return result == PackageManager.PERMISSION_GRANTED;
    }

//    public boolean checkPermissionForSMS() {
//        int receiveResult = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECEIVE_SMS);
//        return result == PackageManager.PERMISSION_GRANTED;
//    }


    /**
     * Request external storage permission in application.
     */
    private void requestPermissionForExternalStorage() {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE);
    }

    /**
     * Request camera permission in application.
     */
    private void requestPermissionForCamera() {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
    }


    /**
     * Request location permission in application.
     */
    public void requestPermissionForLocation() {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
    }


    /**
     * Request call permission in application.
     */
    public void requestCallPermission() {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION_REQUEST_CODE);
    }


    /**
     * Check if External Storage permission denied weather denied with
     * "Never ask again" or just denied and then ask permission again
     * or redirect to settings screen to grant permission manually with
     * message.
     */
    private void ExternalStoragePermissionDenied(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(activity);
            mBuilder.setTitle(alert).setMessage(externalStoragePermissionRequired).setPositiveButton(ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    requestPermissionForExternalStorage();
                }
            }).setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).create().show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(alert).setMessage(externalStoragePermissionRequired).setCancelable(false);
            builder.setPositiveButton(openSettings, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", activity.getPackageName(), null));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity.startActivity(intent);
                }
            }).create().show();
        }
    }


    /**
     * Check if Camera permission denied weather denied with
     * "Never ask again" or just denied and then ask permission again
     * or redirect to settings screen to grant permission manually with
     * message.
     */
    public void CameraPermissionDenied() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
            android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(activity);
            mBuilder.setTitle(alert).setMessage(cameraPermissionRequired).setPositiveButton(ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    requestPermissionForCamera();
                }
            }).setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).create().show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(alert).setMessage(cameraPermissionRequired).setCancelable(false);
            builder.setPositiveButton(openSettings, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", activity.getPackageName(), null));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity.startActivity(intent);
                }
            }).create().show();
        }
    }


    /**
     * Check if location permission denied weather denied with
     * "Never ask again" or just denied and then ask permission again
     * or redirect to settings screen to grant permission manually with
     * message.
     */
    public void locationPermissionDenied() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
            android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(activity);
            mBuilder.setTitle(alert).setMessage(locationPermissionRequired).setPositiveButton(ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    requestPermissionForLocation();
                }
            }).setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).create().show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(alert).setMessage(locationPermissionRequired).setCancelable(false);
            builder.setPositiveButton(openSettings, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", activity.getPackageName(), null));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity.startActivity(intent);
                }
            }).create().show();
        }
    }


    /**
     * Check if call permission denied weather denied with
     * "Never ask again" or just denied and then ask permission again
     * or redirect to settings screen to grant permission manually with
     * message.
     */
    public void callPermissionDenied() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CALL_PHONE)) {
            android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(activity);
            mBuilder.setTitle(alert).setMessage(callPermissionRequired).setPositiveButton(ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    requestPermissionForLocation();
                }
            }).setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).create().show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(alert).setMessage(callPermissionRequired).setCancelable(false);
            builder.setPositiveButton(openSettings, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package", activity.getPackageName(), null));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity.startActivity(intent);
                }
            }).create().show();
        }
    }


}
