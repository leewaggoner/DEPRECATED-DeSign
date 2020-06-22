package com.wreckingball.design.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.wreckingball.design.R
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.koin.android.ext.android.inject

class DashboardFragment() : Fragment(R.layout.fragment_dashboard) {
    private val model: DashboardViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.signs.observe(viewLifecycleOwner, Observer { signList->
            if (signList.isEmpty()) {
                add_signs.visibility = View.VISIBLE
            } else {
                sign_list.adapter = SignsAdapter(signList)
            }
        })
    }
}
