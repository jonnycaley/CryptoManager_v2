package com.example.cryptomanager_v2.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptomanager_v2.data.network.ExchangeRatesApi
import com.example.cryptomanager_v2.data.db.cryptos.DBCryptosDao
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangeDao
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
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
    private val exchangesDao: DBExchangeDao,
    private val fiatsDao: DBFiatsDao,
    private val cryptosDao: DBCryptosDao,
    private val gson: Gson,
    private val cryptoCompareApi: CryptoCompareApi
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _fiats = MutableLiveData<Resource<Int>>()
    private val _cryptos = MutableLiveData<Resource<Int>>()
    private val _exchanges = MutableLiveData<Resource<Int>>()

    private val _isLoading = MutableLiveData<Boolean>()

    val loading: LiveData<Boolean>
        get() = _isLoading

    private var fiatsSubject = BehaviorSubject.createDefault<Resource<Int>>(Resource.idle())
    private var cryptosSubject = BehaviorSubject.createDefault<Resource<Int>>(Resource.idle())
    private var exchangesSubject = BehaviorSubject.createDefault<Resource<Int>>(Resource.idle())

    init {
        Observables.combineLatest(fiatsSubject, cryptosSubject, exchangesSubject) {
            exchangeRates, cryptos, exchanges ->
            exchangeRates.status == Status.LOADING || cryptos.status == Status.LOADING || exchanges.status == Status.LOADING
        }
            .subscribe {
                _isLoading.value = it
            }
            .addTo(compositeDisposable)

        fiatsSubject
            .subscribe {
                _fiats.value = it
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

        checkFiatsDB()
        checkCryptosDB()
        checkExchangesDB()
    }

    private fun checkExchangesDB() {

        exchangesDao.getAll()
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.mainThread)
            .doOnSubscribe {
                exchangesSubject.onNext(Resource.loading())
            }
            .subscribe {
                if(it.isNotEmpty())
                    exchangesSubject.onNext(Resource.success())
                else
                    getExchanges()
            }
    }

    private fun getExchanges() {

        cryptoCompareApi.getAllExchanges()
            .observeOn(schedulers.computation)
            .map {
                Exchange.exchangesToDBExchanges(it)
            }
            .flatMap {
                exchangesDao.insertAll(it).toObservable<Int>()
            }
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

    private fun checkCryptosDB() {

        cryptosDao.getAll()
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.mainThread)
            .doOnSubscribe {
                cryptosSubject.onNext(Resource.loading())
            }
            .subscribe {
                if(it.isNotEmpty())
                    cryptosSubject.onNext(Resource.success())
                else
                    getCryptos()
            }
    }

    private fun getCryptos() {

        cryptoCompareApi.getAllCrypto()
            .observeOn(schedulers.computation)
            .map { cryptoJson ->
                Crypto.cryptosToDBCryptos(cryptoJson)
            }
            .flatMap { dbCrypto ->
                cryptosDao.insertAll(dbCrypto).toObservable<Int>()
            }
            .subscribeOn(schedulers.io)
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

    private fun checkFiatsDB() {

        fiatsDao.getAll()
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.mainThread)
            .doOnSubscribe {
                fiatsSubject.onNext(Resource.loading())
            }
            .subscribe {
                if(it.isNotEmpty())
                    fiatsSubject.onNext(Resource.success())
                else
                    getFiats()
            }
    }

    fun getFiats() {

        exchangeRatesApi.getFiats()
            .observeOn(schedulers.computation)
            .map { exchangeRates ->
                ExchangeRatesOld.ratesToDBFiats(exchangeRates)
            }
            .flatMap { dbFiats ->
                fiatsDao.insertAll(dbFiats).toObservable<Int>()
            }
            .subscribeOn(schedulers.computation)
            .observeOn(schedulers.mainThread)
            .doOnSubscribe {
                fiatsSubject.onNext(Resource.loading())
            }
            .subscribe ({
            },{
                fiatsSubject.onNext(Resource.error(it.message))
            }, {
                fiatsSubject.onNext(Resource.success())
            })
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}