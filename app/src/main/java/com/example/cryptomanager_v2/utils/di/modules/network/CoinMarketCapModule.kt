package com.example.cryptomanager_v2.utils.di.modules.network

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.cryptomanager_v2.data.network.coinmarketcap.CoinMarketCapApi
import com.example.cryptomanager_v2.utils.Constants
import com.example.cryptomanager_v2.utils.NoInternetConnectionInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.annotation.Documented
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
object CoinMarketCapModule {

    @Documented
    @Qualifier
    annotation class COINMARKETCAP

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideCoinMarketCapApi(@COINMARKETCAP retrofit: Retrofit): CoinMarketCapApi {
        return retrofit.create(CoinMarketCapApi::class.java)
    }

    @Provides
    @Singleton
    @JvmStatic
    @COINMARKETCAP
    internal fun provideCoinMarketCapRetrofit(
        moshiConverterFactory: MoshiConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        @COINMARKETCAP okHttpClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(Constants.URL_COINMARKETCAP)
            .addConverterFactory(moshiConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @JvmStatic
    @COINMARKETCAP
    fun provideCoinMarketCapOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        noInternetConnectionInterceptor: NoInternetConnectionInterceptor,
        chuckerInterceptor: ChuckerInterceptor
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(Constants.NAME_COINMARKETCAP, Constants.KEY_COINMARKETCAP)
                .build()

            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }
        httpClient.addInterceptor(httpLoggingInterceptor)
        httpClient.addInterceptor(noInternetConnectionInterceptor)
        httpClient.addInterceptor(chuckerInterceptor)
        return httpClient.build()
    }
}