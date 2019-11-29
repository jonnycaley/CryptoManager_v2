package com.example.cryptomanager_v2.data.model.cryptocompare.exchanges

import com.example.cryptomanager_v2.data.db.exchanges.cryptos.DBExchangeCrypto
import com.example.cryptomanager_v2.data.db.exchanges.DBExchange
import org.json.JSONException
import org.json.JSONTokener
import org.json.JSONObject

class Exchange {
    companion object {
        fun exchangesToDBExchanges(
            json: String
        ) : List<DBExchange>{
            return mutableListOf<DBExchange>().apply {
                try {
                    val jsonObject = JSONTokener(json).nextValue() as JSONObject
                    val keys = jsonObject.keys()

                    while (keys.hasNext()) {
                        val exchange = keys.next()
                        val innerJsonObject = jsonObject.getJSONObject(exchange)
                        val baseCryptos = innerJsonObject.keys()
                        val cryptoAndConversion = arrayListOf<Pair<String, List<String>>>()
                        while (baseCryptos.hasNext()) {
                            val baseCrypto = baseCryptos.next()
                            val conversionCryptos = innerJsonObject.getJSONArray(baseCrypto)

                            val conversionCryptosString = (0 until conversionCryptos.length())
                                .map { conversionCryptos.get(it) as String }

                            cryptoAndConversion.add(Pair(baseCrypto, conversionCryptosString))
                        }
                        add(DBExchange(exchange,
                            DBExchangeCrypto(
                                cryptoAndConversion
                            )
                        ))
                    }

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }.toList()
        }
    }
}