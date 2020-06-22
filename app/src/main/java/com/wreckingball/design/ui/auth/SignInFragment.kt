package com.wreckingball.design.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.wreckingball.design.LoginActivity
import com.wreckingball.design.R
import kotlinx.android.synthetic.main.fragment_signin.*
import kotlinx.android.synthetic.main.fragment_signup.password
import kotlinx.android.synthetic.main.fragment_signup.username
import org.koin.android.ext.android.inject

class SignInFragment : Fragment(R.layout.fragment_signin) {
    private val model: LoginViewModel by inject()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        signin.setOnClickListener {
            if (model.handleSignInClick(username.text.toString(), password.text.toString())) {
                progress_signin.visibility = View.VISIBLE
            }
        }

        register_user.setOnClickListener {view ->
            val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
            view.findNavController().navigate(action)
        }

        forgot_password.setOnClickListener {
            progress_signin.visibility = View.VISIBLE
            model.handleForgotPasswordClick()
        }

        model.isLoggedIn.observe(viewLifecycleOwner, Observer {isLoggedIn ->
            progress_signin.visibility = View.GONE
            if (isLoggedIn) {
                val activity = requireActivity() as LoginActivity
                activity.userSignedIn()
            }
        })

        model.forgotPasswordSent.observe(viewLifecycleOwner, Observer {forgotSent->
            progress_signin.visibility = View.GONE
            if (forgotSent) {
                val action = SignInFragmentDirections.actionSignInFragmentToConfirmPasswordFragment()
                requireView().findNavController().navigate(action)
            }
        })
    }
}