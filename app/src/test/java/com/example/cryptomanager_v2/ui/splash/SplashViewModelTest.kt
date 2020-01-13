package com.example.cryptomanager_v2.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cryptomanager_v2.data.db.cryptos.DBCrypto
import com.example.cryptomanager_v2.data.db.cryptos.DBCryptoService
import com.example.cryptomanager_v2.data.db.exchanges.DBExchange
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangesService
import com.example.cryptomanager_v2.data.db.exchanges.cryptos.DBExchangeCrypto
import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsService
import com.example.cryptomanager_v2.data.network.cryptocompare.CryptoCompareService
import com.example.cryptomanager_v2.data.network.exchangerates.ExchangeRatesService
import com.example.cryptomanager_v2.utils.AppSchedulers
import com.example.cryptomanager_v2.utils.NoConnectivityException
import com.example.cryptomanager_v2.utils.Status
import com.example.cryptomanager_v2.utils.TestAppSchedulers
import com.example.cryptomanager_v2.utils.observeOnce
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

    private var exchangeRatesService: ExchangeRatesService = mock()
    private var dbExchangesService: DBExchangesService = mock()
    private var dbCryptoService: DBCryptoService = mock()
    private var dbFiatsService: DBFiatsService = mock()
    private var cryptoCompareService: CryptoCompareService = mock()

    private var testSchedulers: AppSchedulers = TestAppSchedulers.get()

    private lateinit var viewModel: SplashViewModel

    @Test
    fun givenDatabasesTablesContainData_whenInit_thenSuccess() {
        whenever(dbExchangesService.dbHasData()).thenReturn(Observable.just(true))

        whenever(dbFiatsService.hasFiatsData()).thenReturn(true)

        whenever(dbCryptoService.dbHasData()).thenReturn(Observable.just(true))

        viewModel = createViewModel()
        viewModel.status.observeOnce { assertThat(it).isEqualTo(Status.SUCCESS) }
    }

    @Test
    fun givenDatabaseTablesContainNoData_whenInit_thenSuccess() {
        whenever(dbExchangesService.dbHasData()).thenReturn(Observable.just(false))
        val exchanges = createExchangesDB()
        whenever(cryptoCompareService.getAllExchanges()).thenReturn(Observable.just(exchanges))
        whenever(dbExchangesService.insertAll(exchanges)).thenReturn(Completable.complete())

        whenever(dbFiatsService.hasFiatsData()).thenReturn(false)
        val fiats = createFiatsDB()
        whenever(exchangeRatesService.getFiats()).thenReturn(Observable.just(fiats))
        whenever(dbFiatsService.insertAll(fiats)).thenReturn(Completable.complete())

        whenever(dbCryptoService.dbHasData()).thenReturn(Observable.just(false))
        val cryptos = createCryptosDB()
        whenever(cryptoCompareService.getAllCrypto()).thenReturn(Observable.just(cryptos))
        whenever(dbCryptoService.insertAll(cryptos)).thenReturn(Completable.complete())

        viewModel = createViewModel()
        viewModel.status.observeOnce { assertThat(it).isEqualTo(Status.SUCCESS) }
    }

    @Test
    fun givenGetAllExchangesError_whenInit_thenError() {
        whenever(dbExchangesService.dbHasData()).thenReturn(Observable.just(false))
        val error = NoConnectivityException()
        whenever(cryptoCompareService.getAllExchanges()).thenReturn(Observable.error(error))

        whenever(dbFiatsService.hasFiatsData()).thenReturn(false)
        val fiats = createFiatsDB()
        whenever(exchangeRatesService.getFiats()).thenReturn(Observable.just(fiats))
        whenever(dbFiatsService.insertAll(fiats)).thenReturn(Completable.complete())

        whenever(dbCryptoService.dbHasData()).thenReturn(Observable.just(false))
        val cryptos = createCryptosDB()
        whenever(cryptoCompareService.getAllCrypto()).thenReturn(Observable.just(cryptos))
        whenever(dbCryptoService.insertAll(cryptos)).thenReturn(Completable.complete())

        viewModel = createViewModel()
        viewModel.status.observeOnce { assertThat(it).isEqualTo(Status.ERROR("No network available, please check your WiFi or Data connection")) }
    }

    @Test
    fun givenGetAllFiatsError_whenInit_thenError() {
        whenever(dbExchangesService.dbHasData()).thenReturn(Observable.just(false))
        val exchanges = createExchangesDB()
        whenever(cryptoCompareService.getAllExchanges()).thenReturn(Observable.just(exchanges))
        whenever(dbExchangesService.insertAll(exchanges)).thenReturn(Completable.complete())

        whenever(dbFiatsService.hasFiatsData()).thenReturn(false)
        val error = NoConnectivityException()
        whenever(exchangeRatesService.getFiats()).thenReturn(Observable.error(error))

        whenever(dbCryptoService.dbHasData()).thenReturn(Observable.just(false))
        val cryptos = createCryptosDB()
        whenever(cryptoCompareService.getAllCrypto()).thenReturn(Observable.just(cryptos))
        whenever(dbCryptoService.insertAll(cryptos)).thenReturn(Completable.complete())

        viewModel = createViewModel()
        viewModel.status.observeOnce { assertThat(it).isEqualTo(Status.ERROR("No network available, please check your WiFi or Data connection")) }
    }

    @Test
    fun givenGetAllCryptosError_whenInit_thenError() {
        whenever(dbExchangesService.dbHasData()).thenReturn(Observable.just(false))
        val exchanges = createExchangesDB()
        whenever(cryptoCompareService.getAllExchanges()).thenReturn(Observable.just(exchanges))
        whenever(dbExchangesService.insertAll(exchanges)).thenReturn(Completable.complete())

        whenever(dbFiatsService.hasFiatsData()).thenReturn(false)
        val fiats = createFiatsDB()
        whenever(exchangeRatesService.getFiats()).thenReturn(Observable.just(fiats))
        whenever(dbFiatsService.insertAll(fiats)).thenReturn(Completable.complete())

        whenever(dbCryptoService.dbHasData()).thenReturn(Observable.just(false))
        val error = NoConnectivityException()
        whenever(cryptoCompareService.getAllCrypto()).thenReturn(Observable.error(error))

        viewModel = createViewModel()
        viewModel.status.observeOnce { assertThat(it).isEqualTo(Status.ERROR("No network available, please check your WiFi or Data connection")) }
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

    private fun createExchangesDB() = listOf(DBExchange(exchangeName = "ABCC", cryptos = DBExchangeCrypto(
        cryptos = listOf(
            Pair(first = "CND", second = listOf("BTC", "ETH"))
        )
    )
    ))

    private fun createFiatsDB() = listOf(DBFiat(name = "GBP", rate = 1.3258222788, isBaseFiat = true))

    private fun createViewModel() : SplashViewModel {
        return SplashViewModel(
            exchangeRatesService = exchangeRatesService,
            schedulers = testSchedulers,
            dbExchangesService = dbExchangesService,
            dbFiatsService = dbFiatsService,
            dbCryptoService = dbCryptoService,
            cryptoCompareService = cryptoCompareService
        )
    }
}