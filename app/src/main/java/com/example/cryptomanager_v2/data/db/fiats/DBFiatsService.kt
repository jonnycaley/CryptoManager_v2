package com.example.cryptomanager_v2.data.db.fiats

import io.reactivex.Completable
import javax.inject.Inject

class DBFiatsService @Inject constructor(
    private val dbFiatsDao: DBFiatsDao
) {
    fun hasFiatsData(): Boolean {
        return !dbFiatsDao.getAll().value.isNullOrEmpty()
    }

    fun insertAll(fiats: List<DBFiat>): Completable {
        return dbFiatsDao.insertAll(fiats)
    }
}