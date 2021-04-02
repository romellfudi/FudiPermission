package com.romellfudi.permissionservice.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.romellfudi.permission.PermissionService
import com.romellfudi.permissionservice.R
import com.romellfudi.permissionservice.presenter.MainContract
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

/**
 *
 * @author Romell Domínguez
 * @version 1.0.a 23/02/2017
 * @since 1.0
 */

class MainActivity : AppCompatActivity(), MainContract.MainView, KoinComponent {

    override val view: View
        get() = findViewById(android.R.id.content)

    private val callback: PermissionService.Callback
            by inject { parametersOf(this) }

    private val permissionService: PermissionService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        askBtn.setOnClickListener { permissionService.request(this, callback) }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) =
            PermissionService.handler(callback, grantResults, permissions)

    override fun showOK() = Snackbar.make(view, "ready", Snackbar.LENGTH_SHORT).run {
                view.setBackgroundColor(
                        ContextCompat.getColor(this@MainActivity, R.color.colorPrimary))
                show()
            }

    override fun showError(error: String) = Snackbar.make(view, error, Snackbar.LENGTH_SHORT).run {
                view.setBackgroundColor(
                        ContextCompat.getColor(this@MainActivity, R.color.colorAccent))
                show()
            }
}
