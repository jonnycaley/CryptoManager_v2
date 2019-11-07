package com.example.cryptomanager_v2.data.model.cryptocompare.crytpo

import com.example.cryptomanager_v2.data.db.cryptos.DBCrypto
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.json.JSONException
import org.json.JSONObject

class Crypto {

    @SerializedName("Id")
    var id: String? = null

    @SerializedName("Url")
    var url: String? = null

    @SerializedName("ImageUrl")
    var imageUrl: String? = null

    @SerializedName("ContentCreatedOn")
    var contentCreatedOn: Int? = null

    @SerializedName("Name")
    var name: String? = null

    @SerializedName("Symbol")
    var symbol: String? = null

    @SerializedName("CoinName")
    var coinName: String? = null

    @SerializedName("FullName")
    var fullName: String? = null

    @SerializedName("Algorithm")
    var algorithm: String? = null

    @SerializedName("ProofType")
    var proofType: String? = null

    @SerializedName("FullyPremined")
    var fullyPremined: String? = null

    @SerializedName("TotalCoinSupply")
    var totalCoinSupply: String? = null

    @SerializedName("BuiltOn")
    var builtOn: String? = null

    @SerializedName("SmartContractAddress")
    var smartContractAddress: String? = null

    @SerializedName("PreMinedValue")
    var preMinedValue: String? = null

    @SerializedName("TotalCoinsFreeFloat")
    var totalCoinsFreeFloat: String? = null

    @SerializedName("SortOrder")
    var sortOrder: String? = null

    @SerializedName("Sponsored")
    var sponsored: Boolean? = null

    companion object {
        fun cryptoToDBCryptos(
            gson: Gson,
            json: String
        ) : List<DBCrypto>{
            val cryptoArray = arrayListOf<DBCrypto>()
            try {
                val jObject = JSONObject(json).getJSONObject("Data")
                val keys = jObject.keys()
                while (keys.hasNext())
                {
                    val key = keys.next()
                    val value = jObject.getString(key)

                    val crypto = gson.fromJson(value, Crypto::class.java)

                    cryptoArray.add(
                        DBCrypto(
                            id = crypto.id!!,
                            name = key,
                            imageUrl = crypto.imageUrl,
                            contentCreatedOn = crypto.contentCreatedOn,
                            symbol = crypto.symbol,
                            coinName = crypto.coinName,
                            fullName = crypto.fullName,
                            totalCoinSupply = crypto.totalCoinSupply
                        )
                    )
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return cryptoArray
        }
    }
}