package com.example.cryptomanager_v2.utils

import com.example.cryptomanager_v2.utils.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().app(this).build()
    }
}