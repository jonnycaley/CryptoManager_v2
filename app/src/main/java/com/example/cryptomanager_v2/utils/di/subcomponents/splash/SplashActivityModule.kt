package com.example.cryptomanager_v2.utils.di.subcomponents.splash

import com.example.cryptomanager_v2.data.network.ExchangeRatesApi
import com.example.cryptomanager_v2.data.db.AppDatabase
import com.example.cryptomanager_v2.data.db.cryptos.DBCryptosDao
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangeDao
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import com.example.cryptomanager_v2.data.network.CryptoCompareApi
import com.example.cryptomanager_v2.ui.splash.SplashViewModelFactory
import com.example.cryptomanager_v2.utils.di.AppSchedulers
import com.example.cryptomanager_v2.utils.di.scopes.ActivityScope
import com.google.gson.Gson
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {

    @Provides
    @ActivityScope
    fun providesSplashViewModelFactory(
        exchangeRatesApi: ExchangeRatesApi,
        schedulers: AppSchedulers,
        exchangesDao: DBExchangeDao,
        fiatsDao: DBFiatsDao,
        cryptosDao: DBCryptosDao,
        cryptoCompareApi: CryptoCompareApi
    ) : SplashViewModelFactory {
        return SplashViewModelFactory(
            exchangeRatesApi,
            schedulers,
            exchangesDao,
            fiatsDao,
            cryptosDao,
            cryptoCompareApi
        )
    }
}