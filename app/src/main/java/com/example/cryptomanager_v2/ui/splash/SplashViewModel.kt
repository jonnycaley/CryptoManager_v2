package com.example.cryptomanager_v2.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptomanager_v2.data.ExchangeRatesApi
import com.example.cryptomanager_v2.data.db.AppDatabase
import com.example.cryptomanager_v2.data.db.DBFiat
import com.example.cryptomanager_v2.data.model.ExchangeRates.ExchangeRatesOld
import com.example.cryptomanager_v2.utils.Resource
import com.example.cryptomanager_v2.utils.StateLiveData
import com.example.cryptomanager_v2.utils.Status
import com.example.cryptomanager_v2.utils.di.AppSchedulers
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.subjects.BehaviorSubject
import kotlin.math.sign

@SuppressLint("CheckResult")
class SplashViewModel(
    private val exchangeRatesApi: ExchangeRatesApi,
    private val schedulers: AppSchedulers,
    private val db: AppDatabase,
    private val gson: Gson
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _exchangeRates = MutableLiveData<Resource<Int>>()

    val exchangeRates: LiveData<Resource<Int>>
        get() = _exchangeRates

    init {
        getFiats()
    }

    fun getFiats() {
        db.fiatsDao().getAll()
            .doOnSubscribe {
                _exchangeRates.value = Resource.loading(null)
            }
            .observeOn(schedulers.mainThread)
            .filter {
                if(it.isEmpty()) {
                    true
                } else {
                    _exchangeRates
                    false
                }
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
            .subscribe ({
                _exchangeRates.value = Resource.success(it)
            },{
                _exchangeRates.value = Resource.error(msg = it.localizedMessage, data = null)
            })
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.dispose()
    }
}