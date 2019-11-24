package com.example.cryptomanager_v2.utils

import androidx.test.platform.app.InstrumentationRegistry

class TestInjector(private val testApplicationModule: TestAppModule) {

    fun inject() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as TestApp

        DaggerTestAppComponent
            .builder()
            .appModule(testApplicationModule)
            .build()
            .inject(app)
    }
}