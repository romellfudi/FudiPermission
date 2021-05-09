package com.romellfudi.permissionservice.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.romellfudi.permissionservice.R
import java.util.*
import kotlin.concurrent.schedule

class SplashActivity : AppCompatActivity() {

    var timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        timer.schedule(5000) {
            var intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onPause() {
        timer.cancel()
        super.onPause()
    }
}