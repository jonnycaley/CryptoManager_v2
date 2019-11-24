package com.example.cryptomanager_v2.utils.mocks.api

import com.example.cryptomanager_v2.data.network.ExchangeRatesApi
import io.reactivex.Observable

class FakeExchangeRatesApi: ExchangeRatesApi {

    private val responses: ArrayList<Any> = arrayListOf()

    override fun getFiats(): Observable<String> {
        return responses.first() as Observable<String>
    }

    fun addFiatResponse(response: Observable<String>) {
        responses.add(response)
    }
}