package com.example.cryptomanager_v2.utils.mocks.db

import com.example.cryptomanager_v2.data.db.cryptos.DBCrypto
import com.example.cryptomanager_v2.data.db.cryptos.DBCryptosDao
import io.reactivex.Completable
import io.reactivex.Observable

class FakeDBCryptosDao: DBCryptosDao {

    val getAllResponses: ArrayList<Observable<List<DBCrypto>>> = arrayListOf()
    override fun getAll(): Observable<List<DBCrypto>> {
        return getAllResponses.removeAt(0)
    }

    val insertAllResponses: ArrayList<Completable> = arrayListOf()
    override fun insertAll(fiats: List<DBCrypto>): Completable {
        return insertAllResponses.removeAt(0)
    }
}