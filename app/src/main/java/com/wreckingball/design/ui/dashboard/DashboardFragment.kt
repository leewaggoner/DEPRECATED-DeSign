package com.wreckingball.design.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.wreckingball.design.R
import com.wreckingball.design.repositories.SignRepository
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.koin.android.ext.android.inject

class DashboardFragment() : Fragment(R.layout.fragment_dashboard) {
    private val signRepository: SignRepository by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val signList = ArrayList(signRepository.signMap.values)
        sign_list.adapter = SignsAdapter(signList)
    }
}
