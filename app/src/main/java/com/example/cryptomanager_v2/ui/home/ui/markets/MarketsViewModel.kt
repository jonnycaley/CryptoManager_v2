package com.example.cryptomanager_v2.ui.home.ui.markets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptomanager_v2.data.model.coinmarketcap.Currencies
import com.example.cryptomanager_v2.data.model.coinmarketcap.market.Market
import com.example.cryptomanager_v2.data.network.coinmarketcap.CoinMarketCapService
import com.example.cryptomanager_v2.utils.Resource
import javax.inject.Inject

class MarketsViewModel @Inject constructor(
    coinMarketCapService: CoinMarketCapService
): ViewModel() {

    private val _marketsData = MediatorLiveData<MarketsData>().apply { postValue(MarketsData()) }

    val marketsData: LiveData<MarketsData>
        get() = _marketsData

    init {
        _marketsData.addSource(coinMarketCapService.getTop100()) { top100 ->
            _marketsData.setValue(
                _marketsData.value?.copy(
                    top100 = top100
                )
            )
        }

        _marketsData.addSource(coinMarketCapService.getMarketInfo()) { marketInfo ->
            _marketsData.setValue(
                _marketsData.value?.copy(
                    marketInfo = marketInfo
                )
            )
        }
    }
}

data class MarketsData(
    val top100: Resource<Currencies> = Resource.idle(),
    val marketInfo: Resource<Market> = Resource.idle()
)