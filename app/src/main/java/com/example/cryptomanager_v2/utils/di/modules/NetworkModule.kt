package com.example.cryptomanager_v2.utils.di.modules

import com.example.cryptomanager_v2.utils.AppSchedulers
import com.example.cryptomanager_v2.utils.di.modules.network.CoinMarketCapModule
import com.example.cryptomanager_v2.utils.di.modules.network.CryptoCompareModule
import com.example.cryptomanager_v2.utils.di.modules.network.ExchangeRatesModule
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module(includes = [
    CoinMarketCapModule::class,
    CryptoCompareModule::class,
    ExchangeRatesModule::class
])
object NetworkModule {

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideRxJava2CallAdapterFactory(
        schedulers: AppSchedulers
    ): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.createWithScheduler(schedulers.io)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun providesScalarsConverterFactory(): ScalarsConverterFactory {
        return ScalarsConverterFactory.create()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideLoggingInterceptor() : HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    @JvmStatic
    internal fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideSchedulers(): AppSchedulers {
        return AppSchedulers.get()
    }
}