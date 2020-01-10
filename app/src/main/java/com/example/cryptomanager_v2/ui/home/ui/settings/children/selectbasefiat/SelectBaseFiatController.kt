package com.example.cryptomanager_v2.ui.home.ui.settings.children.selectbasefiat

import com.airbnb.epoxy.TypedEpoxyController
import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import com.example.cryptomanager_v2.ui.home.ui.settings.models.dividerSmall
import com.example.cryptomanager_v2.ui.home.ui.settings.models.item
import javax.inject.Inject

class SelectBaseFiatController @Inject constructor(
    private val presentationMapper: SelectBaseFiatPresentationMapper
): TypedEpoxyController<List<DBFiat>>() {

    lateinit var callback: SelectBaseFiatCallback

    override fun buildModels(data: List<DBFiat>) {
        data.forEachIndexed { index, dbFiat ->
            if (index != 0)
                dividerSmall {
                    id("divider_small")
                }
            item {
                id(dbFiat.name)
                title(presentationMapper.formatFiat(dbFiat))
                onItemClickAction {
                    callback.setBaseFiat(dbFiat)
                }
            }
        }
    }

    interface SelectBaseFiatCallback {
        fun setBaseFiat(it: DBFiat)
    }
}