package com.example.cryptomanager_v2.data.network

import com.example.cryptomanager_v2.data.model.ExchangeRates.ExchangeRatesOld
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface ExchangeRatesApi {

    @GET("latest?base=USD")
    fun getFiats(): Observable<ExchangeRatesOld>
}
