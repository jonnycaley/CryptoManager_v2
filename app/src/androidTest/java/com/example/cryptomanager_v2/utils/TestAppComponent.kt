package com.example.cryptomanager_v2.utils

import com.example.cryptomanager_v2.utils.di.ActivityBuilder
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        TestAppModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class
    ]
)
interface TestAppComponent : AndroidInjector<TestApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<TestApp>() {
        abstract fun appModule(appModule: TestAppModule): Builder
    }
}