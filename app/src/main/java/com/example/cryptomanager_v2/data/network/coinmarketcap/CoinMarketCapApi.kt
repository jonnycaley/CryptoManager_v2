package com.example.cryptomanager_v2.data.network.coinmarketcap

import com.example.cryptomanager_v2.data.model.coinmarketcap.Currencies
import io.reactivex.Observable
import retrofit2.http.GET

interface CoinMarketCapApi {

    @GET("cryptocurrency/listings/latest?start=1&convert=USD&limit=100")
    fun getTopUSD(): Observable<Currencies>
}