package com.example.cryptomanager_v2.ui.home.ui.settings

import android.content.Context
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptomanager_v2.BuildConfig
import com.example.cryptomanager_v2.R
import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import com.example.cryptomanager_v2.data.db.fiats.DBFiatsDao
import com.example.cryptomanager_v2.ui.home.ui.settings.children.selectbasefiat.SelectBaseFiatActivity
import com.example.cryptomanager_v2.ui.home.ui.settings.models.ClickableItem
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val fiatsDao: DBFiatsDao,
    private val resources: Resources,
    private val context: Context
): ViewModel() {

    val baseFiat: LiveData<DBFiat>
        get() = fiatsDao.getBaseFiat()

    private var _settingsData = MutableLiveData<SettingsData>()

    val settingsData: LiveData<SettingsData>
        get() = _settingsData

    fun loadSettingsSections(baseFiat: DBFiat) {

        val generalTitle = resources.getString(R.string.settings_fragment_general_title)
        val generalStrings = resources.getStringArray(R.array.settings_fragment_general)
        val generalItems = mutableListOf<ClickableItem>().apply {
            add(ClickableItem(String.format(generalStrings[0], baseFiat.name, baseFiat.rate)) {
                context.startActivity(SelectBaseFiatActivity.create(context))
            })
            add(ClickableItem(generalStrings[1]) {
                println("Clicked 1!")
            })
        }
        val generalSection = Section(generalTitle, generalItems)

        val dataTitle = resources.getString(R.string.settings_fragment_data_title)
        val dataStrings = resources.getStringArray(R.array.settings_fragment_data)
        val dataItems = mutableListOf<ClickableItem>().apply {
            add(ClickableItem(dataStrings[0]) {
                println("Clicker 0!")
            })
            add(ClickableItem(dataStrings[1]) {
                println("Clicked 1!")
            })
            add(ClickableItem(dataStrings[2]) {
                println("Clicked 2!")
            })
            add(ClickableItem(dataStrings[3]) {
                println("Clicked 3!")
            })
        }
        val dataSection = Section(dataTitle, dataItems)

        val aboutTitle = resources.getString(R.string.settings_fragment_about_title)
        val aboutStrings = resources.getStringArray(R.array.settings_fragment_about)
        val aboutItems = mutableListOf<ClickableItem>().apply {
            add(ClickableItem(aboutStrings[0]) {println("Clicked 0!")})
            add(ClickableItem(aboutStrings[1]) {println("Clicked 1!")})
            add(ClickableItem(aboutStrings[2]) {println("Clicked 2!")})
            add(ClickableItem(String.format(aboutStrings[3], BuildConfig.VERSION_NAME)) {println("Clicked 3!")})
        }
        val aboutSection = Section(aboutTitle, aboutItems)

        _settingsData.value = SettingsData(
            listOf(generalSection, dataSection, aboutSection)
        )
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
