package com.example.cryptomanager_v2.data.db.cryptos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface DBCryptosDao {

    @Query("SELECT * FROM DBCrypto")
    fun getAll(): Observable<List<DBCrypto>>

    @Insert
    fun insertAll(fiats: List<DBCrypto>): Completable
}