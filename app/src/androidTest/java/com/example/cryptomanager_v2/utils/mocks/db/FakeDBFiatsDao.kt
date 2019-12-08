package com.example.cryptomanager_v2.utils.mocks.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import io.reactivex.Completable
import javax.inject.Inject

class FakeDBFiatsDao @Inject constructor(): DBFiatsDao {

    val getAllResponses: ArrayList<List<DBFiat>> = arrayListOf()
    override fun getAll(): LiveData<List<DBFiat>> {
        return MutableLiveData<List<DBFiat>>(getAllResponses.first())
    }

    val insertAllResponses: ArrayList<Completable> = arrayListOf()
    override fun insertAll(fiats: List<DBFiat>): Completable {
        return insertAllResponses.first()
    }
}