package com.example.cryptomanager_v2.utils.di.components

import com.example.cryptomanager_v2.utils.App
import com.example.cryptomanager_v2.utils.di.ActivityBuilder
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
    ]
)
interface AppComponent {
    fun inject(app: App)
}