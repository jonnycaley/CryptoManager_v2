package com.example.cryptomanager_v2.utils.di.modules

import com.example.cryptomanager_v2.data.ExchangeRatesApi
import com.example.cryptomanager_v2.ui.splash.SplashViewModelFactory
import com.example.cryptomanager_v2.utils.di.AppSchedulers
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @Provides
    fun providesSplashViewModelFactory(
        exchangeRatesApi: ExchangeRatesApi,
        schedulers: AppSchedulers
    ) : SplashViewModelFactory {
        return SplashViewModelFactory(
            exchangeRatesApi,
            schedulers
        )
    }
}