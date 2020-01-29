package com.example.cryptomanager_v2.data.model.coinmarketcap.market
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DataDto : Serializable {

    @SerializedName("btc_dominance")
    @Expose
    var btcDominance: Float? = null
    @SerializedName("eth_dominance")
    @Expose
    var ethDominance: Float? = null
    @SerializedName("active_cryptocurrencies")
    @Expose
    var activeCryptocurrencies: Int? = null
    @SerializedName("active_market_pairs")
    @Expose
    var activeMarketPairs: Int? = null
    @SerializedName("active_exchanges")
    @Expose
    var activeExchanges: Int? = null
    @SerializedName("last_updated")
    @Expose
    var lastUpdated: String? = null
    @SerializedName("quote")
    @Expose
    var quoteDto: QuoteDto? = null

    companion object {
        private const val serialVersionUID = -305119108882521200L
    }

}
