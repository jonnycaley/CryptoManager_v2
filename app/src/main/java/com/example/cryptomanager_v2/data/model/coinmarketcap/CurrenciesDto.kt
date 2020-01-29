package com.example.cryptomanager_v2.data.model.coinmarketcap

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CurrenciesDto : Serializable {

    @SerializedName("status")
    @Expose
    var statusDto: StatusDto? = null
    @SerializedName("data")
    @Expose
    var data: List<Currency>? = null
}

class StatusDto : Serializable {

    @SerializedName("timestamp")
    @Expose
    var timestamp: String? = null
    @SerializedName("error_code")
    @Expose
    var errorCode: Int? = null
    @SerializedName("error_message")
    @Expose
    var errorMessage: Any? = null
    @SerializedName("elapsed")
    @Expose
    var elapsed: Int? = null
    @SerializedName("credit_count")
    @Expose
    var creditCount: Int? = null

    companion object {
        private const val serialVersionUID = 3375820567581602557L
    }
}


class Currency : Serializable {

    @SerializedName("id")
    var id: Long? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("symbol")
    var symbol: String? = null

    @SerializedName("slug")
    var slug: String? = null

    @SerializedName("circulating_supply")
    var circulatingSupply: Float? = null

    @SerializedName("total_supply")
    var totalSupply: Float? = null

    @SerializedName("max_supply")
    var maxSupply: Float? = null

    @SerializedName("date_added")
    var dateAdded: String? = null

    @SerializedName("num_market_pairs")
    var numMarketPairs: Long? = null

    @SerializedName("cmc_rank")
    var cmcRank: Long? = null

    @SerializedName("last_updated")
    var lastUpdated: String? = null

    @SerializedName("quote")
    var quote: Quote? = null
}

class Quote : Serializable {

    @SerializedName("USD")
    var uSD: USD? = null

    @SerializedName("BTC")
    var bTC: USD? = null
}

class USD : Serializable {

    @SerializedName("price")
    var price: Float? = null

    @SerializedName("volume_24h")
    var volume24h: Float? = null

    @SerializedName("percent_change_1h")
    var percentChange1h: Float? = null

    @SerializedName("percent_change_24h")
    var percentChange24h: Float? = null

    @SerializedName("percent_change_7d")
    var percentChange7d: Float? = null

    @SerializedName("market_cap")
    var marketCap: Float? = null

    @SerializedName("last_updated")
    var lastUpdated: String? = null
}