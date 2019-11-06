package com.example.cryptomanager_v2.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cryptomanager_v2.data.db.cryptos.DBCrypto
import com.example.cryptomanager_v2.data.db.cryptos.DBCryptosDao
import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao

@Database(entities = [DBFiat::class, DBCrypto::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun fiatsDao(): DBFiatsDao
    abstract fun cryptosDao(): DBCryptosDao
}