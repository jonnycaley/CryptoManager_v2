package com.example.cryptomanager_v2.data.network.coinmarketcap

import com.example.cryptomanager_v2.data.model.coinmarketcap.CurrenciesDto
import com.example.cryptomanager_v2.data.model.coinmarketcap.market.MarketDto
import retrofit2.http.GET

interface CoinMarketCapApi {

    @GET("cryptocurrency/listings/latest?start=1&convert=USD&limit=100")
    suspend fun getTopUSD(): CurrenciesDto

    @GET("global-metrics/quotes/latest")
    fun getMarketData(): MarketDto
}