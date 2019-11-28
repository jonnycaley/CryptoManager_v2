package com.example.cryptomanager_v2.utils

import androidx.test.platform.app.InstrumentationRegistry
import com.example.cryptomanager_v2.data.db.exchanges.DBExchange
import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import com.example.cryptomanager_v2.utils.di.components.DaggerAppComponent
import com.example.cryptomanager_v2.utils.mocks.db.FakeDBExchangesDao
import com.example.cryptomanager_v2.utils.mocks.db.FakeDBFiatsDao
import io.reactivex.Observable

class TestInjector {

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val app = instrumentation.targetContext.applicationContext as TestApp

    fun initComponent(): TestAppComponent {
        return DaggerTestAppComponent
            .create()
    }

    fun inject(component: TestAppComponent) {
        component.inject(app)
    }
}