package com.example.cryptomanager_v2.utils

import com.example.cryptomanager_v2.data.network.ExchangeRatesApi
import io.reactivex.Observable

class FakeExchangeRatesApi: ExchangeRatesApi {

    val getFiatsResponses: List<Observable<String>> = emptyList()

    override fun getFiats(): Observable<String> {
        return getFiatsResponses.first()
    }
}