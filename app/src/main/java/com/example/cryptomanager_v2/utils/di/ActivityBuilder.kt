package com.example.cryptomanager_v2.utils.di

import com.example.cryptomanager_v2.ui.home.HomeActivity
import com.example.cryptomanager_v2.ui.home.ui.settings.children.selectbasefiat.SelectBaseFiatActivity
import com.example.cryptomanager_v2.ui.splash.SplashActivity
import com.example.cryptomanager_v2.ui.test.TestActivity
import com.example.cryptomanager_v2.utils.di.home.HomeFragmentBuildersModule
import com.example.cryptomanager_v2.utils.di.scopes.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun splashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [HomeFragmentBuildersModule::class]
    )
    abstract fun homeActivity(): HomeActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun testActivity(): TestActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun selectBaseFiatActivity(): SelectBaseFiatActivity
}