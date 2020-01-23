package com.example.cryptomanager_v2.ui.home.ui.markets

import com.airbnb.epoxy.TypedEpoxyController
import com.example.cryptomanager_v2.ui.home.ui.markets.models.currency
import javax.inject.Inject

class MarketsEpoxyController @Inject constructor(

): TypedEpoxyController<MarketsData>() {

    override fun buildModels(marketsData: MarketsData) {

        marketsData.top100.data?.data?.forEach { currency ->
            currency {
                id(currency.hashCode())
                crypto(currency)
            }
        }
    }
}