package com.example.cryptomanager_v2.data.model.coinmarketcap.market

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class StatusDto : Serializable {

    @SerializedName("timestamp")
    @Expose
    var timestamp: String? = null
    @SerializedName("error_code")
    @Expose
    var errorCode: Int? = null
    @SerializedName("error_message")
    @Expose
    var errorMessage: String? = null
    @SerializedName("elapsed")
    @Expose
    var elapsed: Int? = null
    @SerializedName("credit_count")
    @Expose
    var creditCount: Int? = null

    companion object {
        private const val serialVersionUID = 835105106560052117L
    }

}
