package com.example.cryptomanager_v2.data.db.fiats

object DBFiatsBuilder {

    fun buildDBF(): List<DBFiat> {
        return listOf(
            buildDBFiat()
        )
    }

    fun buildDBFiat(): DBFiat {
        return DBFiat(name = "USD", rate = 1.0)
    }
}