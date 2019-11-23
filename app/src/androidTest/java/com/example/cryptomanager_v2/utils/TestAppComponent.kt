package com.example.cryptomanager_v2.utils

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [TestAppModule::class])
interface TestAppComponent: AndroidInjector<TestApp> {

    @Component.Builder
    interface Builder {

        fun build() : TestAppComponent

        @BindsInstance
        fun app(app: TestApp): Builder
    }
}