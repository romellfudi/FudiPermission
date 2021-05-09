package com.romellfudi.permissionservice;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.romellfudi.permission.PermissionService;

import java.util.ArrayList;

/**
 * @author Romell Domínguez
 * @version 1.0.a 23/02/2017
 * @since 1.0
 */

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null)
            initViews();
        new PermissionService(this).request(callback);
    }

    private void initViews() {
        textView = (TextView) findViewById(R.id.text);
    }

    private void initController() {
        textView.setText("ToDo");
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                "ready", Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(
                ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
        snackbar.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // In case have other permissions flow task
//        if (requestCode == PermissionService.requestCode) {
        callback.handler(permissions, grantResults);
//        }
    }

    private PermissionService.Callback callback = new PermissionService.Callback() {
        @Override
        public void onResponse(ArrayList<String> refusePermissions) {
            if (refusePermissions != null) {
                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                        "Have to allow all permissions", Snackbar.LENGTH_SHORT);
                snackbar.getView().setBackgroundColor(ContextCompat
                        .getColor(getApplicationContext(), R.color.colorAccent));
                snackbar.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 1500);
            } else {
                initController();
            }
        }
    };
}
