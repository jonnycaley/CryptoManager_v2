package com.example.cryptomanager_v2.data.network.cryptocompare

object CryptoCompareApiBuilder {

    fun buildCryptoResponse(): String? {
        return "{\"Response\":\"Success\",\"Message\":\"Coin list succesfully returned!\",\"Data\":{\"42\":{\"Id\":\"4321\",\"Url\":\"/coins/42/overview\",\"ImageUrl\":\"/media/35650717/42.jpg\",\"ContentCreatedOn\":1427211129,\"Name\":\"42\",\"Symbol\":\"42\",\"CoinName\":\"42 Coin\",\"FullName\":\"42 Coin (42)\",\"Algorithm\":\"Scrypt\",\"ProofType\":\"PoW/PoS\",\"FullyPremined\":\"0\",\"TotalCoinSupply\":\"42\",\"BuiltOn\":\"N/A\",\"SmartContractAddress\":\"N/A\",\"PreMinedValue\":\"N/A\",\"TotalCoinsFreeFloat\":\"N/A\",\"SortOrder\":\"34\",\"Sponsored\":false,\"Taxonomy\":{\"Access\":\"\",\"FCA\":\"\",\"FINMA\":\"\",\"Industry\":\"\",\"CollateralizedAsset\":\"\",\"CollateralizedAssetType\":\"\",\"CollateralType\":\"\",\"CollateralInfo\":\"\"},\"IsTrading\":true,\"TotalCoinsMined\":41.99995313,\"BlockNumber\":150901,\"NetHashesPerSecond\":0,\"BlockReward\":0,\"BlockTime\":0}},\"BaseImageUrl\":\"https://www.cryptocompare.com\",\"BaseLinkUrl\":\"https://www.cryptocompare.com\",\"RateLimit\":{},\"HasWarning\":false,\"Type\":100}"
    }

    fun buildExchangeResponse(): String {
        return "{\"Binance\":{\"BTC\":[\"ETH\",\"LTC\"]}}"
    }
}