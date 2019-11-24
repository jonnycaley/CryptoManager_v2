package com.example.cryptomanager_v2.utils.mocks.db

import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import io.reactivex.Completable
import io.reactivex.Observable

class FakeDBFiatsDao: DBFiatsDao {

    val getAllResponses: ArrayList<Observable<List<DBFiat>>> = arrayListOf()
    override fun getAll(): Observable<List<DBFiat>> {
        return getAllResponses.removeAt(0)
    }

    val insertAllResponses: ArrayList<Completable> = arrayListOf()
    override fun insertAll(fiats: List<DBFiat>): Completable {
        return insertAllResponses.removeAt(0)
    }
}