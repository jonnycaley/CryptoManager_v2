package com.example.cryptomanager_v2.utils

import com.example.cryptomanager_v2.data.db.exchanges.DBExchangeDao
import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import com.example.cryptomanager_v2.utils.di.ActivityBuilder
import com.example.cryptomanager_v2.utils.di.components.AppComponent
import com.example.cryptomanager_v2.utils.mocks.db.FakeDBExchangesDao
import com.example.cryptomanager_v2.utils.mocks.db.FakeDBFiatsDao
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.reactivex.Observable
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
    fun fakeDBExchangesDao(): DBExchangeDao
}