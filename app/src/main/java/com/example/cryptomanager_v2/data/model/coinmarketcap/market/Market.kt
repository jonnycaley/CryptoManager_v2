package com.example.cryptomanager_v2.data.model.coinmarketcap.market

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Market : Serializable {

    @SerializedName("data")
    @Expose
    var data: Data? = null
    @SerializedName("status")
    @Expose
    var status: Status? = null

    companion object {
        private const val serialVersionUID = 5238751506793636051L
    }

}
