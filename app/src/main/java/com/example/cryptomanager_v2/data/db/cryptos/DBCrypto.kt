package com.example.cryptomanager_v2.data.db.cryptos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBCrypto(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "imageUrl") val imageUrl: String?,
    @ColumnInfo(name = "contentCreatedOn") val contentCreatedOn: Int?,
    @ColumnInfo(name = "symbol") val symbol: String?,
    @ColumnInfo(name = "coinName") val coinName: String?,
    @ColumnInfo(name = "fullName") val fullName: String?,
    @ColumnInfo(name = "totalCoinSupply") val totalCoinSupply: String?
)