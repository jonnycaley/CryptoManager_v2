package com.example.cryptomanager_v2.utils.di.subcomponents.splash

import com.example.cryptomanager_v2.data.ExchangeRatesApi
import com.example.cryptomanager_v2.data.db.AppDatabase
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
        database: AppDatabase
    ) : SplashViewModelFactory {
        return SplashViewModelFactory(
            exchangeRatesApi,
            schedulers,
            database
        )
    }
}