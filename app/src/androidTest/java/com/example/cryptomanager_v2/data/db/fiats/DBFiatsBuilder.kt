package com.example.cryptomanager_v2.data.db.fiats

object DBFiatsBuilder {

    fun buildDB(): List<DBFiat> {
        return listOf(
            buildDBFiat()
        )
    }

    private fun buildDBFiat(): DBFiat {
        return DBFiat(name = "USD", rate = 1.0, isBaseFiat = true)
    }
}