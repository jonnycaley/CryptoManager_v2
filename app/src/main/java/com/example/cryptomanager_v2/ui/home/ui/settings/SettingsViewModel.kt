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

        val generalSection = Section(getGeneralTitle(), getGeneralItems(baseFiat))
        val dataSection = Section(getDataTitle(), getDataItems())
        val aboutSection = Section(getAboutTitle(), getAboutItems())

        _settingsData.value = SettingsData(
            listOf(generalSection, dataSection, aboutSection)
        )
    }

    private fun getGeneralTitle() = resources.getString(R.string.settings_fragment_general_title)
    private fun getGeneralItems(baseFiat: DBFiat): List<ClickableItem> {
        val generalItemTitles = resources.getStringArray(R.array.settings_fragment_general)
        return mutableListOf<ClickableItem>().apply {
            add(ClickableItem(String.format(generalItemTitles[0], baseFiat.name, baseFiat.rate)) {
                context.startActivity(SelectBaseFiatActivity.create(context))
            })
            add(ClickableItem(generalItemTitles[1]) {
                println("Clicked 1!")
            })
        }
    }

    private fun getAboutTitle(): String = resources.getString(R.string.settings_fragment_about_title)
    private fun getAboutItems(): List<ClickableItem> {
        val aboutItemTitles = resources.getStringArray(R.array.settings_fragment_about)
        return mutableListOf<ClickableItem>().apply {
            add(ClickableItem(aboutItemTitles[0]) {println("Clicked 0!")})
            add(ClickableItem(aboutItemTitles[1]) {println("Clicked 1!")})
            add(ClickableItem(aboutItemTitles[2]) {println("Clicked 2!")})
            add(ClickableItem(String.format(aboutItemTitles[3], BuildConfig.VERSION_NAME)) {println("Clicked 3!")})
        }
    }

    private fun getDataTitle(): String = resources.getString(R.string.settings_fragment_data_title)
    private fun getDataItems(): List<ClickableItem> {
        val dataItemTitles = resources.getStringArray(R.array.settings_fragment_data)
        return mutableListOf<ClickableItem>().apply {
            add(ClickableItem(dataItemTitles[0]) {
                println("Clicker 0!")
            })
            add(ClickableItem(dataItemTitles[1]) {
                println("Clicked 1!")
            })
            add(ClickableItem(dataItemTitles[2]) {
                println("Clicked 2!")
            })
            add(ClickableItem(dataItemTitles[3]) {
                println("Clicked 3!")
            })
        }
    }
}