package com.wreckingball.design.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.wreckingball.design.R
import kotlinx.android.synthetic.main.fragment_signup.*
import org.koin.android.ext.android.inject

class SignUpFragment : Fragment(R.layout.fragment_signup) {
    private val model: LoginViewModel by inject()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        signup.setOnClickListener {
            if (model.handleSignUpClick(username.text.toString(), password.text.toString(), email.text.toString())) {
                progress_signup.visibility = View.VISIBLE
            }
        }

        //TODO: Handle errors
        model.registrationSent.observe(viewLifecycleOwner, Observer {registrationSent ->
            progress_signup.visibility = View.GONE
            if (registrationSent) {
                val action = SignUpFragmentDirections.actionSignUpFragmentToConfirmSignupFragment()
                requireView().findNavController().navigate(action)
            }
        })
    }
}