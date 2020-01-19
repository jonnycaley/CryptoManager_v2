package com.example.cryptomanager_v2.ui.home.ui.markets

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cryptomanager_v2.data.model.coinmarketcap.Currencies
import com.example.cryptomanager_v2.data.network.coinmarketcap.CoinMarketCapService
import com.example.cryptomanager_v2.utils.Resource
import javax.inject.Inject

class MarketsViewModel @Inject constructor(
    private val coinMarketCapService: CoinMarketCapService
): ViewModel() {

    val top100: LiveData<Resource<Currencies>> = coinMarketCapService.getTop100()
}