package com.example.cryptomanager_v2.utils.di.modules.network

import com.example.cryptomanager_v2.data.network.cryptocompare.CryptoCompareApi
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
object CryptoCompareModule {
    @Documented
    @Qualifier
    annotation class CRYPTOCOMPARE

    @Provides
    @Singleton
    @JvmStatic
    @CRYPTOCOMPARE
    fun provideCryptoCompareOkHttpClient(
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
    @CRYPTOCOMPARE
    internal fun provideCryptoCompareRetrofit(
        scalarsConverterFactory: ScalarsConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        @CRYPTOCOMPARE okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URL_CRYPTOCOMPARE)
            .addConverterFactory(scalarsConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideCryptoCompareApi(@CRYPTOCOMPARE retrofit: Retrofit): CryptoCompareApi {
        return retrofit.create(CryptoCompareApi::class.java)
    }
}