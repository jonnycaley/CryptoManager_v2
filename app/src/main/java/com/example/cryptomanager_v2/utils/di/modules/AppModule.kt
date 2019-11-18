package com.example.cryptomanager_v2.utils.di.modules

import android.content.Context
import com.example.cryptomanager_v2.utils.App
import com.example.cryptomanager_v2.utils.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    
    @Singleton
    @Provides
    internal fun provideContext(application: App): Context {
        return application
    }
}