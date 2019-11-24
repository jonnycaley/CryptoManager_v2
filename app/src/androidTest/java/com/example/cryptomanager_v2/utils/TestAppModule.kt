package com.example.cryptomanager_v2.utils

import com.example.cryptomanager_v2.data.db.exchanges.DBExchangeDao
import com.example.cryptomanager_v2.data.network.ExchangeRatesApi
import com.example.cryptomanager_v2.utils.di.AppSchedulers
import com.example.cryptomanager_v2.utils.mocks.db.FakeDBExchangesDao
import com.example.cryptomanager_v2.utils.mocks.api.FakeExchangeRatesApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestAppModule(
    private val exchangeRatesApi: ExchangeRatesApi = FakeExchangeRatesApi(),
    private val dBExchangeDao: DBExchangeDao = FakeDBExchangesDao()
) {

    @Provides
    @Singleton
    fun providesExchangeRatesApi(): ExchangeRatesApi {
        return exchangeRatesApi
    }

    @Provides
    @Singleton
    fun providesDBExchangesDao(): DBExchangeDao {
        return dBExchangeDao
    }

    @Provides
    @Singleton
    fun providesAppSchedulers(): AppSchedulers {
        return TestAppSchedulers.get()
    }
}