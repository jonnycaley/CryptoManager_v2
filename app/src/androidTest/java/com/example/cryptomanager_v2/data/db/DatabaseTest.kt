package com.example.cryptomanager_v2.data.db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cryptomanager_v2.data.db.cryptos.DBCryptoBuilder
import com.example.cryptomanager_v2.data.db.cryptos.DBCryptosDao
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangeBuilder
import com.example.cryptomanager_v2.data.db.exchanges.DBExchangesDao
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsBuilder
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import com.example.cryptomanager_v2.utils.observeOnce
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var db: AppDatabase

    private lateinit var dbCryptosDao: DBCryptosDao
    private lateinit var dbExchangesDao: DBExchangesDao
    private lateinit var dbFiatsDao: DBFiatsDao

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

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
            .assertValue {
                it[0] == cryptos[0]
            }
    }

    @Test
    fun writeExchangesAndRead() {
        val exchanges = DBExchangeBuilder.buildDB()
        dbExchangesDao.insertAll(exchanges).test()

        dbExchangesDao.getAll().test()
            .assertValue {
                it[0] == exchanges[0]
            }
    }

    @Test
    fun writeFiatsAndRead() {
        val fiats = DBFiatsBuilder.buildDB()
        dbFiatsDao.insertAll(fiats).test()

        dbFiatsDao.getAll()
            .observeOnce {
                assert(it[0] == fiats[0])
            }

    }
}