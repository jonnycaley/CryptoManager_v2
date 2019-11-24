package com.example.cryptomanager_v2.ui.splash

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cryptomanager_v2.utils.TestAppModule
import com.example.cryptomanager_v2.utils.TestInjector
import com.example.cryptomanager_v2.utils.mocks.api.FakeExchangeRatesApi
import com.example.cryptomanager_v2.utils.mocks.db.FakeDBFiatsDao
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

    lateinit var scenario: ActivityScenario<SplashActivity>

    private var dbFiatsDao: FakeDBFiatsDao = FakeDBFiatsDao()
    private var exchangeRatesApi: FakeExchangeRatesApi = FakeExchangeRatesApi()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun test() {

        launchActivity()

        SplashActivityRobot()
            .checkLoadingTextIsIdle()
    }

    private fun launchActivity() {
        TestInjector(TestAppModule()).inject()
        scenario = androidx.test.core.app.launchActivity()
    }
}