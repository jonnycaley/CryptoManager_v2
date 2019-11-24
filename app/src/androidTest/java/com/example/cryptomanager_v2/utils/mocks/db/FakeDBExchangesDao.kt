package com.example.cryptomanager_v2.utils.mocks.db

import com.example.cryptomanager_v2.data.db.exchanges.DBExchange
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangeDao
import io.reactivex.Completable
import io.reactivex.Observable

class FakeDBExchangesDao: DBExchangeDao {

    private val responses: ArrayList<Any> = arrayListOf()

    override fun getAll(): Observable<List<DBExchange>> {
        return responses.first() as Observable<List<DBExchange>>
    }

    override fun insertAll(exchanges: List<DBExchange>): Completable {
        return responses.first() as Completable
    }
}