package com.example.cryptomanager_v2.utils.di.modules

import android.content.Context
import android.content.res.Resources
import com.example.cryptomanager_v2.utils.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule {
    
    @Singleton
    @Provides
    internal fun provideContext(application: App): Context {
        return application
    }

    @Singleton
    @Provides
    internal fun provideResources(context: Context): Resources {
        return context.resources
    }
}