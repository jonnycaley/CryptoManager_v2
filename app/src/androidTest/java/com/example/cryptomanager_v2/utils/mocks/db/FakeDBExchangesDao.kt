package com.example.cryptomanager_v2.utils.mocks.db

import com.example.cryptomanager_v2.data.db.exchanges.DBExchange
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangeDao
import io.reactivex.Completable
import io.reactivex.Observable

class FakeDBExchangesDao: DBExchangeDao {

    val getAllResponses: ArrayList<Observable<List<DBExchange>>> = arrayListOf()
    override fun getAll(): Observable<List<DBExchange>> {
        return getAllResponses.removeAt(0)
    }

    val insertAllResponses: ArrayList<Completable> = arrayListOf()
    override fun insertAll(exchanges: List<DBExchange>): Completable {
        return insertAllResponses.removeAt(0)
    }
}