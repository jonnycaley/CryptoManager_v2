package com.example.cryptomanager_v2.ui.home.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val fiatsDao: DBFiatsDao
): ViewModel() {

    val baseFiat: LiveData<DBFiat>
        get() = fiatsDao.getBaseFiat()

    init {

    }
}

//data class Settings(val settingsSections: SettingsSections)

//sealed class SettingsSections {
//    sealed class SettingsGeneral: SettingsSections() {
//        class SelectBaseCurrency(baseFiat: DBFiat): SettingsGeneral()
//        object NightMode: SettingsGeneral()
//    }
//    sealed class SettingsData: SettingsSections() {
//        object SavedArticles: SettingsData()
//        object TransactionHistory: SettingsData()
//        object DeleteArticles: SettingsData()
//        object DeletePortfolio: SettingsData()
//    }
//    sealed class SettingsAbout: SettingsSections() {
//        object ReviewApp: SettingsAbout()
//        object ShareApp: SettingsAbout()
//        object SendFeedback: SettingsAbout()
//        class Version(version: String): SettingsAbout()
//    }
//}
