package com.wreckingball.design.ui.campaigns

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.wreckingball.design.R
import com.wreckingball.design.callbacks.CampaignCallback
import kotlinx.android.synthetic.main.fragment_campaigns.*
import org.koin.android.ext.android.inject

class CampaignsFragment : Fragment(R.layout.fragment_campaigns) {
    private val model: CampaignsViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.campaigns.observe(viewLifecycleOwner, Observer {campaigns ->
            if (campaigns.isEmpty()) {
                no_campaigns.visibility = View.VISIBLE
            } else {
                campaign_list.adapter = CampaignAdapter(campaigns,
                    model.getSelectedCampaign(),
                    object : CampaignCallback {
                        override fun changeCampaign(selectedCampaign: Int) {
                            model.changeCampaign(selectedCampaign)
                        }
                    })
            }
        })
    }
}
