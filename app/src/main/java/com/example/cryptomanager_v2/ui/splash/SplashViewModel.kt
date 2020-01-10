package com.example.cryptomanager_v2.ui.splash

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptomanager_v2.data.db.cryptos.DBCrypto
import com.example.cryptomanager_v2.data.db.cryptos.DBCryptosDao
import com.example.cryptomanager_v2.data.db.exchanges.DBExchange
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangesDao
import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import com.example.cryptomanager_v2.data.network.cryptocompare.CryptoCompareService
import com.example.cryptomanager_v2.data.network.exchangerates.ExchangeRatesService
import com.example.cryptomanager_v2.utils.AppSchedulers
import com.example.cryptomanager_v2.utils.Resource
import com.example.cryptomanager_v2.utils.Status
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

@SuppressLint("CheckResult")
class SplashViewModel @Inject constructor(
    private val exchangeRatesService: ExchangeRatesService,
    private val schedulers: AppSchedulers,
    private val exchangesDao: DBExchangesDao,
    private val fiatsDao: DBFiatsDao,
    private val cryptosDao: DBCryptosDao,
    private val cryptoCompareService: CryptoCompareService
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
            if(arrayStates.any { it.status is Status.ERROR }) {
                val reason = (arrayStates.first { it.status is Status.ERROR }.status as Status.ERROR).reason
                return@combineLatest Status.ERROR(reason)
            }

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
        cryptoCompareService.getAllExchanges()
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.mainThread)
            .doOnSubscribe {
                exchangesSubject.onNext(Resource.loading())
            }
            .subscribe ({
                saveExchanges(it)
            },{
                exchangesSubject.onNext(Resource.error(it.localizedMessage))
            })
            .addTo(compositeDisposable)
    }
    private fun saveExchanges(it: List<DBExchange>) {
        exchangesDao.insertAll(it)
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.mainThread)
            .subscribe ({
                exchangesSubject.onNext(Resource.success())
            }, {
                exchangesSubject.onNext(Resource.error(it.localizedMessage))
            }).addTo(compositeDisposable)
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
        cryptoCompareService.getAllCrypto()
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.mainThread)
            .doOnSubscribe {
                cryptosSubject.onNext(Resource.loading())
            }
            .subscribe ({ saveCryptos(it) },{
                cryptosSubject.onNext(Resource.error(it.localizedMessage))
            })
            .addTo(compositeDisposable)
    }
    private fun saveCryptos(dbCryptos: List<DBCrypto>) {
        cryptosDao.insertAll(dbCryptos)
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.mainThread)
            .subscribe ({
                cryptosSubject.onNext(Resource.success())
            }, {
                cryptosSubject.onNext(Resource.error(it.localizedMessage))
            }).addTo(compositeDisposable)
    }

    private fun checkFiatsDB() {
        fiatsDao.getAll()
            .observeForever {
                if (it.isNotEmpty())
                    fiatsSubject.onNext(Resource.success())
                else
                    getFiats()
            }
    }

    fun retry() {
        if (fiatsSubject.value?.status is Status.ERROR)
            getFiats()
        if (exchangesSubject.value?.status is Status.ERROR)
            getExchanges()
        if (cryptosSubject.value?.status is Status.ERROR)
            getCryptos()
    }

    private fun getFiats() {
        exchangeRatesService.getFiats()
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.mainThread)
            .doOnSubscribe {
                fiatsSubject.onNext(Resource.loading())
            }
            .subscribe ({
                saveFiats(it)
            },{
                fiatsSubject.onNext(Resource.error(it.message ?: "An error occurred, please try again later."))
            })
            .addTo(compositeDisposable)
    }
    private fun saveFiats(dbFiats: List<DBFiat>) {
        fiatsDao.insertAll(dbFiats)
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.computation)
            .subscribe({
                fiatsSubject.onNext(Resource.success())
            },{
                fiatsSubject.onNext(Resource.error(it.message ?: "An error occurred, please try again later."))
            }).addTo(compositeDisposable)

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}