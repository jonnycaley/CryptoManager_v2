package com.example.cryptomanager_v2.data.model.cryptocompare.exchanges

import com.example.cryptomanager_v2.data.db.exchanges.DBExchange
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONTokener
import org.json.JSONObject

class Exchange {
    companion object {
        fun exchangesToDBExchanges(
            gson: Gson,
            json: String
        ) : List<DBExchange>{
            return arrayListOf<DBExchange>().apply {
                try {
                    val jsonObject = JSONTokener(json).nextValue() as JSONObject
                    val keys = jsonObject.keys()

                    while (keys.hasNext()) {
                        val exchange = keys.next()
                        val innerJsonObject = jsonObject.getString(exchange)

                        val jsonObject2 = JSONTokener(innerJsonObject).nextValue() as JSONObject
                        val baseCryptos = jsonObject2.keys()
                        val cryptoAndConversion = mutableListOf<Pair<String, List<String>>>()
                        while (baseCryptos.hasNext()) {
                            val baseCrypto = baseCryptos.next()
                            val newObj = jsonObject2.getString(baseCrypto)
                            val conversionCryptos = gson.fromJson(jsonObject2.getString(baseCrypto), mutableListOf<String>()::class.java)
                            cryptoAndConversion.add(Pair(baseCrypto, conversionCryptos))
                        }
                        add(DBExchange(exchange, cryptoAndConversion))
                    }

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }.toList()
        }
    }
}