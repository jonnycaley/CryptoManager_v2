package com.example.cryptomanager_v2.utils.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptomanager_v2.ui.home.ui.markets.MarketsViewModel
import com.example.cryptomanager_v2.ui.home.ui.settings.SettingsViewModel
import com.example.cryptomanager_v2.ui.splash.SplashViewModel
import com.example.cryptomanager_v2.utils.di.ViewModelFactory
import com.example.cryptomanager_v2.utils.di.keys.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    internal abstract fun splashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    internal abstract fun settingsViewModel(viewModel: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MarketsViewModel::class)
    internal abstract fun marketsViewModel(viewModel: MarketsViewModel): ViewModel
}