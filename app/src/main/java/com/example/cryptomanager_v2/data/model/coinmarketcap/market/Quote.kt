package com.example.cryptomanager_v2.data.model.coinmarketcap.market

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Quote : Serializable {

    @SerializedName("USD")
    @Expose
    var uSD: USD? = null

    companion object {
        private const val serialVersionUID = -7649085317542117573L
    }

}
