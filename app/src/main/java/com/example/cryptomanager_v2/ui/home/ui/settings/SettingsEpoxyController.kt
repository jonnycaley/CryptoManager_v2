package com.example.cryptomanager_v2.ui.home.ui.settings

import android.content.res.Resources
import com.airbnb.epoxy.TypedEpoxyController
import com.example.cryptomanager_v2.ui.home.ui.settings.models.ClickableItem
import com.example.cryptomanager_v2.ui.home.ui.settings.models.item
import com.example.cryptomanager_v2.ui.home.ui.settings.models.title
import javax.inject.Inject

class SettingsEpoxyController @Inject constructor(
    private val resources: Resources
): TypedEpoxyController<SettingsData>() {

    override fun buildModels(settingsData: SettingsData) {
        settingsData.sections.forEach {
            buildTitle(it.title)
            it.items.forEach { item ->
                buildItem(item)
            }
        }
    }
    private fun buildItem(item: ClickableItem) {
        item {
            id(item.text)
            title(item.text)
            onItemClickListener { _ -> item.clickListener }
        }
    }

    private fun buildTitle(title: String) {
        title {
            id(title)
            title(title)
        }
    }
}

data class SettingsData(
    val sections: List<Section>
)

data class Section(
    val title: String,
    val items: List<ClickableItem>
)