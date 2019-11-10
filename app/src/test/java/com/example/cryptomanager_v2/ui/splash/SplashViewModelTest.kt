package com.example.cryptomanager_v2.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cryptomanager_v2.data.db.cryptos.DBCryptosDao
import com.example.cryptomanager_v2.data.db.exchanges.DBExchange
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangeDao
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import com.example.cryptomanager_v2.data.network.CryptoCompareApi
import com.example.cryptomanager_v2.data.network.ExchangeRatesApi
import com.example.cryptomanager_v2.utils.TestSchedulers
import com.example.cryptomanager_v2.utils.di.AppSchedulers
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
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

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun givenAllSubjectsNotLoading_whenInit_thenIsNotLoading() {
        whenever(exchangeDao.getAll()).thenReturn(Observable.just(listOf(
            DBExchange(
                exchangeName = "test",
                cryptos = null
            )
        )))

        viewModel = createViewModel()
        viewModel.loading.observeForever { loading = it }

        assertEquals(expected = false, actual = true)
    }

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