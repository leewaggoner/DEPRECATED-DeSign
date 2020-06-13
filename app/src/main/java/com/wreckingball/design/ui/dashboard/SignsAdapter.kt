package com.wreckingball.design.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wreckingball.design.databinding.ItemSignBinding
import com.wreckingball.design.models.Sign


class SignsAdapter(private val list: List<Sign>) : RecyclerView.Adapter<SignsAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemSignBinding, itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Sign) {
            binding.sign = item
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bindView(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val formatsBinding = ItemSignBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(formatsBinding, formatsBinding.root)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}