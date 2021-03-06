package com.example.cryptomanager_v2.utils.di.components

import com.example.cryptomanager_v2.utils.App
import com.example.cryptomanager_v2.utils.di.ActivityBuilder
import com.example.cryptomanager_v2.utils.di.modules.AppAssistedModule
import com.example.cryptomanager_v2.utils.di.modules.AppModule
import com.example.cryptomanager_v2.utils.di.modules.DatabaseModule
import com.example.cryptomanager_v2.utils.di.modules.NetworkModule
import com.example.cryptomanager_v2.utils.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        DatabaseModule::class,
        AppModule::class,
        AppAssistedModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent: AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        fun build() : AppComponent

        @BindsInstance
        fun app(app: App): Builder
    }
}