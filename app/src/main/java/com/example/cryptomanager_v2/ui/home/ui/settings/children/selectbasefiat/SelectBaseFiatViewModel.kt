package com.example.cryptomanager_v2.ui.home.ui.settings.children.selectbasefiat

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class SelectBaseFiatViewModel @AssistedInject constructor(
    private val fiatsDao: DBFiatsDao,
    @Assisted private val context: Context
): ViewModel() {

    val fiats: LiveData<List<DBFiat>>
        get() = fiatsDao.getAll()

    init {

    }

    @AssistedInject.Factory
    interface Factory{
        fun create(context: Context): SelectBaseFiatViewModel
    }
}