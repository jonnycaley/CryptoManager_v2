package com.example.cryptomanager_v2.utils

import androidx.test.platform.app.InstrumentationRegistry
import com.example.cryptomanager_v2.utils.di.components.DaggerTestAppComponent
import com.example.cryptomanager_v2.utils.di.components.TestAppComponent

class TestInjector {

    fun initComponent(): TestAppComponent {
        return DaggerTestAppComponent
            .create()
    }

    fun inject(component: TestAppComponent) {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as TestApp
        component.inject(app)
    }
}