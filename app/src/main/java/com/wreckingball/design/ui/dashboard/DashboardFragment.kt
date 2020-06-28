package com.wreckingball.design.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.wreckingball.design.R
import com.wreckingball.design.callbacks.SwipeToDeleteCallback
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.koin.android.ext.android.inject

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {
    private val model: DashboardViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                model.handleSwipeLeft(viewHolder.adapterPosition)
            }
        }

        model.signs.observe(viewLifecycleOwner, Observer { signList->
            if (signList.isEmpty()) {
                add_signs.visibility = View.VISIBLE
            } else {
                sign_list.adapter = SignsAdapter(signList)
                val itemTouchHelper = ItemTouchHelper(swipeHandler)
                itemTouchHelper.attachToRecyclerView(sign_list)
            }
        })
    }
}
