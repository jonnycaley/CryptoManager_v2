package com.example.cryptomanager_v2.ui.home.ui.markets.models

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.cryptomanager_v2.R
import com.example.cryptomanager_v2.R2
import com.example.cryptomanager_v2.data.model.coinmarketcap.Currency

@EpoxyModelClass(layout = R2.layout.crypto_item)
abstract class CurrencyModel : EpoxyModelWithHolder<CurrencyModel.Holder>() {

    @EpoxyAttribute
    lateinit var crypto: Currency

    override fun bind(holder: Holder) {
        holder.name.text = crypto.name
    }

    inner class Holder: EpoxyHolder() {

        lateinit var name: TextView

        override fun bindView(itemView: View) {
            name = itemView.findViewById(R.id.name)
        }
    }
}