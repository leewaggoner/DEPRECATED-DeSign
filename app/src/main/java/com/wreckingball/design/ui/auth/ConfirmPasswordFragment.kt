package com.wreckingball.design.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.wreckingball.design.R
import com.wreckingball.design.auth.Authentication
import kotlinx.android.synthetic.main.fragment_confirm_password.*
import org.koin.android.ext.android.inject

class ConfirmPasswordFragment : Fragment(R.layout.fragment_confirm_password) {
    private val authentication: Authentication by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        confirm_password.setOnClickListener {
            if (confirmation_code.text.toString().isNotEmpty()
                && authentication.validPassword(password.text.toString())) {
                authentication.confirmPassword(confirmation_code.text.toString(), password.text.toString())
            }
        }

        authentication.passwordConfirmed.observe(viewLifecycleOwner, Observer {passwordConfirmed->
            if (passwordConfirmed) {
                val action = ConfirmPasswordFragmentDirections.actionConfirmPasswordFragmentToSignInFragment()
                requireView().findNavController().navigate(action)
            }
        })
    }
}