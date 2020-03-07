package com.romellfudi.permissionservice

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.romellfudi.permission.PermissionService
import java.util.*

/**
 *
 * @author Romell Domínguez
 * @version 1.0.a 23/02/2017
 * @since 1.0
 */

class MainActivity : AppCompatActivity() {


    private val callback = object : PermissionService.Callback() {
        override fun onResponse(refusePermissions: ArrayList<String>?) {
            if (refusePermissions != null) {
                val snackBar = Snackbar.make(findViewById(android.R.id.content),
                        "Have to allow all permissions", Snackbar.LENGTH_SHORT)
                snackBar.view.setBackgroundColor(ContextCompat
                        .getColor(applicationContext, R.color.colorAccent))
                snackBar.show()
                Handler().postDelayed({ finish() }, 1500)
            } else
                initController()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PermissionService(this@MainActivity).request(callback)
    }


    private fun initController() {
        (findViewById<View>(R.id.text) as TextView).text = "ToDo"
        val snackBar = Snackbar.make(findViewById(android.R.id.content),
                "ready", Snackbar.LENGTH_SHORT)
        snackBar.view.setBackgroundColor(ContextCompat
                .getColor(applicationContext, R.color.colorPrimary))
        snackBar.show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) =
            PermissionService.handler(grantResults, permissions, callback)
}
