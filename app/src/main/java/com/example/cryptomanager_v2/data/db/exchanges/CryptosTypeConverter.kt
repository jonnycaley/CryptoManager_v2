package com.example.cryptomanager_v2.data.db.exchanges

import androidx.room.TypeConverter
import com.google.gson.Gson
import javax.inject.Inject

class CryptosTypeConverter {

    var gson: Gson = Gson()

    @TypeConverter
    fun storedStringToCryptos(string: String?) : Cryptos? {
        return if(string.isNullOrEmpty()) {
            Cryptos(arrayListOf())
        } else {
            gson.fromJson(string, Cryptos::class.java)
        }
    }

    @TypeConverter
    fun cryptosToString(cryptos: Cryptos?) : String? {
        return gson.toJson(cryptos)
    }
}