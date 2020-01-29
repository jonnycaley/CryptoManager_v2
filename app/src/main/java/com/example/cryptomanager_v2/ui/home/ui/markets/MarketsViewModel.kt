package com.example.cryptomanager_v2.ui.home.ui.markets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptomanager_v2.data.model.coinmarketcap.CurrenciesDto
import com.example.cryptomanager_v2.data.model.coinmarketcap.market.MarketDto
import com.example.cryptomanager_v2.data.network.coinmarketcap.CoinMarketCapService
import javax.inject.Inject

class MarketsViewModel @Inject constructor(
    coinMarketCapService: CoinMarketCapService
): ViewModel() {

    private val _marketsData: MediatorLiveData<MarketsData> = MediatorLiveData<MarketsData>().apply { postValue(MarketsData()) }

    val marketsData: LiveData<MarketsData>
        get() = _marketsData

    init {
        _marketsData.addSource(coinMarketCapService.getTop100()) { top100 ->
            _marketsData.setValue(
                _marketsData.value?.copy(
                    top100 = top100.data
                )
            )
        }

        _marketsData.addSource(coinMarketCapService.getMarketInfo()) { marketInfo ->
            _marketsData.setValue(
                _marketsData.value?.copy(
                    marketDtoInfo = marketInfo.data
                )
            )
        }
    }
}

data class MarketsData(
    val top100: CurrenciesDto? = null,
    val marketDtoInfo: MarketDto? = null
)