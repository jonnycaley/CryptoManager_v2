package com.example.cryptomanager_v2.utils

import com.example.cryptomanager_v2.data.network.ExchangeRatesApi
import dagger.Module

@Module
class TestAppModule(
    private val exchangeRatesApi: ExchangeRatesApi = FakeExchangeRatesApi()
) {

    fun providesExchangeRatesApi(): ExchangeRatesApi {
        return exchangeRatesApi
    }
}