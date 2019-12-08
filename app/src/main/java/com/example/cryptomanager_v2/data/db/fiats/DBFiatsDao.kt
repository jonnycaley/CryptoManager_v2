package com.example.cryptomanager_v2.data.db.fiats

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable

@Dao
interface DBFiatsDao {

    @Query("SELECT * FROM DBFiat")
    fun getAll(): LiveData<List<DBFiat>>

    @Insert
    fun insertAll(fiats: List<DBFiat>): Completable
}