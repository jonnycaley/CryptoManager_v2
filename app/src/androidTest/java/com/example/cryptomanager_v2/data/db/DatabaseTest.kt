package com.example.cryptomanager_v2.data.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cryptomanager_v2.data.db.cryptos.DBCryptoBuilder
import com.example.cryptomanager_v2.data.db.cryptos.DBCryptosDao
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangesDao
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var db: AppDatabase

    private lateinit var dbCryptosDao: DBCryptosDao
    private lateinit var dbExchangesDao: DBExchangesDao
    private lateinit var dbFiatsDao: DBFiatsDao

    @Before
    fun before() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        dbCryptosDao = db.cryptosDao()
        dbExchangesDao = db.exchangesDao()
        dbFiatsDao = db.fiatsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeCryptosAndRead() {
        val cryptos = DBCryptoBuilder.buildDB()
        dbCryptosDao.insertAll(cryptos).test()

        dbCryptosDao.getAll().test()
            .awaitCount(1)
            .assertValue {
                it[0] == cryptos[0]
            }
    }
}