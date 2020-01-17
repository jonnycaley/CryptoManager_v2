package com.example.cryptomanager_v2.ui.home.ui.markets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptomanager_v2.data.model.coinmarketcap.Currencies
import com.example.cryptomanager_v2.data.network.coinmarketcap.CoinMarketCapService
import com.example.cryptomanager_v2.utils.AppSchedulers
import com.example.cryptomanager_v2.utils.Resource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MarketsViewModel @Inject constructor(
    private val coinMarketCapService: CoinMarketCapService,
    private val appSchedulers: AppSchedulers
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _marketsData = MutableLiveData<Resource<Currencies>>()
    val marketsData: LiveData<Resource<Currencies>>
        get() = _marketsData

    init {
        getCryptos()
    }

    private fun getCryptos() {
        coinMarketCapService.getTop100()
            .subscribeOn(appSchedulers.io)
            .observeOn(appSchedulers.mainThread)
            .subscribe { top100 ->
                _marketsData.value = Resource.success(top100)
            }
            .addTo(compositeDisposable)
    }
}