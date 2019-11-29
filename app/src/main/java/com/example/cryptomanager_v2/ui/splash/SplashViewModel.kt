package com.example.cryptomanager_v2.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptomanager_v2.data.network.exchangerates.ExchangeRatesApi
import com.example.cryptomanager_v2.data.db.cryptos.DBCryptosDao
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangesDao
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import com.example.cryptomanager_v2.data.model.cryptocompare.crytpo.Crypto
import com.example.cryptomanager_v2.data.model.ExchangeRates.ExchangeRatesOld
import com.example.cryptomanager_v2.data.model.cryptocompare.exchanges.Exchange
import com.example.cryptomanager_v2.data.network.cryptocompare.CryptoCompareApi
import com.example.cryptomanager_v2.utils.Resource
import com.example.cryptomanager_v2.utils.Status
import com.example.cryptomanager_v2.utils.AppSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

@SuppressLint("CheckResult")
class SplashViewModel @Inject constructor(
    private val exchangeRatesApi: ExchangeRatesApi,
    private val schedulers: AppSchedulers,
    private val exchangesDao: DBExchangesDao,
    private val fiatsDao: DBFiatsDao,
    private val cryptosDao: DBCryptosDao,
    private val cryptoCompareApi: CryptoCompareApi
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _fiats = MutableLiveData<Resource<Int>>()
    private val _cryptos = MutableLiveData<Resource<Int>>()
    private val _exchanges = MutableLiveData<Resource<Int>>()

    private val _status = MutableLiveData<Status>()

    val status: LiveData<Status>
        get() = _status

    private var fiatsSubject = BehaviorSubject.createDefault<Resource<Int>>(Resource.idle())
    private var cryptosSubject = BehaviorSubject.createDefault<Resource<Int>>(Resource.idle())
    private var exchangesSubject = BehaviorSubject.createDefault<Resource<Int>>(Resource.idle())

    init {
        Observables.combineLatest(fiatsSubject, cryptosSubject, exchangesSubject) {
            exchangeRates, cryptos, exchanges ->

            val arrayStates = arrayOf(exchangeRates, cryptos, exchanges)

            //If any are loading: show Loading
            if(arrayStates.any { it.status is Status.LOADING })
                return@combineLatest Status.LOADING

            //If any are error: show Error
            if(arrayStates.any { it.status is Status.ERROR })
                return@combineLatest arrayStates.filterIsInstance<Status.ERROR>().first()

            //If all are success: show Success
            if(arrayStates.all { it.status is Status.SUCCESS })
                return@combineLatest Status.SUCCESS

            //Else show Idle
            return@combineLatest Status.IDLE
        }
            .subscribe {
                _status.value = it
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
            .addTo(compositeDisposable)
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
                exchangesSubject.onNext(Resource.error(it.localizedMessage))
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
            }.addTo(compositeDisposable)
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
                cryptosSubject.onNext(Resource.error(it.localizedMessage))
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
            }.addTo(compositeDisposable)
    }

    fun retry() {

        if(fiatsSubject.value?.status is Status.ERROR)
            getFiats()
        if(exchangesSubject.value?.status is Status.ERROR)
            getExchanges()
        if(cryptosSubject.value?.status is Status.ERROR)
            getCryptos()
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
                fiatsSubject.onNext(Resource.error(it.localizedMessage))
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