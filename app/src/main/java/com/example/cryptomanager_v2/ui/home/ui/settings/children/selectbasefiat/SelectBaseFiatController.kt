package com.example.cryptomanager_v2.ui.home.ui.settings.children.selectbasefiat

import com.airbnb.epoxy.TypedEpoxyController
import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import com.example.cryptomanager_v2.ui.home.ui.settings.models.item
import javax.inject.Inject

class SelectBaseFiatController @Inject constructor(
    private val presentationMapper: SelectBaseFiatPresentationMapper
): TypedEpoxyController<List<DBFiat>>() {

    lateinit var callback: SelectBaseFiatCallback

    override fun buildModels(data: List<DBFiat>) {
        data.forEach {
            item {
                id(it.name)
                title(presentationMapper.formatFiat(it))
                onItemClickAction {
                    callback.setBaseFiat(it)
                }
            }
        }
    }

    interface SelectBaseFiatCallback {
        fun setBaseFiat(it: DBFiat)
    }
}