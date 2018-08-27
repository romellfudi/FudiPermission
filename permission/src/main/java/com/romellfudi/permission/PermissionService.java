package com.romellfudi.permission;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.ArrayList;

/**
 *
 * @author Romell Domínguez
 * @version 1.0.a 23/02/2017
 * @since 1.0
 */

public class PermissionService {

    private SharedPreferences sharedPreferences;
    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected;

    private Activity context;
    public static int requestCode = 999;

    public PermissionService(Activity context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
    }

    public void request(String[] permissionsArray, final Callback callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<>();
            for (String perm : permissionsArray)
                permissions.add(perm);
            permissionsToRequest = missAllowPermissions(permissions);
            permissionsRejected = blockPermissions(permissions);
            if (permissionsToRequest.size() > 0) {
                context.requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]), requestCode);
                for (String perm : permissions)
                    sharedPreferences.edit().putBoolean(perm, false).apply();
            } else if (permissionsRejected.size() > 0) {
                for (String perm : permissionsRejected)
                    sharedPreferences.edit().putBoolean(perm, true).apply();
                callback.onRefuse(permissionsRejected);
            } else
                callback.onFinally();
        } else
            callback.onFinally();

    }

    private boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return (context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
        return true;
    }

    private ArrayList<String> missAllowPermissions(ArrayList<String> permissions) {
        ArrayList<String> result = new ArrayList<String>();
        for (String perm : permissions)
            if (!hasPermission(perm) && sharedPreferences.getBoolean(perm, true))
                result.add(perm);
        return result;
    }

    private ArrayList<String> blockPermissions(ArrayList<String> permissions) {
        ArrayList<String> result = new ArrayList<String>();
        for (String perm : permissions)
            if (!hasPermission(perm) && !sharedPreferences.getBoolean(perm, true))
                result.add(perm);
        return result;
    }


    public static abstract class Callback implements PermisionInterface {
        public final void handler(String[] permissions, int[] grantResults) {
            ArrayList<String> permissionsRejected = new ArrayList<>();
            for (int i = 0; i < grantResults.length; i++)
                if (grantResults[i] != 0)
                    permissionsRejected.add(permissions[i]);
            if (permissionsRejected.size() > 0)
                onRefuse(permissionsRejected);
            else
                onFinally();
        }
    }

}
