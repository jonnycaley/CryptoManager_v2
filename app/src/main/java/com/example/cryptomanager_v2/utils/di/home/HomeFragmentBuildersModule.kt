package com.example.cryptomanager_v2.utils.di.home

import com.example.cryptomanager_v2.ui.home.ui.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun providesSettingsFragment(): SettingsFragment

}