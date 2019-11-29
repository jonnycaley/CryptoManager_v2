package com.example.cryptomanager_v2.utils.di.modules

import android.content.Context
import androidx.room.Room
import com.example.cryptomanager_v2.data.db.AppDatabase
import com.example.cryptomanager_v2.data.db.cryptos.DBCryptosDao
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangesDao
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    @JvmStatic
    fun providesRoomDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "db").build()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideExchangesDao(db: AppDatabase): DBExchangesDao {
        return db.exchangesDao()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideFiatsDao(db: AppDatabase): DBFiatsDao {
        return db.fiatsDao()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideCryptosDao(db: AppDatabase): DBCryptosDao {
        return db.cryptosDao()
    }
}