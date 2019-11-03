package com.example.cryptomanager_v2.data.model.ExchangeRates

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ExchangeRatesOld {

    @SerializedName("base")
    @Expose
    var base: String? = null

    @SerializedName("rates")
    @Expose
    var rates: RatesOld? = null
}
