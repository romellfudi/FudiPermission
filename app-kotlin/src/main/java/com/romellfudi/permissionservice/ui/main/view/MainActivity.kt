package com.romellfudi.permissionservice.ui.main.view

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.romellfudi.permission.PermissionService
import com.romellfudi.permissionservice.R
import com.romellfudi.permissionservice.ui.main.ActivityModule
import com.romellfudi.permissionservice.ui.main.component.DaggerActivityComponent
import com.romellfudi.permissionservice.ui.main.presenter.MainContract
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 *
 * @author Romell Domínguez
 * @version 1.0.a 23/02/2017
 * @since 1.0
 */

class MainActivity : AppCompatActivity(), MainContract.MainView {

    override var view: View
        get() = findViewById(android.R.id.content)
        set(_) = Unit

    override var activity: Activity = this

    @Inject
    lateinit var callback: PermissionService.Callback

    @Inject
    lateinit var permissionService: PermissionService

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        permissionService.request(callback)
    }

    override fun initController() {
        text.text = "ToDo"
        showOK()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) =
            PermissionService.handler(grantResults, permissions, callback)

    override fun showOK() {
        val snackBar = Snackbar.make(view,    "ready", Snackbar.LENGTH_SHORT)
        snackBar.view.setBackgroundColor(
                ContextCompat.getColor(applicationContext, R.color.colorPrimary))
        snackBar.show()
    }

    override fun showError(error: String) {
        val snackBar = Snackbar.make(view, error, Snackbar.LENGTH_SHORT)
        snackBar.view.setBackgroundColor(ContextCompat
                .getColor(applicationContext, R.color.colorAccent))
        snackBar.show()
    }
}
