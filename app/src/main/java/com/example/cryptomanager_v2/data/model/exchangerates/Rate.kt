package com.example.cryptomanager_v2.data.model.exchangerates

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.math.BigDecimal

class Rate : Serializable {

    @SerializedName("fiat")
    @Expose
    var fiat: String? = null

    @SerializedName("rate")
    @Expose
    var rate: BigDecimal? = null

}
