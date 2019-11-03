package com.example.cryptomanager_v2.data.model.ExchangeRates

import com.example.cryptomanager_v2.data.db.DBFiat
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.json.JSONException
import org.json.JSONObject

class ExchangeRatesOld {

    @SerializedName("base")
    var base: String? = null

    @SerializedName("rates")
    var rates: RatesOld? = null

    companion object {
        fun ratesToDBFiats(
            gson: Gson,
            exchangeRatesOld: ExchangeRatesOld
        ): List<DBFiat> {
            val fiatsArray = arrayListOf<DBFiat>()
            try {
                val json = gson.toJson(exchangeRatesOld)

                val jObject = JSONObject(json).getJSONObject("rates")
                val keys = jObject.keys()
                while (keys.hasNext())
                {
                    val key = keys.next()
                    val value = jObject.getString(key)

                    fiatsArray.add(DBFiat(name = key, rate = value.toDouble()))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return fiatsArray
        }
    }
}
