package com.example.cryptomanager_v2.utils

import android.app.Application
import com.example.cryptomanager_v2.utils.di.components.AppComponent
import com.example.cryptomanager_v2.utils.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App: Application(), HasAndroidInjector {
    //https://www.arthlimchiu.com/2019/09/03/advanced-dagger-in-mvvm.html#iteration-3-reduce-boilerplate-code-by-using-dagger-android

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
            .builder()
            .build()
        component.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}

lateinit var component: AppComponent