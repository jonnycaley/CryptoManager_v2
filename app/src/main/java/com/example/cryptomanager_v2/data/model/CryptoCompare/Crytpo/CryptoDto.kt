package com.example.cryptomanager_v2.data.model.cryptocompare.crytpo

import com.example.cryptomanager_v2.data.db.cryptos.DBCrypto
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.json.JSONException
import org.json.JSONObject

class CryptoDto {

    @SerializedName("Id")
    @Expose
    var id: String? = null

    @SerializedName("Url")
    @Expose
    var url: String? = null

    @SerializedName("ImageUrl")
    @Expose
    var imageUrl: String? = null

    @SerializedName("ContentCreatedOn")
    @Expose
    var contentCreatedOn: Int? = null

    @SerializedName("Name")
    @Expose
    var name: String? = null

    @SerializedName("Symbol")
    @Expose
    var symbol: String? = null

    @SerializedName("CoinName")
    @Expose
    var coinName: String? = null

    @SerializedName("FullName")
    @Expose
    var fullName: String? = null

    @SerializedName("Algorithm")
    @Expose
    var algorithm: String? = null

    @SerializedName("ProofType")
    @Expose
    var proofType: String? = null

    @SerializedName("FullyPremined")
    @Expose
    var fullyPremined: String? = null

    @SerializedName("TotalCoinSupply")
    @Expose
    var totalCoinSupply: String? = null

    @SerializedName("BuiltOn")
    @Expose
    var builtOn: String? = null

    @SerializedName("SmartContractAddress")
    @Expose
    var smartContractAddress: String? = null

    @SerializedName("PreMinedValue")
    @Expose
    var preMinedValue: String? = null

    @SerializedName("TotalCoinsFreeFloat")
    @Expose
    var totalCoinsFreeFloat: String? = null

    @SerializedName("SortOrder")
    @Expose
    var sortOrder: String? = null

    @SerializedName("Sponsored")
    @Expose
    var sponsored: Boolean? = null

    companion object {
        fun cryptosToDBCryptos(
            json: String
        ) : List<DBCrypto>{
            return mutableListOf<DBCrypto>().apply {
                try {
                    val jObject = JSONObject(json).optJSONObject("Data")
                    val keys = jObject?.keys()
                    while (keys != null && keys.hasNext()) {
                        val name = keys.next()
                        val value = jObject.optJSONObject(name)
                        value?.let {
                            val dBCrypto = cryptoToDBCrypto(it)
                            add(dBCrypto)
                        }
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }.toList()
        }

        private fun cryptoToDBCrypto(cryptoInfo: JSONObject) : DBCrypto {
            return DBCrypto(
                id = cryptoInfo.opt("Id") as String,
                name = cryptoInfo.opt("Name") as String,
                imageUrl = cryptoInfo.opt("ImageUrl") as String?,
                contentCreatedOn = cryptoInfo.opt("ContentCreatedOn") as Int?,
                symbol = cryptoInfo.opt("Symbol") as String?,
                coinName = cryptoInfo.opt("CoinName") as String?,
                fullName = cryptoInfo.opt("FullName") as String?,
                totalCoinSupply = cryptoInfo.opt("TotalCoinSupply") as String?
            )
        }
    }
}