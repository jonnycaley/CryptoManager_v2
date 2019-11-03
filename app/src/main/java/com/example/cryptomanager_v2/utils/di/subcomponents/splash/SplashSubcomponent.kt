package com.example.cryptomanager_v2.utils.di.subcomponents.splash

import com.example.cryptomanager_v2.ui.splash.SplashActivity
import com.example.cryptomanager_v2.utils.di.scopes.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [
        SplashActivityModule::class
    ]
)
interface SplashSubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): SplashSubcomponent
    }

    fun inject(activity: SplashActivity)
}