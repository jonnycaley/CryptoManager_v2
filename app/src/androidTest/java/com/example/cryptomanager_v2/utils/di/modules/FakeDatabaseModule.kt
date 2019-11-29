package com.example.cryptomanager_v2.utils.di.modules

import com.example.cryptomanager_v2.data.db.cryptos.DBCryptosDao
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangesDao
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import com.example.cryptomanager_v2.utils.mocks.db.FakeDBCryptosDao
import com.example.cryptomanager_v2.utils.mocks.db.FakeDBExchangesDao
import com.example.cryptomanager_v2.utils.mocks.db.FakeDBFiatsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FakeDatabaseModule {

    @Provides
    @Singleton
    fun providesDBExchangesDao(): DBExchangesDao {
        return FakeDBExchangesDao()
    }

    @Provides
    @Singleton
    fun providesDBFiatsDao(): DBFiatsDao {
        return FakeDBFiatsDao()
    }

    @Provides
    @Singleton
    fun providesDBCryptosDao(): DBCryptosDao {
        return FakeDBCryptosDao()
    }
}