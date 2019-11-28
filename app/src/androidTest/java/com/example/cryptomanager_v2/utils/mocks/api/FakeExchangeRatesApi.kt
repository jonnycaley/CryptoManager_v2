package com.example.cryptomanager_v2.utils.mocks.api

import com.example.cryptomanager_v2.data.network.ExchangeRatesApi
import io.reactivex.Observable
import javax.inject.Inject

class FakeExchangeRatesApi @Inject constructor(): ExchangeRatesApi {

    val getFiatsResponses: ArrayList<Observable<String>> = arrayListOf()
    override fun getFiats(): Observable<String> {
        return getFiatsResponses.removeAt(0)
    }
}