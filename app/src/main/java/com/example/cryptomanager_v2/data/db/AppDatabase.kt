package com.example.cryptomanager_v2.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cryptomanager_v2.data.db.cryptos.DBCrypto
import com.example.cryptomanager_v2.data.db.cryptos.DBCryptosDao
import com.example.cryptomanager_v2.data.db.exchanges.CryptosTypeConverter
import com.example.cryptomanager_v2.data.db.exchanges.DBExchange
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangesDao
import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao

@Database(entities = [DBFiat::class, DBCrypto::class, DBExchange::class], version = 1, exportSchema = false)
@TypeConverters(CryptosTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun fiatsDao(): DBFiatsDao
    abstract fun cryptosDao(): DBCryptosDao
    abstract fun exchangesDao(): DBExchangesDao
}