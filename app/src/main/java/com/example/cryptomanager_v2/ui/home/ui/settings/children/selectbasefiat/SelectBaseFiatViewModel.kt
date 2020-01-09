package com.example.cryptomanager_v2.ui.home.ui.settings.children.selectbasefiat

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import com.example.cryptomanager_v2.utils.AppSchedulers
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class SelectBaseFiatViewModel @AssistedInject constructor(
    private val fiatsDao: DBFiatsDao,
    private val schedulers: AppSchedulers,
    @Assisted private val context: Context
): ViewModel() {

    private var disposable = CompositeDisposable()

    val fiats: LiveData<List<DBFiat>>
        get() = fiatsDao.getAll()

    fun setBaseFiat(it: DBFiat) {

        Single.fromCallable { fiatsDao.resetFiats(it.name) }
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.computation)
            .subscribe()
            .addTo(disposable)

        Single.fromCallable { fiatsDao.setBaseFiat(it.name) }
            .subscribeOn(schedulers.io)
            .observeOn(schedulers.computation)
            .subscribe()
            .addTo(disposable)
    }

    @AssistedInject.Factory
    interface Factory{
        fun create(context: Context): SelectBaseFiatViewModel
    }
}