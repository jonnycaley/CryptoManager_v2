package com.example.cryptomanager_v2.utils

import androidx.test.platform.app.InstrumentationRegistry
import com.example.cryptomanager_v2.utils.di.components.DaggerTestAppComponent
import com.example.cryptomanager_v2.utils.di.components.TestAppComponent

class TestInjector {

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val app = instrumentation.targetContext.applicationContext as TestApp

    fun initComponent(): TestAppComponent {
        return DaggerTestAppComponent
            .builder()
            .build()
    }

    fun inject(component: TestAppComponent) {
        component.inject(app)
    }
}