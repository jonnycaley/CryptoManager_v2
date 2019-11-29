package com.example.cryptomanager_v2.ui.splash

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cryptomanager_v2.data.network.cryptocompare.CryptoCompareApiBuilder
import com.example.cryptomanager_v2.data.network.exchangerates.ExchangeRatesApiBuilder
import com.example.cryptomanager_v2.utils.di.components.TestAppComponent
import com.example.cryptomanager_v2.utils.TestInjector
import com.example.cryptomanager_v2.utils.mocks.api.FakeCryptoCompareApi
import com.example.cryptomanager_v2.utils.mocks.api.FakeExchangeRatesApi
import com.example.cryptomanager_v2.utils.mocks.db.FakeDBCryptosDao
import com.example.cryptomanager_v2.utils.mocks.db.FakeDBExchangesDao
import com.example.cryptomanager_v2.utils.mocks.db.FakeDBFiatsDao
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

    private lateinit var scenario: ActivityScenario<SplashActivity>

    private lateinit var testComponent: TestAppComponent

    private lateinit var fakeDBExchangesDao: FakeDBExchangesDao
    private lateinit var fakeDBFiatsDao: FakeDBFiatsDao
    private lateinit var fakeDBCryptosDao: FakeDBCryptosDao

    private lateinit var fakeExchangeRatesApi: FakeExchangeRatesApi
    private lateinit var fakeCryptoCompareApi: FakeCryptoCompareApi

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testComponent = TestInjector().initComponent()
        testComponent.apply {
            fakeDBExchangesDao = dbExhangeDao() as FakeDBExchangesDao
            fakeDBFiatsDao = dbFiatsDao() as FakeDBFiatsDao
            fakeDBCryptosDao = dbCryptosDao() as FakeDBCryptosDao

            fakeExchangeRatesApi = exchangeRatesApi() as FakeExchangeRatesApi
            fakeCryptoCompareApi = cryptoCompareApi() as FakeCryptoCompareApi
        }
    }

    @Test
    fun whenStart_givenDataIsEmpty_thenLoadDataAndSuccessShown() {
        fakeDBExchangesDao.getAllResponses.add(Observable.just(listOf()))
        fakeDBFiatsDao.getAllResponses.add(Observable.just(listOf()))
        fakeDBCryptosDao.getAllResponses.add(Observable.just(listOf()))

        fakeExchangeRatesApi.getFiatsResponses.add(Observable.just(ExchangeRatesApiBuilder.build()))
        fakeCryptoCompareApi.getAllCryptoResponses.add(Observable.just(CryptoCompareApiBuilder.buildCryptoResponse()))
        fakeCryptoCompareApi.getAllExchangesResponses.add(Observable.just(CryptoCompareApiBuilder.buildExchangeResponse()))

        fakeDBExchangesDao.insertAllResponses.add(Completable.complete())
        fakeDBCryptosDao.insertAllResponses.add(Completable.complete())
        fakeDBFiatsDao.insertAllResponses.add(Completable.complete())

        launchActivity()

        SplashActivityRobot()
            .checkLoadingTextIsSuccess()
            .checkHasNavigatedToHomeActivity()
    }

    @Test
    fun whenStart_givenError_thenRetryShownAndWorks() {

    }

    private fun launchActivity() {
        TestInjector().inject(testComponent)
        scenario = androidx.test.core.app.launchActivity()
    }
}