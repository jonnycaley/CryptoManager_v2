package com.example.cryptomanager_v2.utils.di.subcomponents.splash

import com.example.cryptomanager_v2.data.network.ExchangeRatesApi
import com.example.cryptomanager_v2.data.db.cryptos.DBCryptosDao
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangesDao
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import com.example.cryptomanager_v2.data.network.CryptoCompareApi
import com.example.cryptomanager_v2.ui.splash.SplashViewModelFactory
import com.example.cryptomanager_v2.utils.di.AppSchedulers
import com.example.cryptomanager_v2.utils.di.scopes.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {

    @Provides
    @ActivityScope
    fun providesSplashViewModelFactory(
        exchangeRatesApi: ExchangeRatesApi,
        schedulers: AppSchedulers,
        exchangesDao: DBExchangesDao,
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