package com.example.cryptomanager_v2.utils.di.modules

import android.content.Context
import com.example.cryptomanager_v2.utils.App
import com.example.cryptomanager_v2.utils.di.Utils
import com.example.cryptomanager_v2.utils.di.subcomponents.splash.SplashSubcomponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
//    (
//    subcomponents = [
//        SplashSubcomponent::class
//    ]
//)
class AppModule {
    
    @Singleton
    @Provides
    internal fun provideContext(application: App): Context {
        return application
    }

    @Singleton
    @Provides
    internal fun provideUtils(context: Context): Utils {
        return Utils(context)
    }
}