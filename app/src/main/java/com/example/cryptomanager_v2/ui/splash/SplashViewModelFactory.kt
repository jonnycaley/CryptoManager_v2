package com.example.cryptomanager_v2.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptomanager_v2.data.ExchangeRatesApi
import com.example.cryptomanager_v2.data.db.AppDatabase
import com.example.cryptomanager_v2.utils.di.AppSchedulers
import com.google.gson.Gson
import javax.inject.Inject

class SplashViewModelFactory @Inject constructor(
    private val exchangeRatesApi: ExchangeRatesApi,
    private val schedulers: AppSchedulers,
    private val database: AppDatabase,
    private val gson: Gson
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(
                exchangeRatesApi,
                schedulers,
                database,
                gson
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}