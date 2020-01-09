package com.example.cryptomanager_v2.ui.home.ui.settings.children.selectbasefiat

import com.example.cryptomanager_v2.data.db.fiats.DBFiat
import javax.inject.Inject

class SelectBaseFiatPresentationMapper @Inject constructor() {
    fun formatFiat(dbFiat: DBFiat): String {
        return "${dbFiat.name} ${dbFiat.rate}"
    }
}