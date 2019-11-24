package com.example.cryptomanager_v2.ui.splash

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cryptomanager_v2.utils.TestAppModule
import com.example.cryptomanager_v2.utils.TestInjector
import com.example.cryptomanager_v2.utils.mocks.api.FakeExchangeRatesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

    lateinit var senario: ActivityScenario<SplashActivity>

    private var exchangeRatesApi: FakeExchangeRatesApi = FakeExchangeRatesApi()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        TestInjector(TestAppModule(exchangeRatesApi)).inject()
    }

    @Test
    fun test() {
        senario = launchActivity()

        SplashActivityRobot()
            .checkLoadingTextIsIdle()
    }
}