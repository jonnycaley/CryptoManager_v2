package com.example.cryptomanager_v2.utils.di.modules

import com.example.cryptomanager_v2.data.network.cryptocompare.CryptoCompareApi
import com.example.cryptomanager_v2.data.network.exchangerates.ExchangeRatesApi
import com.example.cryptomanager_v2.utils.mocks.api.FakeCryptoCompareApi
import com.example.cryptomanager_v2.utils.mocks.api.FakeExchangeRatesApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FakeNetworkModule {

    @Provides
    @Singleton
    fun providesExchangeRatesApi(): ExchangeRatesApi {
        return FakeExchangeRatesApi()
    }

    @Provides
    @Singleton
    fun providesCrypotCompareApi(): CryptoCompareApi {
        return FakeCryptoCompareApi()
    }
}