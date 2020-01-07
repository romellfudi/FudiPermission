package com.romellfudi.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.ArrayList;

/**
 * @author Romell Dominguez
 * @version 1.0.a 23/02/2017
 * @since 1.0
 */

public class PermissionService implements PermisionServiceInterface {

    private SharedPreferences sharedPreferences;
    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected;
    private PermisionServiceInterface permisionServiceInterface;

    private Activity context;
    private PackageInfo info;
    public static int requestCode = 999;

    public PermissionService(Activity context) {
        this.context = context;
        this.permisionServiceInterface = this;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void request(final Callback callback) {
        if (permisionServiceInterface.getBuildSDK() >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<>();
            String[] permissionsArray = permisionServiceInterface.getPermissions();
            for (String perm : permissionsArray)
                permissions.add(perm);
            permissionsToRequest = missAllowPermissions(permissions);
            permissionsRejected = blockPermissions(permissions);
            if (permissionsToRequest.size() > 0) {
                context.requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]), requestCode);
                for (String perm : permissions) {
                    sharedPreferences.edit().putBoolean(perm, false);
                    sharedPreferences.edit().apply();
                }
            } else if (permissionsRejected.size() > 0) {
                for (String perm : permissionsRejected) {
                    sharedPreferences.edit().putBoolean(perm, false);
                    sharedPreferences.edit().apply();
                }
                callback.onResponse(permissionsRejected);
            } else
                callback.onResponse(null);
        } else
            callback.onResponse(null);

    }

    @TargetApi(Build.VERSION_CODES.M)
    private boolean hasPermission(String permission) {
        if (permisionServiceInterface.getBuildSDK() >= Build.VERSION_CODES.M)
            return (context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
        return true;
    }

    private ArrayList<String> missAllowPermissions(ArrayList<String> permissions) {
        ArrayList<String> result = new ArrayList<String>();
        for (String perm : permissions) {
            if (!hasPermission(perm) && sharedPreferences.getBoolean(perm, true))
                result.add(perm);
        }
        return result;
    }

    private ArrayList<String> blockPermissions(ArrayList<String> permissions) {
        ArrayList<String> result = new ArrayList<String>();
        for (String perm : permissions)
            if (!hasPermission(perm) && !sharedPreferences.getBoolean(perm, true))
                result.add(perm);
        return result;
    }

    @Override
    public int getBuildSDK() {
        return Build.VERSION.SDK_INT;
    }

    @Override
    public String[] getPermissions() {
        String[] permissionsArray = new String[0];
        try {
            info = this.context.getPackageManager()
                    .getPackageInfo(this.context.getPackageName(),
                            PackageManager.GET_PERMISSIONS);
            permissionsArray = info.requestedPermissions;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return permissionsArray;
    }

    public static abstract class Callback implements PermisionInterface {
        public final void handler(String[] permissions, int[] grantResults) {
            ArrayList<String> permissionsRejected = new ArrayList<>();
            for (int i = 0; i < grantResults.length; i++)
                if (grantResults[i] != 0)
                    permissionsRejected.add(permissions[i]);
            onResponse(permissionsRejected);
        }
    }

}
