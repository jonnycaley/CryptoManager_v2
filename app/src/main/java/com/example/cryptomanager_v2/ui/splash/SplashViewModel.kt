package com.example.cryptomanager_v2.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptomanager_v2.data.ExchangeRatesApi
import com.example.cryptomanager_v2.data.db.AppDatabase
import com.example.cryptomanager_v2.data.model.ExchangeRates.ExchangeRatesOld
import com.example.cryptomanager_v2.utils.di.AppSchedulers
import io.reactivex.disposables.CompositeDisposable

@SuppressLint("CheckResult")
class SplashViewModel(
    private val exchangeRatesApi: ExchangeRatesApi,
    private val schedulers: AppSchedulers,
    private val db: AppDatabase
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _exchangeRates = MutableLiveData<ExchangeRatesOld>()

    val exchangeRates: LiveData<ExchangeRatesOld>
        get() = _exchangeRates

    init {
        checkInternalStorage()
    }

    fun checkInternalStorage() {
        db.fiatsDao().getAll()
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.mainThread)
            .subscribe ({ fiats ->
                if(fiats.isEmpty()) {
                    loadData()
                } else {
                    // to next activity
                }
            },{
                println("onError: ${it.message}")
            })
    }

    private fun loadData() {
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

        compositeDisposable.dispose()
    }
}