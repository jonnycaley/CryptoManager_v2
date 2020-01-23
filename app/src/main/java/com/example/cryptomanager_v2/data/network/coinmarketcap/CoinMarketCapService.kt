package com.example.cryptomanager_v2.data.network.coinmarketcap

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.cryptomanager_v2.data.model.coinmarketcap.Currencies
import com.example.cryptomanager_v2.data.model.coinmarketcap.market.Market
import com.example.cryptomanager_v2.utils.Resource
import javax.inject.Inject

class CoinMarketCapService @Inject constructor(
    private val coinMarketCapApi: CoinMarketCapApi
){
    fun getTop100(): LiveData<Resource<Currencies>> = liveData {
        emit(Resource.loading())
        try {
            val data = coinMarketCapApi.getTopUSD()
            emit(Resource.success(data))
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "An error occurred"))
        }
    }

    fun getMarketInfo(): LiveData<Resource<Market>> = liveData {
        emit(Resource.loading())
        try {
            val data = coinMarketCapApi.getMarketData()
            emit(Resource.success(data))
        } catch (e: Exception) {
            emit(Resource.error(e.message ?: "An error occurred"))
        }
    }
}