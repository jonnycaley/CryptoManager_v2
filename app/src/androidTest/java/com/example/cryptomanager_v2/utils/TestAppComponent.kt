package com.example.cryptomanager_v2.utils

import com.example.cryptomanager_v2.utils.di.ActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        TestAppModule::class,
        ActivityBuilder::class,
        AndroidSupportInjectionModule::class
    ]
)
interface TestAppComponent : AndroidInjector<TestApp> {

    @Component.Builder
    interface Builder {

        fun build() : TestAppComponent

        @BindsInstance
        fun appModule(appModule: TestAppModule): Builder
    }
}