package com.example.cryptomanager_v2.ui.splash

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cryptomanager_v2.data.db.exchanges.DBExchange
import com.example.cryptomanager_v2.utils.TestAppComponent
import com.example.cryptomanager_v2.utils.TestInjector
import com.example.cryptomanager_v2.utils.mocks.db.FakeDBExchangesDao
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

    lateinit var scenario: ActivityScenario<SplashActivity>

    private lateinit var testComponent: TestAppComponent
    private lateinit var fakeDBExchangesDao: FakeDBExchangesDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testComponent = TestInjector().initComponent()
        testComponent.apply {
            fakeDBExchangesDao = dbExhangeDao() as FakeDBExchangesDao
        }
    }

    @Test
    fun test() {

        fakeDBExchangesDao.getAllResponses.add((Observable.just(listOf(
            DBExchange(
                exchangeName = "Test woooo",
                cryptos = null
            )
        ))))

        launchActivity()

        SplashActivityRobot()
            .checkLoadingTextIsIdle()
    }

    private fun launchActivity() {
        TestInjector().inject(testComponent)
        scenario = androidx.test.core.app.launchActivity()
    }
}