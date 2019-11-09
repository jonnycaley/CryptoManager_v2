package com.example.cryptomanager_v2.data.model.cryptocompare.exchanges

import com.example.cryptomanager_v2.data.db.exchanges.Cryptos
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
                        val cryptoAndConversion = arrayListOf<Pair<String, ArrayList<String>>>()
                        while (baseCryptos.hasNext()) {
                            val baseCrypto = baseCryptos.next()
                            val conversionCryptos = gson.fromJson(jsonObject2.getString(baseCrypto), arrayListOf<String>()::class.java)
                            cryptoAndConversion.add(Pair(baseCrypto, conversionCryptos))
                        }
                        add(DBExchange(exchange, Cryptos(cryptoAndConversion)))
                    }

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }.toList()
        }
    }
}