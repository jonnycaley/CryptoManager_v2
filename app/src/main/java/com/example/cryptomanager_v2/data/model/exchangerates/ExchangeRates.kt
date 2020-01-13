package com.example.cryptomanager_v2.data.model.exchangerates

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ExchangeRates : Serializable {

    @SerializedName("rates")
    @Expose
    var rates: List<Rate>? = null
}
