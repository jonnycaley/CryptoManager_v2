package com.example.cryptomanager_v2.data.db.cryptos

import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class DBCryptoService @Inject constructor(
    private val dbCryptosDao: DBCryptosDao
) {
    fun dbHasData(): Observable<Boolean> {
        return dbCryptosDao.getAll()
            .map { it.isNotEmpty() }
    }
    fun insertAll(dbCryptos: List<DBCrypto>): Completable {
        return dbCryptosDao.insertAll(dbCryptos)
    }
}