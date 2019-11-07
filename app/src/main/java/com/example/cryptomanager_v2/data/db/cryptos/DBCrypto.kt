package com.example.cryptomanager_v2.data.db.cryptos

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["id", "name"])
data class DBCrypto(
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String?,
    @ColumnInfo(name = "contentCreatedOn") val contentCreatedOn: Int?,
    @ColumnInfo(name = "symbol") val symbol: String?,
    @ColumnInfo(name = "coinName") val coinName: String?,
    @ColumnInfo(name = "fullName") val fullName: String?,
    @ColumnInfo(name = "totalCoinSupply") val totalCoinSupply: String?
)