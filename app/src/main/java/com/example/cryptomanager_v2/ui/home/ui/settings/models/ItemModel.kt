package com.example.cryptomanager_v2.ui.home.ui.settings.models

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.cryptomanager_v2.R
import com.example.cryptomanager_v2.R2

@EpoxyModelClass(layout = R2.layout.settings_item)
abstract class ItemModel: EpoxyModelWithHolder<ItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var title: String

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onItemClickListener: View.OnClickListener

    override fun bind(holder: Holder) {
        holder.itemText.text = title
    }

    inner class Holder: EpoxyHolder() {
        lateinit var itemText: TextView
        override fun bindView(itemView: View) {
            itemText = itemView.findViewById(R.id.text_item)
        }
    }

}

data class ClickableItem(
    var text: String,
    var clickListener: Unit
)