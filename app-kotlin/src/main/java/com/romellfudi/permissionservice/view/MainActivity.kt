package com.romellfudi.permissionservice.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
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

    lateinit var snack: Snackbar

    private val callback: PermissionService.Callback
            by inject { parametersOf(this) }

    private val permissionService: PermissionService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        askBtn.setOnClickListener { permissionService.request(this, callback) }
        snack = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) = permissionService.handler(callback, grantResults, permissions)

    override fun showOK() = snack.run {
        setText("Ready!!!")
        view.setBackgroundColor(
            ContextCompat.getColor(this@MainActivity, R.color.colorPrimary)
        )
        duration =3000
        show()
    }

    override fun showError(error: String) = snack.run {
        setText(error)
        view.setBackgroundColor(
            ContextCompat.getColor(this@MainActivity, R.color.colorAccent)
        )
        duration =3000
        show()
    }
}
