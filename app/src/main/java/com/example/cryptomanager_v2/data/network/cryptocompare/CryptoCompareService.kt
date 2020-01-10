package com.example.cryptomanager_v2.data.network.cryptocompare

import com.example.cryptomanager_v2.data.db.cryptos.DBCrypto
import com.example.cryptomanager_v2.data.db.exchanges.DBExchange
import com.example.cryptomanager_v2.data.model.cryptocompare.crytpo.Crypto
import com.example.cryptomanager_v2.data.model.cryptocompare.exchanges.Exchange
import com.example.cryptomanager_v2.utils.AppSchedulers
import io.reactivex.Observable
import javax.inject.Inject

class CryptoCompareService @Inject constructor(
    private var cryptoCompareApi: CryptoCompareApi,
    private val schedulers: AppSchedulers
) {
    fun getAllExchanges(): Observable<List<DBExchange>> {
        return cryptoCompareApi.getAllExchanges()
            .observeOn(schedulers.computation)
            .map { Exchange.exchangesToDBExchanges(it) }
    }

    fun getAllCrypto(): Observable<List<DBCrypto>> {
        return cryptoCompareApi.getAllCrypto()
            .observeOn(schedulers.computation)
            .map { cryptoJson -> Crypto.cryptosToDBCryptos(cryptoJson) }
    }
}