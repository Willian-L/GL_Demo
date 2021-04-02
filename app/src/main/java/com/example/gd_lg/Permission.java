package com.example.gd_lg;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

public class Permission {
    private static final String[] PerLists = new String[]{Manifest.permission.CALL_PHONE};

    public static boolean isPermissionOK(Activity activity, String PERMISSION) {
        int permission = ActivityCompat.checkSelfPermission(activity, PERMISSION);
        return permission == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermission(Activity activity, String PERMISSION, int REQUEST_CODE) {
        ActivityCompat.requestPermissions(activity, new String[]{PERMISSION}, REQUEST_CODE);
    }

    public static void requestPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, PerLists, 1);
    }

    public static void checkAndRequestPermission(Activity activity, int REQUEST_CODE) {
        boolean isAllGrant = true;
        for (String per : PerLists) {
            if (ActivityCompat.checkSelfPermission(activity, per) != PackageManager.PERMISSION_GRANTED) {
                isAllGrant = false;
                break;
            }
        }
        if (!isAllGrant) {
            ActivityCompat.requestPermissions(activity, PerLists, REQUEST_CODE);
        }
    }

    public static boolean isAllGrant(Activity activity) {
        boolean isAllGrant = true;
        for (String per : PerLists) {
            if (ActivityCompat.checkSelfPermission(activity, per) != PackageManager.PERMISSION_GRANTED) {
                isAllGrant = false;
                break;
            }
        }
        return isAllGrant;
    }
}