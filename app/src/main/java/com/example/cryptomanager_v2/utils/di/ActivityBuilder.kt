package com.example.cryptomanager_v2.utils.di

import com.example.cryptomanager_v2.ui.splash.SplashActivity
import com.example.cryptomanager_v2.utils.di.scopes.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun splashActivity(): SplashActivity
}