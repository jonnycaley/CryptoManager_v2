package com.example.cryptomanager_v2.data.db.exchanges

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
data class DBExchange(
    @PrimaryKey val exchangeName: String,
    @TypeConverters(CryptosTypeConverter::class) val cryptos: Cryptos?
)