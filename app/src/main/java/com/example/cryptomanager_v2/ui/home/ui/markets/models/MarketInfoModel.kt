package com.example.cryptomanager_v2.ui.home.ui.markets.models

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.cryptomanager_v2.R
import com.example.cryptomanager_v2.R2

@EpoxyModelClass(layout = R2.layout.market_info)
abstract class MarketInfoModel : EpoxyModelWithHolder<MarketInfoModel.Holder>() {

    @EpoxyAttribute
    lateinit var marketCap: String

    @EpoxyAttribute
    lateinit var volume: String

    @EpoxyAttribute
    lateinit var dominance: String

    override fun bind(holder: Holder) {
        holder.textViewVolume.text = volume
        holder.textViewMarketCap.text = marketCap
        holder.textviewDominance.text = dominance
    }

    class Holder: EpoxyHolder() {

        lateinit var textViewMarketCap: TextView
        lateinit var textViewVolume: TextView
        lateinit var textviewDominance: TextView

        override fun bindView(itemView: View) {
            textViewMarketCap = itemView.findViewById(R.id.text_market_cap)
            textViewVolume = itemView.findViewById(R.id.text_volume)
            textviewDominance = itemView.findViewById(R.id.text_btc_dominance)
        }
    }
}