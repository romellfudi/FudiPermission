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
import com.romellfudi.permissionservice.ui.main.component.ActivityComponent
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

    override val view: View
        get() = findViewById(android.R.id.content)

    @Inject
    lateinit var callback: PermissionService.Callback

    @Inject
    lateinit var permissionService: PermissionService

    private val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        askBtn.setOnClickListener { permissionService.request(this,callback) }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) =
            PermissionService.handler(callback, grantResults, permissions)

    override fun showOK() =
            Snackbar.make(view, "ready", Snackbar.LENGTH_SHORT).run {
                view.setBackgroundColor(
                        ContextCompat.getColor(this@MainActivity, R.color.colorPrimary))
                show()
            }

    override fun showError(error: String) =
            Snackbar.make(view, error, Snackbar.LENGTH_SHORT).run {
                view.setBackgroundColor(
                        ContextCompat.getColor(this@MainActivity, R.color.colorAccent))
                show()
            }
}
