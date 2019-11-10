package com.example.cryptomanager_v2.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cryptomanager_v2.data.db.cryptos.DBCrypto
import com.example.cryptomanager_v2.data.db.cryptos.DBCryptosDao
import com.example.cryptomanager_v2.data.db.exchanges.DBExchange
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangeDao
import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import com.example.cryptomanager_v2.data.model.ExchangeRates.ExchangeRatesOld
import com.example.cryptomanager_v2.data.model.ExchangeRates.RatesOld
import com.example.cryptomanager_v2.data.model.cryptocompare.exchanges.Exchange
import com.example.cryptomanager_v2.data.network.CryptoCompareApi
import com.example.cryptomanager_v2.data.network.ExchangeRatesApi
import com.example.cryptomanager_v2.utils.TestSchedulers
import com.example.cryptomanager_v2.utils.di.AppSchedulers
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class SplashViewModelTest {

    @JvmField
    @Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private var exchangeRatesApi: ExchangeRatesApi = mock()
    private var exchangeDao: DBExchangeDao = mock()
    private var cryptosDao: DBCryptosDao = mock()
    private var fiatsDao: DBFiatsDao = mock()
    private var gson: Gson = mock()
    private var cryptoCompareApi: CryptoCompareApi = mock()

    private var testSchedulers: AppSchedulers = TestSchedulers.get()

    private lateinit var viewModel: SplashViewModel

    private var loading: Boolean = false

    @Test
    fun givenDatabasesTablesContainData_whenInit_thenIsNotLoading() {
        val exchangesDB = createExchangesDB()
        whenever(exchangeDao.getAll()).thenReturn(Observable.just(exchangesDB))

        val fiatsDB = createFiatsDB()
        whenever(fiatsDao.getAll()).thenReturn(Observable.just(fiatsDB))

        val cryptosDB = createCryptosDB()
        whenever(cryptosDao.getAll()).thenReturn(Observable.just(cryptosDB))

        viewModel = createViewModel()
        viewModel.loading.observeForever { loading = it }

        assertThat(loading).isEqualTo(false)
    }

    @Test
    fun givenDatabaseTablesContainNoData_whenInit_thenDataLoasded() {
        val emptyExchangesDB = createEmptyExchangesDB()
        whenever(exchangeDao.getAll()).thenReturn(Observable.just(emptyExchangesDB))
        val exchanges = createExchangesString()
        whenever(cryptoCompareApi.getAllExchanges()).thenReturn(Observable.just(exchanges))
        val dbExchanges = Exchange.exchangesToDBExchanges(Gson(), exchanges)
        whenever(exchangeDao.insertAll(dbExchanges)).thenReturn(Completable.complete())

        val emptyFiatDB = createEmptyFiatsDB()
        whenever(fiatsDao.getAll()).thenReturn(Observable.just(emptyFiatDB))
        val fiats = createFiats()
        whenever(exchangeRatesApi.getFiats()).thenReturn(Observable.just(fiats))
        whenever(fiatsDao.insertAll(createFiatsDB())).thenReturn(Completable.complete())

        val emptyCryptosDB = createEmptyCryptosDB()
        whenever(cryptosDao.getAll()).thenReturn(Observable.just(emptyCryptosDB))
        val cryptos = createCryptosString()
        whenever(cryptoCompareApi.getAllCrypto()).thenReturn(Observable.just(cryptos))
        whenever(cryptosDao.insertAll(createCryptosDB())).thenReturn(Completable.complete())

        viewModel = createViewModel()
        viewModel.loading.observeForever { loading = it }

        assertThat(loading).isEqualTo(false)
    }

    private fun createCryptosString(): String {
        return "{" +
            "  \"Response\":\"Success\"," +
            "  \"Message\":\"Coin list succesfully returned!\"," +
            "  \"Data\":{" +
            "    \"42\":{" +
            "      \"Id\":\"4321\"," +
            "      \"Url\":\"coins42overview\"," +
            "      \"ImageUrl\":\"media3565071742.jpg\"," +
            "      \"ContentCreatedOn\":1427211129," +
            "      \"Name\":\"42\"," +
            "      \"Symbol\":\"42\"," +
            "      \"CoinName\":\"42 Coin\"," +
            "      \"FullName\":\"42 Coin (42)\"," +
            "      \"Algorithm\":\"Scrypt\"," +
            "      \"ProofType\":\"PoWPoS\"," +
            "      \"FullyPremined\":\"0\"," +
            "      \"TotalCoinSupply\":\"42\"," +
            "      \"BuiltOn\":\"NA\"," +
            "      \"SmartContractAddress\":\"NA\"," +
            "      \"PreMinedValue\":\"NA\"," +
            "      \"TotalCoinsFreeFloat\":\"NA\"," +
            "      \"SortOrder\":\"34\"," +
            "      \"Sponsored\":false," +
            "      \"Taxonomy\":{" +
            "        \"Access\":\"\"," +
            "        \"FCA\":\"\"," +
            "        \"FINMA\":\"\"," +
            "        \"Industry\":\"\"," +
            "        \"CollateralizedAsset\":\"\"," +
            "        \"CollateralizedAssetType\":\"\"," +
            "        \"CollateralType\":\"\"," +
            "        \"CollateralInfo\":\"\"" +
            "      }," +
            "      \"IsTrading\":true," +
            "      \"TotalCoinsMined\":41.99995317," +
            "      \"BlockNumber\":147668," +
            "      \"NetHashesPerSecond\":0," +
            "      \"BlockReward\":0," +
            "      \"BlockTime\":0" +
            "    }" +
            "  }," +
            "  \"BaseImageUrl\":\"https://www.cryptocompare.com\"," +
            "  \"BaseLinkUrl\":\"https://www.cryptocompare.com\"," +
            "  \"RateLimit\":{" +
            "    " +
            "  }," +
            "  \"HasWarning\":false," +
            "  \"Type\":100" +
            "}"
    }

    private fun createExchangesString(): String {
        return "{\"55com\":{}}"
    }

    private fun createFiats(): ExchangeRatesOld {
        return ExchangeRatesOld().apply {
            base = "USD"
            rates = RatesOld().apply {
                bgn = 2.00
            }
        }
    }

    private fun createCryptosDB() = listOf(DBCrypto(
        id = "4321",
        name = "BTC",
        imageUrl = "/media/35650717/42.jpg",
        contentCreatedOn = 1427211129,
        symbol = "42",
        coinName = "42 coin",
        fullName = "42 Coin (42)",
        totalCoinSupply = "42"
    ))

    private fun createEmptyCryptosDB() = emptyList<DBCrypto>()

    private fun createEmptyFiatsDB() = emptyList<DBFiat>()

    private fun createEmptyExchangesDB() = emptyList<DBExchange>()

    private fun createExchangesDB() = listOf(DBExchange(exchangeName = "55com", cryptos = null))

    private fun createFiatsDB() = listOf(DBFiat(name = "USD", rate = 1.00))


    private fun createViewModel() : SplashViewModel {
        return SplashViewModel(
            exchangeRatesApi = exchangeRatesApi,
            schedulers = testSchedulers,
            exchangesDao = exchangeDao,
            fiatsDao = fiatsDao,
            cryptosDao = cryptosDao,
            gson = gson,
            cryptoCompareApi = cryptoCompareApi
        )
    }
}