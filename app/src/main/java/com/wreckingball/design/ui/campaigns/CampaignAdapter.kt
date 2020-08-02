package com.wreckingball.design.ui.campaigns

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.amplifyframework.datastore.generated.model.Campaign
import com.wreckingball.design.R
import com.wreckingball.design.callbacks.CampaignCallback
import com.wreckingball.design.databinding.ItemCampaignBinding
import kotlinx.android.synthetic.main.item_campaign.view.*

class CampaignAdapter(private val list: List<Campaign>,
                      selected: Int,
                      private val callback: CampaignCallback) : RecyclerView.Adapter<CampaignAdapter.ViewHolder>() {
    var selectedPosition = selected
    inner class ViewHolder(private val binding: ItemCampaignBinding, itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Campaign, position: Int) {
            if (position == selectedPosition) {
                itemView.campaign_card.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.colorAccent))
                itemView.campaign_name.setTextColor(ContextCompat.getColor(itemView.context, R.color.lightText))
                itemView.campaign_description.setTextColor(ContextCompat.getColor(itemView.context, R.color.lightText))
            } else {
                itemView.campaign_card.setCardBackgroundColor(ContextCompat.getColor(itemView.context, R.color.white))
                itemView.campaign_name.setTextColor(ContextCompat.getColor(itemView.context, R.color.darkText))
                itemView.campaign_description.setTextColor(ContextCompat.getColor(itemView.context, R.color.darkText))
            }
            binding.campaign = item
            binding.executePendingBindings()

            itemView.setOnClickListener {
                selectedPosition = position
                notifyDataSetChanged()
                callback.changeCampaign(position)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bindView(item, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val formatsBinding = ItemCampaignBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(formatsBinding, formatsBinding.root)
    }

    override fun getItemCount(): Int =list.size
}