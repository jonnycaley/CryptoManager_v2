package com.example.cryptomanager_v2.ui.home.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val fiatsDao: DBFiatsDao
): ViewModel() {

    val fiats: LiveData<DBFiat>
        get() = fiatsDao.getBaseFiat()

    init {

    }

}
