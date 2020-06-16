package com.wreckingball.design.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.wreckingball.design.LoginActivity
import com.wreckingball.design.R
import com.wreckingball.design.auth.Authentication
import kotlinx.android.synthetic.main.fragment_signin.*
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.fragment_signup.authenticate
import kotlinx.android.synthetic.main.fragment_signup.password
import kotlinx.android.synthetic.main.fragment_signup.username
import org.koin.android.ext.android.inject

class SignInFragment : Fragment(R.layout.fragment_signin) {
    private val authentication: Authentication by inject()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        authenticate.setOnClickListener {
            if (authentication.validUsername(username.text.toString())
                && authentication.validPassword(password.text.toString())) {
                authentication.signIn(username.text.toString(), password.text.toString())
            }
        }

        register_user.setOnClickListener {view ->
            val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
            view.findNavController().navigate(action)
        }

        forgot_password.setOnClickListener {
            authentication.forgotPassword()
        }

        authentication.isLoggedIn.observe(viewLifecycleOwner, Observer {isLoggedIn ->
            if (isLoggedIn) {
                val activity = requireActivity() as LoginActivity
                activity.userSignedIn()
            }
        })

        authentication.forgotPasswordSent.observe(viewLifecycleOwner, Observer {forgotSent->
            if (forgotSent) {
                val action = SignInFragmentDirections.actionSignInFragmentToConfirmPasswordFragment()
                requireView().findNavController().navigate(action)
            }
        })
    }
}