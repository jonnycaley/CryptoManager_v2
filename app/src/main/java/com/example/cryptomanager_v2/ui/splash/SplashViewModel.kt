package com.example.cryptomanager_v2.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptomanager_v2.data.network.ExchangeRatesApi
import com.example.cryptomanager_v2.data.db.AppDatabase
import com.example.cryptomanager_v2.data.model.CryptoCompare.Crytpo.Crypto
import com.example.cryptomanager_v2.data.model.ExchangeRates.ExchangeRatesOld
import com.example.cryptomanager_v2.data.network.CryptoCompareApi
import com.example.cryptomanager_v2.utils.Resource
import com.example.cryptomanager_v2.utils.di.AppSchedulers
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables

@SuppressLint("CheckResult")
class SplashViewModel(
    private val exchangeRatesApi: ExchangeRatesApi,
    private val schedulers: AppSchedulers,
    private val db: AppDatabase,
    private val gson: Gson,
    private val cryptoCompareApi: CryptoCompareApi
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _exchangeRates = MutableLiveData<Resource<Int>>()
    private val _cryptos = MutableLiveData<Resource<Int>>()

    val exchangeRates: LiveData<Resource<Int>>
        get() = _exchangeRates

    val cryptos: LiveData<Resource<Int>>
        get() = _cryptos

    init {
        getFiats()
        getCryptos()
        getExchangeRates()
    }

    private fun getExchangeRates() {
    }

    private fun getCryptos() {

        cryptoCompareApi.getAllCrypto()
            .observeOn(schedulers.computation)
            .map { cryptoJson ->
                Crypto.cryptoToDBCryptos(gson, cryptoJson)
            }
            .observeOn(schedulers.mainThread)
            .observeOn(schedulers.computation)
            .flatMap { dbCrypto ->
                db.cryptosDao().insertAll(dbCrypto).toObservable<Int>()
            }
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.mainThread)
            .doOnSubscribe {
                _cryptos.value = Resource.loading(null)
            }
            .doOnComplete {
                _cryptos.value = Resource.success(null)
            }
            .doOnError {
                _cryptos.value = Resource.error(msg = it.localizedMessage, data = null)
            }
    }

    fun getFiats() {
        db.fiatsDao().getAll()
            .doOnSubscribe { _exchangeRates.value = Resource.loading(null) }
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
            .observeOn(schedulers.computation)
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