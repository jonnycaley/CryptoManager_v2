package com.example.cryptomanager_v2.data.network.coinmarketcap

import com.example.cryptomanager_v2.data.model.coinmarketcap.Currencies
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinMarketCapApi {

    @GET("cryptocurrency/listings/latest?start=1&convert=USD")
    suspend fun getTopUSD(@Query("limit") symbol: String): Observable<Currencies>
}