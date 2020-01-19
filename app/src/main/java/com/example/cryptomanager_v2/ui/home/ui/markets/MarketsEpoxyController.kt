package com.example.cryptomanager_v2.ui.home.ui.markets

import com.airbnb.epoxy.TypedEpoxyController
import com.example.cryptomanager_v2.data.model.coinmarketcap.Currencies
import com.example.cryptomanager_v2.ui.home.ui.markets.models.currency
import javax.inject.Inject

class MarketsEpoxyController @Inject constructor(

): TypedEpoxyController<Currencies>() {

    override fun buildModels(top100: Currencies) {
        top100.data?.forEach { currency ->
            currency {
                crypto(currency)
            }
        }
    }
}