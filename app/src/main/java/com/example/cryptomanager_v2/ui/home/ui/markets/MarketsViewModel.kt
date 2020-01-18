package com.example.cryptomanager_v2.ui.home.ui.markets

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.cryptomanager_v2.data.model.coinmarketcap.Currencies
import com.example.cryptomanager_v2.data.network.coinmarketcap.CoinMarketCapService
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MarketsViewModel @Inject constructor(
    private val coinMarketCapService: CoinMarketCapService
): ViewModel() {

    val marketsData: LiveData<Currencies> = liveData(Dispatchers.IO) {
        val data = coinMarketCapService.getTop100()
        emit(data)
    }
}