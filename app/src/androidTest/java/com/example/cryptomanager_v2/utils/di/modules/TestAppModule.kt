package com.example.cryptomanager_v2.utils.di.modules

import com.example.cryptomanager_v2.data.db.cryptos.DBCryptosDao
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangesDao
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import com.example.cryptomanager_v2.data.network.CryptoCompareApi
import com.example.cryptomanager_v2.data.network.ExchangeRatesApi
import com.example.cryptomanager_v2.utils.TestAppSchedulers
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
class TestAppModule {

    @Provides
    @Singleton
    fun providesAppSchedulers(): AppSchedulers {
        return TestAppSchedulers.get()
    }
}