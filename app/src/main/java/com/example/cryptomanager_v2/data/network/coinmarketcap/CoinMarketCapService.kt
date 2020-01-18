package com.example.cryptomanager_v2.data.network.coinmarketcap

import com.example.cryptomanager_v2.data.model.coinmarketcap.Currencies
import com.example.cryptomanager_v2.utils.AppSchedulers
import javax.inject.Inject

class CoinMarketCapService @Inject constructor(
    private val schedulers: AppSchedulers,
    private val coinMarketCapApi: CoinMarketCapApi
){
    suspend fun getTop100(): Currencies {
        return coinMarketCapApi.getTopUSD()
    }
}