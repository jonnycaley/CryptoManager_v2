package com.example.cryptomanager_v2.ui.home.ui.settings.models

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.cryptomanager_v2.R
import com.example.cryptomanager_v2.R2

@EpoxyModelClass(layout = R2.layout.settings_title)
abstract class TitleModel: EpoxyModelWithHolder<TitleModel.Holder>() {

    @EpoxyAttribute
    lateinit var title: String

    override fun bind(holder: Holder) {
        holder.title.text = title
    }

    inner class Holder: EpoxyHolder() {
        lateinit var title: TextView
        override fun bindView(itemView: View) {
            title = itemView.findViewById(R.id.text_title)
        }
    }

}