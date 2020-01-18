package com.example.cryptomanager_v2.data.db.exchanges

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface DBExchangesDao {

    @Query("SELECT * FROM DBExchange")
    fun getAll(): Observable<List<DBExchange>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(exchanges: List<DBExchange>): Completable
}