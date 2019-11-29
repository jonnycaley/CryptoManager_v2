package com.example.cryptomanager_v2.utils.di.modules

import com.example.cryptomanager_v2.data.network.CryptoCompareApi
import com.example.cryptomanager_v2.data.network.ExchangeRatesApi
import com.example.cryptomanager_v2.utils.Constants
import com.example.cryptomanager_v2.utils.NoInternetConnectionInterceptor
import com.example.cryptomanager_v2.utils.AppSchedulers
import com.google.gson.Gson
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
    @Reusable
    @JvmStatic
    internal fun provideExchangeRatesApi(@EXCHANGERATES retrofit: Retrofit): ExchangeRatesApi {
        return retrofit.create(ExchangeRatesApi::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideCryptoCompareApi(@CRYPTOCOMPARE retrofit: Retrofit): CryptoCompareApi {
        return retrofit.create(CryptoCompareApi::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    @EXCHANGERATES
    internal fun provideExchangeRatesRetrofit(
        scalarsConverterFactory: ScalarsConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_EXCHANGE_RATES)
            .addConverterFactory(scalarsConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @JvmStatic
    @CRYPTOCOMPARE
    internal fun provideCryptoCompareRetrofit(
        scalarsConverterFactory: ScalarsConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_CRYPTOCOMPARE)
            .addConverterFactory(scalarsConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build()
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
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        noInternetConnectionInterceptor: NoInternetConnectionInterceptor
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                    .addQueryParameter(Constants.NAME_CRYPTOCOMPARE, Constants.KEY_CRYPTOCOMPARE)
                    .build()

            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }
        httpClient.addInterceptor(httpLoggingInterceptor)
        httpClient.addInterceptor(noInternetConnectionInterceptor)
        return httpClient.build()
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

    @Documented
    @Qualifier
    annotation class EXCHANGERATES

    @Documented
    @Qualifier
    annotation class CRYPTOCOMPARE
}