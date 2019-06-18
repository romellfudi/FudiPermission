package com.romellfudi.permissionservice

import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

import java.util.ArrayList

import com.romellfudi.permission.PermissionService

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.Manifest.permission.CAMERA
import android.view.View

/**
 *
 * @author Romell Domínguez
 * @version 1.0.a 23/02/2017
 * @since 1.0
 */

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    private val callback = object : PermissionService.Callback() {
        override fun onRefuse(refusePermissions: ArrayList<String>) {
            Toast.makeText(baseContext,
                    "Have to allow all permissions",
                    Toast.LENGTH_SHORT).show()

            Handler().postDelayed({ finish() }, 2000)
        }

        override fun onFinally() {
            initController()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            initViews()
        PermissionService(this).request(
                arrayOf(ACCESS_FINE_LOCATION, CAMERA),
                callback)
    }

    private fun initViews() {
        textView = findViewById<View>(R.id.text) as TextView
    }

    private fun initController() {
        textView.text = "ToDo"
        Toast.makeText(baseContext, textView.text.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        // In case have other permissions flow task
        //        if (requestCode == PermissionService.requestCode) {
        PermissionService.handler(callback, grantResults, permissions)
        //        }
    }
}
