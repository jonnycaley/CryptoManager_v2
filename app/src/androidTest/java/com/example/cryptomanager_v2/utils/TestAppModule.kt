package com.example.cryptomanager_v2.utils

import com.example.cryptomanager_v2.data.db.cryptos.DBCryptosDao
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangeDao
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import com.example.cryptomanager_v2.data.network.CryptoCompareApi
import com.example.cryptomanager_v2.data.network.ExchangeRatesApi
import com.example.cryptomanager_v2.utils.di.AppSchedulers
import com.example.cryptomanager_v2.utils.mocks.api.FakeCryptoCompareApi
import com.example.cryptomanager_v2.utils.mocks.db.FakeDBExchangesDao
import com.example.cryptomanager_v2.utils.mocks.api.FakeExchangeRatesApi
import com.example.cryptomanager_v2.utils.mocks.db.FakeDBCryptosDao
import com.example.cryptomanager_v2.utils.mocks.db.FakeDBFiatsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestAppModule(
    private val exchangeRatesApi: ExchangeRatesApi = FakeExchangeRatesApi(),
    private val cryptoCompareApi: CryptoCompareApi = FakeCryptoCompareApi(),
    private val dBExchangeDao: DBExchangeDao = FakeDBExchangesDao(),
    private val dbFiatsDao: DBFiatsDao = FakeDBFiatsDao(),
    private val dbCryptosDao: DBCryptosDao = FakeDBCryptosDao()
) {

    @Provides
    @Singleton
    fun providesExchangeRatesApi(): ExchangeRatesApi {
        return exchangeRatesApi
    }

    @Provides
    @Singleton
    fun providesCrypotCompareApi(): CryptoCompareApi {
        return cryptoCompareApi
    }

    @Provides
    @Singleton
    fun providesDBExchangesDao(): DBExchangeDao {
        return dBExchangeDao
    }

    @Provides
    @Singleton
    fun providesDBFiatsDao(): DBFiatsDao {
        return dbFiatsDao
    }

    @Provides
    @Singleton
    fun providesDBCryptosDao(): DBCryptosDao {
        return dbCryptosDao
    }

    @Provides
    @Singleton
    fun providesAppSchedulers(): AppSchedulers {
        return TestAppSchedulers.get()
    }
}