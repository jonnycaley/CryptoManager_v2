package com.example.cryptomanager_v2.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DBFiat::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun fiatsDao() : DBFiatsDao
}