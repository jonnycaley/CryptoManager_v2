package com.example.cryptomanager_v2.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cryptomanager_v2.data.db.cryptos.DBCrypto
import com.example.cryptomanager_v2.data.db.cryptos.DBCryptosDao
import com.example.cryptomanager_v2.data.db.exchanges.Cryptos
import com.example.cryptomanager_v2.data.db.exchanges.DBExchange
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangeDao
import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import com.example.cryptomanager_v2.data.model.cryptocompare.crytpo.Crypto
import com.example.cryptomanager_v2.data.model.cryptocompare.exchanges.Exchange
import com.example.cryptomanager_v2.data.network.CryptoCompareApi
import com.example.cryptomanager_v2.data.network.ExchangeRatesApi
import com.example.cryptomanager_v2.utils.NoConnectivityException
import com.example.cryptomanager_v2.utils.Status
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
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest {

    @JvmField
    @Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private var exchangeRatesApi: ExchangeRatesApi = mock()
    private var exchangeDao: DBExchangeDao = mock()
    private var cryptosDao: DBCryptosDao = mock()
    private var fiatsDao: DBFiatsDao = mock()
    private var cryptoCompareApi: CryptoCompareApi = mock()

    private var testSchedulers: AppSchedulers = TestSchedulers.get()

    private lateinit var viewModel: SplashViewModel

    private var status: Status = Status.IDLE

    @Test
    fun givenDatabasesTablesContainData_whenInit_thenSuccess() {
        val exchangesDB = createExchangesDB()
        whenever(exchangeDao.getAll()).thenReturn(Observable.just(exchangesDB))

        val fiatsDB = createFiatsDB()
        whenever(fiatsDao.getAll()).thenReturn(Observable.just(fiatsDB))

        val cryptosDB = createCryptosDB()
        whenever(cryptosDao.getAll()).thenReturn(Observable.just(cryptosDB))

        viewModel = createViewModel()
        viewModel.status.observeForever { status = it }

        assertThat(status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun givenDatabaseTablesContainNoData_whenInit_thenSuccess() {
        val emptyExchangesDB = createEmptyExchangesDB()
        whenever(exchangeDao.getAll()).thenReturn(Observable.just(emptyExchangesDB))
        val exchanges = createExchangesString()
        whenever(cryptoCompareApi.getAllExchanges()).thenReturn(Observable.just(exchanges))
        whenever(exchangeDao.insertAll(createExchangesDB())).thenReturn(Completable.complete())

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
        viewModel.status.observeForever { status = it }

        assertThat(status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun givenGetAllExchangesError_whenInit_thenError() {
        val emptyExchangesDB = createEmptyExchangesDB()
        whenever(exchangeDao.getAll()).thenReturn(Observable.just(emptyExchangesDB))
        val error = NoConnectivityException()
        whenever(cryptoCompareApi.getAllExchanges()).thenReturn(Observable.error(error))

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
        viewModel.status.observeForever { status = it }

        assertThat(status).isEqualTo(Status.ERROR("No network available, please check your WiFi or Data connection"))
    }

    @Test
    fun givenGetAllFiatsError_whenInit_thenError() {
        val emptyExchangesDB = createEmptyExchangesDB()
        whenever(exchangeDao.getAll()).thenReturn(Observable.just(emptyExchangesDB))
        val exchanges = createExchangesString()
        whenever(cryptoCompareApi.getAllExchanges()).thenReturn(Observable.just(exchanges))
        val dbExchanges = Exchange.exchangesToDBExchanges(exchanges)
        whenever(exchangeDao.insertAll(dbExchanges)).thenReturn(Completable.complete())

        val emptyFiatDB = createEmptyFiatsDB()
        whenever(fiatsDao.getAll()).thenReturn(Observable.just(emptyFiatDB))
        val error = NoConnectivityException()
        whenever(exchangeRatesApi.getFiats()).thenReturn(Observable.error(error))

        val emptyCryptosDB = createEmptyCryptosDB()
        whenever(cryptosDao.getAll()).thenReturn(Observable.just(emptyCryptosDB))
        val cryptos = createCryptosString()
        whenever(cryptoCompareApi.getAllCrypto()).thenReturn(Observable.just(cryptos))
        whenever(cryptosDao.insertAll(createCryptosDB())).thenReturn(Completable.complete())

        viewModel = createViewModel()
        viewModel.status.observeForever { status = it }

        assertThat(status).isEqualTo(Status.ERROR("No network available, please check your WiFi or Data connection"))
    }

    @Test
    fun givenGetAllCryptosError_whenInit_thenError() {
        val emptyExchangesDB = createEmptyExchangesDB()
        whenever(exchangeDao.getAll()).thenReturn(Observable.just(emptyExchangesDB))
        val exchanges = createExchangesString()
        whenever(cryptoCompareApi.getAllExchanges()).thenReturn(Observable.just(exchanges))
        val dbExchanges = Exchange.exchangesToDBExchanges(exchanges)
        whenever(exchangeDao.insertAll(dbExchanges)).thenReturn(Completable.complete())

        val emptyFiatDB = createEmptyFiatsDB()
        whenever(fiatsDao.getAll()).thenReturn(Observable.just(emptyFiatDB))
        val fiats = createFiats()
        whenever(exchangeRatesApi.getFiats()).thenReturn(Observable.just(fiats))
        whenever(fiatsDao.insertAll(createFiatsDB())).thenReturn(Completable.complete())

        val emptyCryptosDB = createEmptyCryptosDB()
        whenever(cryptosDao.getAll()).thenReturn(Observable.just(emptyCryptosDB))
        val error = NoConnectivityException()
        whenever(cryptoCompareApi.getAllCrypto()).thenReturn(Observable.error(error))

        viewModel = createViewModel()
        viewModel.status.observeForever { status = it }

        assertThat(status).isEqualTo(Status.ERROR("No network available, please check your WiFi or Data connection"))
    }

    private fun createCryptosString(): String {
        return readJsonFile("json/crypto_compare_get_all_crypto.json")
    }

    private fun createExchangesString(): String {
        return readJsonFile("json/crypto_compare_get_all_exchanges.json")
    }

    private fun createFiats(): String {
        return readJsonFile("json/exchange_rates_get_all_rates.json")
    }

    private fun readJsonFile(filePath: String): String {
        return javaClass.classLoader?.getResource(filePath)?.readText().toString()
    }

    private fun createCryptosDB() = listOf(
        DBCrypto(
            id="4321",
            name="42",
            imageUrl="/media/35650717/42.jpg",
            contentCreatedOn=1427211129,
            symbol="42",
            coinName="42 Coin",
            fullName="42 Coin (42)",
            totalCoinSupply="42"
        ))

    private fun createEmptyCryptosDB() = listOf<DBCrypto>()

    private fun createEmptyFiatsDB() = listOf<DBFiat>()

    private fun createEmptyExchangesDB() = listOf<DBExchange>()

    private fun createExchangesDB() = listOf(DBExchange(exchangeName = "ABCC", cryptos = Cryptos(
        cryptos = listOf(
            Pair(first ="CND", second = listOf("BTC", "ETH"))
        )
    )))

    private fun createFiatsDB() = listOf(DBFiat(name = "CAD", rate = 1.3258222788))

    private fun createViewModel() : SplashViewModel {
        return SplashViewModel(
            exchangeRatesApi = exchangeRatesApi,
            schedulers = testSchedulers,
            exchangesDao = exchangeDao,
            fiatsDao = fiatsDao,
            cryptosDao = cryptosDao,
            cryptoCompareApi = cryptoCompareApi
        )
    }
}