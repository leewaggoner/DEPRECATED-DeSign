package com.wreckingball.design

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wreckingball.design.utils.PreferencesWrapper
import org.koin.android.ext.android.inject

private const val TAG = "LoginActivity"
private const val REGISTERED_KEY = "isRegistered"

class LoginActivity : AppCompatActivity() {
    val preferencesWrapper: PreferencesWrapper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun userSignedIn() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
