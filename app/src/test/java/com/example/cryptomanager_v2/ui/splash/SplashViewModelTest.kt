package com.example.cryptomanager_v2.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cryptomanager_v2.data.db.cryptos.DBCrypto
import com.example.cryptomanager_v2.data.db.cryptos.DBCryptosDao
import com.example.cryptomanager_v2.data.db.exchanges.DBExchange
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangeDao
import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import com.example.cryptomanager_v2.data.network.CryptoCompareApi
import com.example.cryptomanager_v2.data.network.ExchangeRatesApi
import com.example.cryptomanager_v2.utils.TestSchedulers
import com.example.cryptomanager_v2.utils.di.AppSchedulers
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test

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
        val exchanges = createExchangesDB()
        whenever(exchangeDao.getAll()).thenReturn(Observable.just(exchanges))

        val fiats = createFiatsDB()
        whenever(fiatsDao.getAll()).thenReturn(Observable.just(fiats))

        val cryptos = createCryptosDB()
        whenever(cryptosDao.getAll()).thenReturn(Observable.just(cryptos))

        viewModel = createViewModel()
        viewModel.loading.observeForever { loading = it }

        assertThat(loading).isEqualTo(false)
    }

    fun createExchangesDB() = listOf(DBExchange(exchangeName = "test", cryptos = null))

    fun createFiatsDB() = listOf(DBFiat(name = "USD", rate = 1.00))

    fun createCryptosDB() = listOf(DBCrypto(
        id = "1",
        name = "BTC",
        imageUrl = null,
        contentCreatedOn = null,
        symbol = null,
        coinName = null,
        fullName = null,
        totalCoinSupply = null
    ))

    fun createViewModel() : SplashViewModel {
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