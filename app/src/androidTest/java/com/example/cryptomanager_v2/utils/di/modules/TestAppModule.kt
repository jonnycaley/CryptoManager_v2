package com.example.cryptomanager_v2.utils.di.modules

import com.example.cryptomanager_v2.utils.TestAppSchedulers
import com.example.cryptomanager_v2.utils.AppSchedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestAppModule {

    @Provides
    @Singleton
    fun providesAppSchedulers(): AppSchedulers {
        return TestAppSchedulers.get()
    }
}