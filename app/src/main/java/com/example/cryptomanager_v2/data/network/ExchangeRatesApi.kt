package com.example.cryptomanager_v2.data.network

import io.reactivex.Observable
import retrofit2.http.GET

interface ExchangeRatesApi {

    @GET("latest?base=USD")
    fun getFiats(): Observable<String>
}
