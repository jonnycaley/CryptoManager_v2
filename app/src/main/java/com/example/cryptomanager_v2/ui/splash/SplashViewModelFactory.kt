package com.example.cryptomanager_v2.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptomanager_v2.data.network.ExchangeRatesApi
import com.example.cryptomanager_v2.data.db.AppDatabase
import com.example.cryptomanager_v2.data.db.cryptos.DBCryptosDao
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangeDao
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import com.example.cryptomanager_v2.data.network.CryptoCompareApi
import com.example.cryptomanager_v2.utils.di.AppSchedulers
import com.google.gson.Gson
import javax.inject.Inject

class SplashViewModelFactory @Inject constructor(
    private val exchangeRatesApi: ExchangeRatesApi,
    private val schedulers: AppSchedulers,
    private val exchangesDao: DBExchangeDao,
    private val fiatsDao: DBFiatsDao,
    private val cryptosDao: DBCryptosDao,
    private val cryptoCompareApi: CryptoCompareApi
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(
                exchangeRatesApi,
                schedulers,
                exchangesDao,
                fiatsDao,
                cryptosDao,
                cryptoCompareApi
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}