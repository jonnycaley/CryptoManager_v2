package com.example.cryptomanager_v2.utils.json

import android.content.Context

object JsonUtils {
    private fun readJsonFile(context: Context, filePath: String): String {
        return context.javaClass.classLoader?.getResource(filePath)?.readText().toString()
    }
}