package com.example.cryptomanager_v2.utils.di.components

import com.example.cryptomanager_v2.ui.splash.SplashActivity
import com.example.cryptomanager_v2.utils.App
import com.example.cryptomanager_v2.utils.di.ActivityBuilder
import com.example.cryptomanager_v2.utils.di.subcomponents.splash.SplashSubcomponent
import com.example.cryptomanager_v2.utils.di.modules.NetworkModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        AndroidInjectionModule::class,
        ActivityBuilder::class
//        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(app: App)

//    fun splashSubcomponent(): SplashSubcomponent.Builder

//    fun inject(splashActivity: SplashActivity)
}