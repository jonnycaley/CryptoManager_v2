package com.example.cryptomanager_v2.data.db.fiats

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBFiat(
    @PrimaryKey val name: String,
    @ColumnInfo(name = "rate") val rate: Double,
    @ColumnInfo(name = "isBaseFiat") val isBaseFiat: Boolean
)