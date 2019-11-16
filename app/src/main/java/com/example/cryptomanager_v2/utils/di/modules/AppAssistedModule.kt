package com.example.cryptomanager_v2.utils.di.modules

import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module

@AssistedModule
@Module(includes = [AssistedInject_AppAssistedModule::class])
abstract class AppAssistedModule