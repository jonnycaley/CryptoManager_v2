package com.example.cryptomanager_v2.data.db.exchanges

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBExchange(
    @PrimaryKey val exchangeName: String,
    @ColumnInfo val cryptos: List<Pair<String, List<String>>>
)