package com.wreckingball.design

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wreckingball.design.auth.Authentication
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {
    private val authentication: Authentication by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        authenticate.setOnClickListener {
            if (userName.text.isNotEmpty() && password.text.isNotEmpty()) {
                authentication.authenticateUser(userName.text.toString(), password.text.toString())
                if(authentication.loggedIn) {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}