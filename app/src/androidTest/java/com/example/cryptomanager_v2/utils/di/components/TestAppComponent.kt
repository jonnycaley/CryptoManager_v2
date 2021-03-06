package com.example.cryptomanager_v2.utils.di.components

import com.example.cryptomanager_v2.data.db.cryptos.DBCryptosDao
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangesDao
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import com.example.cryptomanager_v2.data.network.cryptocompare.CryptoCompareApi
import com.example.cryptomanager_v2.data.network.exchangerates.ExchangeRatesApi
import com.example.cryptomanager_v2.utils.TestApp
import com.example.cryptomanager_v2.utils.di.modules.TestAppModule
import com.example.cryptomanager_v2.utils.di.ActivityBuilder
import com.example.cryptomanager_v2.utils.di.modules.FakeDatabaseModule
import com.example.cryptomanager_v2.utils.di.modules.FakeNetworkModule
import com.example.cryptomanager_v2.utils.di.modules.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FakeNetworkModule::class,
        FakeDatabaseModule::class,
        TestAppModule::class,
        ActivityBuilder::class,
        ViewModelModule::class,
        AndroidInjectionModule::class
    ]
)
interface TestAppComponent : AndroidInjector<TestApp> {
    fun dbExhangeDao(): DBExchangesDao
    fun dbFiatsDao(): DBFiatsDao
    fun dbCryptosDao(): DBCryptosDao
    fun exchangeRatesApi(): ExchangeRatesApi
    fun cryptoCompareApi(): CryptoCompareApi
}