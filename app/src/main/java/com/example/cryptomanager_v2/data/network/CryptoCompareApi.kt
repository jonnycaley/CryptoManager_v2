package com.example.cryptomanager_v2.data.network

import io.reactivex.Observable
import retrofit2.http.GET

interface CryptoCompareApi {

    @GET("all/coinlist")
    fun getAllCrypto(): Observable<String>

    @GET("all/exchanges")
    fun getAllExchanges(): Observable<String>
}