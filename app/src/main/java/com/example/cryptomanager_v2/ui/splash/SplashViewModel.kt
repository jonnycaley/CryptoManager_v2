package com.example.cryptomanager_v2.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptomanager_v2.data.ExchangeRatesApi
import com.example.cryptomanager_v2.data.db.AppDatabase
import com.example.cryptomanager_v2.data.model.ExchangeRates.ExchangeRatesOld
import com.example.cryptomanager_v2.utils.di.AppSchedulers
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable

@SuppressLint("CheckResult")
class SplashViewModel(
    private val exchangeRatesApi: ExchangeRatesApi,
    private val schedulers: AppSchedulers,
    private val db: AppDatabase,
    private val gson: Gson
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _exchangeRates = MutableLiveData<Int>()

    val exchangeRates: LiveData<Int>
        get() = _exchangeRates

    init {
        getFiats()
    }

    fun getFiats() {

        db.fiatsDao().getAll()
            .observeOn(schedulers.mainThread)
            .filter {
                if(it.isEmpty()){
                    return@filter true
                }
                _exchangeRates.value = it.size
                println("")
                return@filter false
            }
            .observeOn(schedulers.io)
            .flatMap {
                exchangeRatesApi.getExchangeRates().toObservable()
            }
            .map { exchangeRates ->
                ExchangeRatesOld.ratesToDBFiats(gson, exchangeRates)
            }
            .flatMap { dbFiats ->
                db.fiatsDao().insertAll(dbFiats).toObservable<Int>()
            }
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.mainThread)
            .subscribe ({ fiats ->
                _exchangeRates.value = fiats
            },{
                println("onError: ${it.message}")
            }, {
                println("onComplete")
            })
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.dispose()
    }
}