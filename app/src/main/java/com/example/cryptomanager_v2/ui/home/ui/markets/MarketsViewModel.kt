package com.example.cryptomanager_v2.ui.home.ui.markets

import androidx.lifecycle.ViewModel
import com.example.cryptomanager_v2.data.model.coinmarketcap.Currencies
import com.example.cryptomanager_v2.data.network.coinmarketcap.CoinMarketCapService
import com.example.cryptomanager_v2.utils.AppSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MarketsViewModel @Inject constructor(
    private val coinMarketCapService: CoinMarketCapService,
    private val appSchedulers: AppSchedulers
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    init {
        getCryptos()
    }

    private fun getCryptos() {
        coinMarketCapService.getTop100()
            .subscribeOn(appSchedulers.io)
            .observeOn(appSchedulers.mainThread)
            .subscribe(::showTop100)
            .addTo(compositeDisposable)
    }

    private fun showTop100(top100: Currencies){

    }
}