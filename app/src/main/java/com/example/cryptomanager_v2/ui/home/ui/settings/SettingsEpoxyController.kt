package com.example.cryptomanager_v2.ui.home.ui.settings

import android.content.res.Resources
import com.airbnb.epoxy.TypedEpoxyController
import com.example.cryptomanager_v2.R
import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import com.example.cryptomanager_v2.ui.home.ui.settings.models.title
import javax.inject.Inject

class SettingsEpoxyController @Inject constructor(
    private val resources: Resources
): TypedEpoxyController<DBFiat>() {

    override fun buildModels(baseFiat: DBFiat) {
        buildGeneral()
        buildData()
        buildAbout()
    }

    private fun buildGeneral() {
        val titleString = resources.getString(R.string.settings_fragment_general_title)
        title {
            id("GeneralTitle")
            title(titleString)
        }
    }


    private fun buildData() {
        val titleString = resources.getString(R.string.settings_fragment_data_title)
        title {
            id("DataTitle")
            title(titleString)
        }
    }

    private fun buildAbout() {
        val titleString = resources.getString(R.string.settings_fragment_about_title)
        title {
            id("AboutTitle")
            title(titleString)
        }
    }
}