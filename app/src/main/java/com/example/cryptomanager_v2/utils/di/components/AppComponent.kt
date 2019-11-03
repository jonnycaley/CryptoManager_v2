package com.example.cryptomanager_v2.utils.di.components

import com.example.cryptomanager_v2.utils.App
import com.example.cryptomanager_v2.utils.di.ActivityBuilder
import com.example.cryptomanager_v2.utils.di.modules.AppModule
import com.example.cryptomanager_v2.utils.di.modules.DatabaseModule
import com.example.cryptomanager_v2.utils.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        DatabaseModule::class,
        AppModule::class
    ]
)
interface AppComponent {
    fun inject(app: App)

    @Component.Builder
    interface Builder {

        fun build() : AppComponent

        @BindsInstance
        fun app(app: App): Builder
    }
}