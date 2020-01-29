package com.example.cryptomanager_v2.data.model.coinmarketcap.market

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.math.BigDecimal

class USDDto : Serializable {

    @SerializedName("total_market_cap")
    @Expose
    var totalMarketCap: BigDecimal? = null
    @SerializedName("total_volume_24h")
    @Expose
    var totalVolume24h: BigDecimal? = null
    @SerializedName("last_updated")
    @Expose
    var lastUpdated: String? = null

    companion object {
        private const val serialVersionUID = -480902373748681864L
    }

}
