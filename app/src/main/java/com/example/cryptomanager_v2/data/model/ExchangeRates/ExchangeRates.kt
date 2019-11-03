package com.example.cryptomanager_v2.data.model.ExchangeRates

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ExchangeRates : Serializable {

    @SerializedName("rates")
    @Expose
    var rates: List<Rate>? = null
}
