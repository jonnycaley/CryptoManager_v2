package com.example.cryptomanager_v2.utils.di.modules.network

import com.example.cryptomanager_v2.data.network.exchangerates.ExchangeRatesApi
import com.example.cryptomanager_v2.utils.Constants
import com.example.cryptomanager_v2.utils.NoInternetConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.lang.annotation.Documented
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
object ExchangeRatesModule {

    @Documented
    @Qualifier
    annotation class EXCHANGERATES

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
    internal fun provideExchangeRatesRetrofit(
        scalarsConverterFactory: ScalarsConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        @EXCHANGERATES okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_EXCHANGE_RATES)
            .addConverterFactory(scalarsConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }
}