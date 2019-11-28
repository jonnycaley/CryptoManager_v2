package com.example.cryptomanager_v2.utils.mocks.api

import com.example.cryptomanager_v2.data.network.CryptoCompareApi
import io.reactivex.Observable
import javax.inject.Inject

class FakeCryptoCompareApi @Inject constructor(): CryptoCompareApi {

    val getAllCryptoResponses: ArrayList<Observable<String>> = arrayListOf()
    override fun getAllCrypto(): Observable<String> {
        return getAllCryptoResponses.removeAt(0)
    }

    val getAllExchangesResponses: ArrayList<Observable<String>> = arrayListOf()
    override fun getAllExchanges(): Observable<String> {
        return getAllExchangesResponses.removeAt(0)
    }
}