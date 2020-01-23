package com.example.cryptomanager_v2.data.network.coinmarketcap

import com.example.cryptomanager_v2.data.model.coinmarketcap.Currencies
import com.example.cryptomanager_v2.data.model.coinmarketcap.market.Market
import retrofit2.http.GET

interface CoinMarketCapApi {

    @GET("cryptocurrency/listings/latest?start=1&convert=USD&limit=100")
    suspend fun getTopUSD(): Currencies

    @GET("global-metrics/quotes/latest")
    fun getMarketData(): Market
}