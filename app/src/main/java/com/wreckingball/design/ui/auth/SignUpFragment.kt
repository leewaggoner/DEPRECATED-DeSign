package com.wreckingball.design.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.wreckingball.design.R
import com.wreckingball.design.auth.Authentication
import com.wreckingball.design.utils.PreferencesWrapper
import kotlinx.android.synthetic.main.fragment_signup.*
import org.koin.android.ext.android.inject

class SignUpFragment : Fragment(R.layout.fragment_signup) {
    private val authentication: Authentication by inject()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        authenticate.setOnClickListener {
            if (authentication.validUsername(username.text.toString())
                && authentication.validEmail(email.text.toString())
                && authentication.validPassword(password.text.toString())) {
                authentication.registerUser(username.text.toString(), password.text.toString(), email.text.toString())
            }
        }

        authentication.registrationSent.observe(viewLifecycleOwner, Observer {registrationSent ->
            if (registrationSent) {
                val action = SignUpFragmentDirections.actionSignUpFragmentToConfirmSignupFragment()
                requireView().findNavController().navigate(action)
            }
        })
    }
}