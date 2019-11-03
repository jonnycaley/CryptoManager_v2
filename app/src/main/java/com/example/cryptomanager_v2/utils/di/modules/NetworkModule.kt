package com.example.cryptomanager_v2.utils.di.modules

import android.util.Log
import com.example.cryptomanager_v2.data.ExchangeRatesApi
import com.example.cryptomanager_v2.utils.di.AppSchedulers
import com.example.cryptomanager_v2.utils.di.Constants
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.annotation.Documented
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideExchangeRatesApi(@EXCHANGERATES retrofit: Retrofit): ExchangeRatesApi {
        return retrofit.create(ExchangeRatesApi::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    @EXCHANGERATES
    internal fun provideExchangeRatesRetrofit(schedulers: AppSchedulers): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_EXCHANGE_RATES)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(schedulers.io))
            .build()
    }

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideSchedulers(): AppSchedulers {
        return AppSchedulers.get()
    }

    @Documented
    @Qualifier
    annotation class EXCHANGERATES
}