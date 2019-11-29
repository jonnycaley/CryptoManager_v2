package com.example.cryptomanager_v2.data.db.exchanges

import com.example.cryptomanager_v2.data.db.exchanges.cryptos.DBExchangeCrypto

object DBExchangeBuilder {
    fun buildDB(): List<DBExchange> {
        return listOf(DBExchange(
            exchangeName = "Binance",
            cryptos = buildExchangeCrypto()
        ))
    }

    fun buildExchangeCrypto(): DBExchangeCrypto {
        return DBExchangeCrypto(cryptos = buildCryptoList())
    }

    private fun buildCryptoList(): List<Pair<String, List<String>>>? {
        return listOf(
            Pair("BTC", listOf("ETH", "LTC"))
        )
    }
}