package com.example.cryptomanager_v2.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface DBFiatsDao {

    @Query("SELECT * FROM DBFiat")
    fun getAll(): Observable<List<DBFiat>>

    @Insert
    fun insertAll(fiats: List<DBFiat>): Completable
}