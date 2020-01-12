package com.example.cryptomanager_v2.data.db.exchanges

import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class DBExchangesService @Inject constructor(
    private val exchangesDao: DBExchangesDao
) {

    fun dbHasData(): Observable<Boolean> {
        return exchangesDao.getAll()
            .map { it.isNotEmpty() }
    }

    fun insertAll(exchanges: List<DBExchange>): Completable {
        return exchangesDao.insertAll(exchanges)
    }

}