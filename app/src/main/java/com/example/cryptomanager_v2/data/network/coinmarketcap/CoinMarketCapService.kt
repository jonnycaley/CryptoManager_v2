package com.example.cryptomanager_v2.data.network.coinmarketcap

import com.example.cryptomanager_v2.data.model.coinmarketcap.Currencies
import com.example.cryptomanager_v2.utils.AppSchedulers
import io.reactivex.Observable
import javax.inject.Inject

class CoinMarketCapService @Inject constructor(
    private val schedulers: AppSchedulers,
    private val coinMarketCapApi: CoinMarketCapApi
){
    fun getTop100(): Observable<Currencies> {
        return coinMarketCapApi.getTopUSD()
    }
}