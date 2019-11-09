package com.example.cryptomanager_v2.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptomanager_v2.data.network.ExchangeRatesApi
import com.example.cryptomanager_v2.data.db.AppDatabase
import com.example.cryptomanager_v2.data.model.cryptocompare.crytpo.Crypto
import com.example.cryptomanager_v2.data.model.ExchangeRates.ExchangeRatesOld
import com.example.cryptomanager_v2.data.model.cryptocompare.exchanges.Exchange
import com.example.cryptomanager_v2.data.network.CryptoCompareApi
import com.example.cryptomanager_v2.utils.Resource
import com.example.cryptomanager_v2.utils.Status
import com.example.cryptomanager_v2.utils.di.AppSchedulers
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject

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
    private val _exchanges = MutableLiveData<Resource<Int>>()

    private val _isLoading = MutableLiveData<Boolean>()

    val loading: LiveData<Boolean>
        get() = _isLoading

    var exchangeRatesSubject = BehaviorSubject.createDefault<Resource<Int>>(Resource.idle())
    var cryptosSubject = BehaviorSubject.createDefault<Resource<Int>>(Resource.idle())
    var exchangesSubject = BehaviorSubject.createDefault<Resource<Int>>(Resource.idle())

    init {
        Observables.combineLatest(exchangeRatesSubject, cryptosSubject, exchangesSubject) {
            exchangeRates, cryptos, exchanges ->
            exchangeRates.status == Status.LOADING || cryptos.status == Status.LOADING || exchanges.status == Status.LOADING
        }
            .subscribe {
            _isLoading.value = it
            }
            .addTo(compositeDisposable)

        exchangeRatesSubject
            .subscribe {
                _exchangeRates.value = it
            }
            .addTo(compositeDisposable)

        cryptosSubject
            .subscribe {
                _cryptos.value = it
            }
            .addTo(compositeDisposable)

        exchangesSubject
            .subscribe {
                _exchanges.value = it
            }
            .addTo(compositeDisposable)

        getFiats()
        getCryptos()
        getExchangeRates()
    }

    private fun getExchangeRates() {

        db.exchangesDao().getAll()
            .observeOn(schedulers.mainThread)
            .doOnNext {
                if(it.isNotEmpty()) {
                    exchangesSubject.onNext(Resource.success())
                }
            }
            .filter {
                it.isEmpty()
            }
            .observeOn(schedulers.io)
            .flatMap { cryptoCompareApi.getAllExchanges() }
            .observeOn(schedulers.computation)
            .map { Exchange.exchangesToDBExchanges(gson, it) }
            .flatMap { db.exchangesDao().insertAll(it).toObservable<Int>() }
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.mainThread)
            .doOnSubscribe {
                exchangesSubject.onNext(Resource.loading())
            }
            .subscribe ({ exchanges ->

            },{
                exchangesSubject.onNext(Resource.error(it.message))
            },{
                exchangesSubject.onNext(Resource.success())
            })
            .addTo(compositeDisposable)
    }

    private fun getCryptos() {

        db.cryptosDao().getAll()
            .observeOn(schedulers.mainThread)
            .doOnNext {
                if(it.isNotEmpty()) {
                    cryptosSubject.onNext(Resource.success())
                }
            }
            .filter {
                it.isEmpty()
            }
            .observeOn(schedulers.io)
            .flatMap {
                cryptoCompareApi.getAllCrypto()
            }
            .observeOn(schedulers.computation)
            .map { cryptoJson ->
                Crypto.cryptoToDBCryptos(gson, cryptoJson)
            }
            .flatMap { dbCrypto ->
                db.cryptosDao().insertAll(dbCrypto).toObservable<Int>()
            }
            .subscribeOn(schedulers.computation)
            .observeOn(schedulers.mainThread)
            .doOnSubscribe {
                cryptosSubject.onNext(Resource.loading())
            }
            .subscribe ({

            },{
                cryptosSubject.onNext(Resource.error(it.message))
            },{
                cryptosSubject.onNext(Resource.success())
            })
            .addTo(compositeDisposable)
    }

    fun getFiats() {
        db.fiatsDao().getAll()
            .observeOn(schedulers.mainThread)
            .doOnNext {
                if(it.isNotEmpty()) {
                    exchangesSubject.onNext(Resource.success())
                }
            }
            .filter {
                it.isEmpty()
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
            .subscribeOn(schedulers.computation)
            .observeOn(schedulers.mainThread)
            .doOnSubscribe {
                exchangesSubject.onNext(Resource.loading())
            }
            .subscribe ({

            },{
                exchangesSubject.onNext(Resource.error(it.message))
            }, {
                exchangesSubject.onNext(Resource.success())
            })
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}