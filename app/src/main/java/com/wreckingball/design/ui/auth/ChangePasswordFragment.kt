package com.wreckingball.design.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.wreckingball.design.LoginActivity
import com.wreckingball.design.R
import kotlinx.android.synthetic.main.fragment_change_password.*
import org.koin.android.ext.android.inject

class ChangePasswordFragment : Fragment(R.layout.fragment_change_password) {
    private val model: LoginViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reset_password.setOnClickListener {
            if (model.handleResetPasswordClick(existing_password.text.toString(), new_password.text.toString())) {
                progress_change_password.visibility = View.VISIBLE
            }
        }

        //TODO: Handle errors
        model.resetPassword.observe(viewLifecycleOwner, Observer { reset_password ->
            progress_change_password.visibility = View.GONE
            if (reset_password) {
                val intent = Intent(requireContext(), LoginActivity::class.java)
                requireContext().startActivity(intent)
            }
        })
    }
}