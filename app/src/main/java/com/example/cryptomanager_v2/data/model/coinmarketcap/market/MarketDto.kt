package com.example.cryptomanager_v2.data.model.coinmarketcap.market

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MarketDto : Serializable {

    @SerializedName("data")
    @Expose
    var dataDto: DataDto? = null
    @SerializedName("status")
    @Expose
    var statusDto: StatusDto? = null

    companion object {
        private const val serialVersionUID = 5238751506793636051L
    }

}
