package com.example.cryptomanager_v2.data.db.exchanges.cryptos

import androidx.room.TypeConverter
import com.google.gson.Gson

class CryptosTypeConverter {

    var gson: Gson = Gson()

    @TypeConverter
    fun storedStringToCryptos(string: String?) : DBExchangeCrypto? {
        return if(string.isNullOrEmpty()) {
            DBExchangeCrypto(arrayListOf())
        } else {
            gson.fromJson(string, DBExchangeCrypto::class.java)
        }
    }

    @TypeConverter
    fun cryptosToString(cryptos: DBExchangeCrypto?) : String? {
        return gson.toJson(cryptos)
    }
}