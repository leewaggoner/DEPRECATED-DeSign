package com.wreckingball.design

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.wreckingball.design.auth.Authentication
import org.koin.android.ext.android.inject


class SplashActivity : AppCompatActivity() {
    private val authentication: Authentication by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission to access the location is missing. Request permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            );
        } else {
            authentication.checkAuthenticationStatus()
        }

        authentication.isLoggedIn.observe(this, Observer { loggedIn ->
            login(loggedIn)
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            authentication.checkAuthenticationStatus()
        } else {
            // permission denied, boo! tell the user the app is unusable
        }
    }

    private fun login(loggedIn: Boolean) {
        val intent = if (loggedIn) {
            Intent(applicationContext, MainActivity::class.java)
        } else {
            Intent(applicationContext, LoginActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}