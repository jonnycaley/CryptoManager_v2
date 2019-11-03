package com.example.cryptomanager_v2.data.model.ExchangeRates

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.JsonElement
import com.google.gson.JsonArray
import com.google.gson.JsonObject

class ExchangeRatesOld {

    @SerializedName("base")
    var base: String? = null

    @SerializedName("rates")
    var rates: RatesOld? = null
}
