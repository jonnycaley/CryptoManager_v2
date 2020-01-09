package com.example.cryptomanager_v2.ui.home.ui.settings

import com.airbnb.epoxy.TypedEpoxyController
import com.example.cryptomanager_v2.ui.home.ui.settings.models.ClickableItem
import com.example.cryptomanager_v2.ui.home.ui.settings.models.dividerLarge
import com.example.cryptomanager_v2.ui.home.ui.settings.models.dividerSmall
import com.example.cryptomanager_v2.ui.home.ui.settings.models.item
import com.example.cryptomanager_v2.ui.home.ui.settings.models.title
import javax.inject.Inject

class SettingsEpoxyController @Inject constructor(
): TypedEpoxyController<SettingsData>() {

    override fun buildModels(settingsData: SettingsData) {
        settingsData.sections.forEach {
            buildTitle(it.title)
            buildDivider()
            buildItems(it.items)
            buildDivider()
        }
    }
    private fun buildDivider() {
        dividerLarge {
            id("divider_large")
        }
    }

    private fun buildItems(items: List<ClickableItem>) {
        items.forEach {
            if (it != items[0]){
                dividerSmall {
                    id("divider_small")
                }
            }
            item {
                id(it.text)
                title(it.text)
                onItemClickAction {
                    it.clickAction.invoke()
                }
            }
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