package com.example.cryptomanager_v2.data.db.fiats

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable

@Dao
interface DBFiatsDao {

    @Query("SELECT * FROM DBFiat")
    fun getAll(): LiveData<List<DBFiat>>

    @Query("SELECT * FROM DBFiat WHERE isBaseFiat = 1")
    fun getBaseFiat(): LiveData<DBFiat>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(fiats: List<DBFiat>): Completable

    @Query("UPDATE DBFiat SET isBaseFiat = 0 WHERE name != :fiatName")
    fun resetFiats(fiatName: String)

    @Query("UPDATE DBFiat SET isBaseFiat = 1 WHERE name = :fiatName")
    fun setBaseFiat(fiatName: String)
}