package com.example.cryptomanager_v2.data.network.exchangerates

import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import com.example.cryptomanager_v2.data.model.ExchangeRates.ExchangeRatesOld
import com.example.cryptomanager_v2.utils.AppSchedulers
import io.reactivex.Observable
import javax.inject.Inject

class ExchangeRatesService @Inject constructor(
    private val exchangeRatesApi: ExchangeRatesApi,
    private val schedulers: AppSchedulers
) {
    fun getFiats(): Observable<List<DBFiat>> {
        return exchangeRatesApi.getFiats()
            .observeOn(schedulers.computation)
            .map { exchangeRates ->
                ExchangeRatesOld.ratesToDBFiats(exchangeRates)
            }
    }
}