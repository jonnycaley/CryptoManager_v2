package com.example.cryptomanager_v2.data.db.cryptos

object DBCryptoBuilder {

    fun buildDB(): List<DBCrypto> {
        return listOf(
            buildDBCrypto()
        )
    }

    fun buildDBCrypto(): DBCrypto {
        return DBCrypto(
            id = "BTC",
            name = "Bitcoin",
            imageUrl = null,
            contentCreatedOn = null,
            symbol = "BTC",
            coinName = "Bitcoin",
            fullName = "Bitcoin",
            totalCoinSupply = "17,000,000"
        )
    }
}