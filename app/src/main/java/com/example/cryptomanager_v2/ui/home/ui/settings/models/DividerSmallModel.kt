package com.example.cryptomanager_v2.ui.home.ui.settings.models

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.cryptomanager_v2.R2

@EpoxyModelClass(layout = R2.layout.divider_small)
abstract class DividerSmallModel: EpoxyModelWithHolder<DividerSmallModel.Holder>() {
    inner class Holder: EpoxyHolder() {
        override fun bindView(itemView: View) {}
    }
}