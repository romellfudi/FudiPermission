package fudi.freddy.permissionservice;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import fudi.freddy.permission.PermissionService;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;

/**
 *
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
        new PermissionService(this).request(
                new String[]{ACCESS_FINE_LOCATION, CAMERA},
                callback);
    }

    private void initViews() {
        textView = (TextView) findViewById(R.id.text);
    }

    private void initController() {
        textView.setText("ToDo");
        Toast.makeText(getBaseContext(), textView.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // In case have other permissions flow task
//        if (requestCode == PermissionService.requestCode) {
        callback.handler(permissions, grantResults);
//        }
    }

    private PermissionService.Callback callback = new PermissionService.Callback() {
        @Override
        public void onRefuse(ArrayList<String> RefusePermissions) {
            Toast.makeText(getBaseContext(),
                    "Have to allow all permissions",
                    Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 2000);
        }

        @Override
        public void onFinally() {
            initController();
        }
    };
}
