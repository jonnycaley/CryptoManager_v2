package com.example.cryptomanager_v2.utils.di.modules

import android.content.Context
import androidx.room.Room
import com.example.cryptomanager_v2.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    @JvmStatic
    fun providesRoomDatabase(
        context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "db"
        ).build()
    }
}