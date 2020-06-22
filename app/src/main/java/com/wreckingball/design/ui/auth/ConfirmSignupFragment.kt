package com.wreckingball.design.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.wreckingball.design.R
import kotlinx.android.synthetic.main.fragment_confirm_signup.*
import org.koin.android.ext.android.inject

class ConfirmSignupFragment : Fragment(R.layout.fragment_confirm_signup) {
    private val model: LoginViewModel by inject()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        confirm_signup.setOnClickListener {
            if (model.handleConfirmSignUpClick(confirmation_code.text.toString())) {
                progress_confirm_signup.visibility = View.VISIBLE
            }
        }

        model.registrationComplete.observe(viewLifecycleOwner, Observer {registrationComplete ->
            progress_confirm_signup.visibility = View.GONE
            if (registrationComplete) {
                val action = ConfirmSignupFragmentDirections.actionConfirmSignupFragmentToSignInFragment()
                requireView().findNavController().navigate(action)
            }
        })
    }
}