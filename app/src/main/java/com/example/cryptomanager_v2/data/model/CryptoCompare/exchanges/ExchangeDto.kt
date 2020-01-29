package com.example.cryptomanager_v2.data.model.cryptocompare.exchanges

import com.example.cryptomanager_v2.data.db.exchanges.DBExchange
import com.example.cryptomanager_v2.data.db.exchanges.cryptos.DBExchangeCrypto
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONTokener

class ExchangeDto {
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
                        val innerJsonObject = jsonObject.optJSONObject(exchange)
                        val baseCryptos = innerJsonObject?.keys()
                        val cryptoAndConversion = arrayListOf<Pair<String, List<String>>>()
                        while (baseCryptos != null && baseCryptos.hasNext()) {
                            val baseCrypto = baseCryptos.next()
                            val conversionCryptos = innerJsonObject.optJSONArray(baseCrypto)

                            conversionCryptos?.let { cryptos ->
                                val conversionCryptosString = (0 until cryptos.length())
                                    .map { conversionCryptos.opt(it) as String }

                                cryptoAndConversion.add(Pair(baseCrypto, conversionCryptosString))
                            }
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