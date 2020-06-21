package com.wreckingball.design

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.wreckingball.design.utils.PreferencesWrapper
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

const val CHANGE_PASSWORD_KEY = "changePassword"

class LoginActivity : AppCompatActivity() {
    val preferencesWrapper: PreferencesWrapper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val navController = login_nav_host_fragment.findNavController()
        val navInflater = navController.navInflater
        val graph = navInflater.inflate(R.navigation.login_navigation)

        val changePassword = intent.getBooleanExtra(CHANGE_PASSWORD_KEY, false)
        if (changePassword) {
            graph.startDestination = R.id.changePasswordFragment
        } else {
            graph.startDestination = R.id.signInFragment
        }
        navController.graph = graph
    }

    fun userSignedIn() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
