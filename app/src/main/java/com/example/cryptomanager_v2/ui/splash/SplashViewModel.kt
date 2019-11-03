package com.example.cryptomanager_v2.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptomanager_v2.data.ExchangeRatesApi
import com.example.cryptomanager_v2.data.model.ExchangeRates.ExchangeRatesOld
import com.example.cryptomanager_v2.utils.di.AppSchedulers
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class SplashViewModel(
    private val exchangeRatesApi: ExchangeRatesApi,
    private val schedulers: AppSchedulers
): ViewModel() {

    private val _exchangeRates = MutableLiveData<ExchangeRatesOld>()

    val exchangeRates: LiveData<ExchangeRatesOld>
        get() = _exchangeRates

    init {
        loadExchangeRates()
    }

    fun loadExchangeRates() {

        exchangeRatesApi.getExchangeRates()
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.mainThread)
            .subscribe({ exchangeRates ->
                _exchangeRates.value = exchangeRates
            },{ error ->
                println(error.message)
            })
    }

    override fun onCleared() {
        super.onCleared()
        // dispose of disposables
    }
}